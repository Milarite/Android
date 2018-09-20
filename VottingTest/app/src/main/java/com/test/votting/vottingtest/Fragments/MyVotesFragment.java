package com.test.votting.vottingtest.Fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test.votting.vottingtest.Adapters.AdapterMyVotesRecyclerview;
import com.test.votting.vottingtest.HelperCLass;
import com.test.votting.vottingtest.Moduls.SetGetMyVotes;
import com.test.votting.vottingtest.R;

import java.math.BigInteger;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyVotesFragment extends Fragment {

     SwipeRefreshLayout swipeContainer;
    LongOperation longOperation;
    String candidatesIVoted;
    int myVotesLength=0;
    String candidateName;
    RecyclerView histotyRecyclerview;
    SetGetMyVotes setGetMyVotes;
    AdapterMyVotesRecyclerview adapterHistoryRecyclerview;
    StaggeredGridLayoutManager gridLayoutManager;
    HelperCLass helperCLass;
    public MyVotesFragment() {

//        HelperCLass.voters.getNationalIDArrayLength();
//        HelperCLass.candidates.getCandidateNumberOfVotes(helperCLass.getSharedPreferences().getString("nationalID",""));
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_my_votes, container, false);
        swipeContainer = (SwipeRefreshLayout) v.findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        helperCLass=new HelperCLass(getActivity());
        histotyRecyclerview=(RecyclerView)v.findViewById(R.id.histotyRecyclerview);
        gridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        adapterHistoryRecyclerview=new AdapterMyVotesRecyclerview(HelperCLass.arrayListMyVotes,getActivity());
        histotyRecyclerview.setAdapter(adapterHistoryRecyclerview);
        histotyRecyclerview.setLayoutManager(gridLayoutManager);


        histotyRecyclerview.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(recyclerView.canScrollVertically(-1))
                {
                    swipeContainer.setEnabled(false);
                }
                else
                    swipeContainer.setEnabled(true);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


            }
        });

      //  if(HelperCLass.arrayListMyVotes.isEmpty()) {
             longOperation = new LongOperation();
            longOperation.execute("");
       // }






        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                 longOperation = new LongOperation();

                longOperation.execute("");
            }
        });
        return v;
    }

    class LongOperation extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(HelperCLass.arrayListMyVotes !=null)
                HelperCLass.arrayListMyVotes.clear();
            adapterHistoryRecyclerview.notifyDataSetChanged();
            if(!swipeContainer.isRefreshing())
                swipeContainer.setRefreshing(true);

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            if(swipeContainer.isRefreshing())
                swipeContainer.setRefreshing(false);

        }

        @Override
        protected String doInBackground(String... params) {




            try {

                myVotesLength= Integer.parseInt(String.valueOf(HelperCLass.mainContract.getNationalIDArrayLength(
                        helperCLass.getSharedPreferences().getString("MyAddress","")).send()));

                for (int i = 0; i < myVotesLength; i++) {

                    candidatesIVoted = HelperCLass.mainContract.getVotedCandidatesAddress(
                            helperCLass.getSharedPreferences().getString("MyAddress",""), BigInteger.valueOf(i)).send();
               candidateName=HelperCLass.mainContract.getCandidateName(candidatesIVoted).send();
//
                     if(!candidateName.isEmpty()) {
                         setGetMyVotes = new SetGetMyVotes();

                         setGetMyVotes.setCity(HelperCLass.mainContract.getCandidateCity(candidatesIVoted).send());
                         setGetMyVotes.setName(candidateName);
                         setGetMyVotes.setYear(HelperCLass.mainContract.getCandidateYear(candidatesIVoted).send());
                         setGetMyVotes.setCampaign(HelperCLass.mainContract.getCandidateCampaign(candidatesIVoted).send());
                         setGetMyVotes.setNationalID(candidatesIVoted);
                         HelperCLass.arrayListMyVotes.add(setGetMyVotes);
                     }

                }
            }
            catch (Exception e) {
                e.printStackTrace();
             //   Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            return null;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
                 adapterHistoryRecyclerview.notifyDataSetChanged();
                 if(swipeContainer.isRefreshing())
            swipeContainer.setRefreshing(false);

        //    Toast.makeText(getActivity(),myVotesLength , Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(longOperation !=null)
            longOperation.cancel(true);



    }

}
