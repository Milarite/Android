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

import com.test.votting.vottingtest.Adapters.AdapterCandidatesRecyclerview;
import com.test.votting.vottingtest.HelperCLass;
import com.test.votting.vottingtest.Moduls.SetGetCandidatesInformations;
import com.test.votting.vottingtest.R;

import org.json.JSONObject;

import java.math.BigInteger;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class CandidatesFramgnet extends Fragment {
    SwipeRefreshLayout swipeContainer;
    LongOperation longOperation;
    RecyclerView candidateRecyclerview;
    int candidateLength=0;
    SetGetCandidatesInformations setGetCandidatesInformations;
    StaggeredGridLayoutManager gridLayoutManager;
    AdapterCandidatesRecyclerview adapterCandidatesRecyclerview;
    public CandidatesFramgnet() {
        // Required empty public constructor

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_candidates_framgnet, container, false);
        swipeContainer = (SwipeRefreshLayout) v.findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        candidateRecyclerview=(RecyclerView)v.findViewById(R.id.candidateRecyclerview);
        gridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        adapterCandidatesRecyclerview=new AdapterCandidatesRecyclerview(HelperCLass.arrayList,getActivity());
        candidateRecyclerview.setAdapter(adapterCandidatesRecyclerview);
        candidateRecyclerview.setLayoutManager(gridLayoutManager);

        if(HelperCLass.arrayList.isEmpty()) {
             longOperation = new LongOperation();
            longOperation.execute("");
        }

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                 longOperation = new LongOperation();

                longOperation.execute("");
            }
        });
        //adapterCandidatesRecyclerview.notifyDataSetChanged();
//
//        if(HelperCLass.arrayList.isEmpty())
//            getCandidates();

        return v;
    }


    class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            if(HelperCLass.arrayList !=null)
                HelperCLass.arrayList.clear();

            try {
                String nationalID;

                candidateLength=Integer.parseInt(String.valueOf(HelperCLass.candidates.getNationalIDArrayLength().send()));

                for(int i=0;i<candidateLength;i++)
                {

                    try {
                        nationalID=HelperCLass.mainContract.getCandidateNationalID(BigInteger.valueOf(i)).send();

                        setGetCandidatesInformations=new SetGetCandidatesInformations();
                        setGetCandidatesInformations.setName(HelperCLass.mainContract.getCandidateName(nationalID).send());
                        setGetCandidatesInformations.setCandidateNationalID(nationalID);
                        setGetCandidatesInformations.setCity(HelperCLass.mainContract.getCandidateCity(nationalID).send());
                        setGetCandidatesInformations.setYear(HelperCLass.mainContract.getCandidateYear(nationalID).send());
                        setGetCandidatesInformations.setNumberOfVotes( Integer.parseInt(String.valueOf(HelperCLass.mainContract.getCandidateVotesNumber(nationalID).send())));
                        setGetCandidatesInformations.setCampaign(HelperCLass.mainContract.getCandidateCampaign(nationalID).send());
                        HelperCLass.  arrayList.add(setGetCandidatesInformations);
                        //       Toast.makeText(getActivity(), String.valueOf(HelperCLass.arrayList.size()), Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();

                        // Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        //   Log.d("CandidateErrors",e.getMessage());
                    }

                }
                // lazem afaker esh el eshii ele bzbot 3la HashMap


            } catch (Exception e) {
                e.printStackTrace();
                //     Log.d("ErrorSignUp",e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            adapterCandidatesRecyclerview.notifyDataSetChanged();
            if(swipeContainer.isRefreshing())
                swipeContainer.setRefreshing(false);

        }
    }

}
