package com.test.votting.vottingtest.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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


    int myVotesLength=0;
    RecyclerView histotyRecyclerview;
    SetGetMyVotes setGetMyVotes;
    AdapterMyVotesRecyclerview adapterHistoryRecyclerview;
    StaggeredGridLayoutManager gridLayoutManager;
    HelperCLass helperCLass=new HelperCLass(getActivity());
    public MyVotesFragment() {
        try {
            myVotesLength= Integer.parseInt(String.valueOf(HelperCLass.mainContract.getNationalIDArrayLength("").send()));
        } catch (Exception e) {
            e.printStackTrace();
//            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
//        HelperCLass.voters.getNationalIDArrayLength();
//        HelperCLass.candidates.getCandidateNumberOfVotes(helperCLass.getSharedPreferences().getString("nationalID",""));
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_my_votes, container, false);
        histotyRecyclerview=(RecyclerView)v.findViewById(R.id.histotyRecyclerview);
        gridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        adapterHistoryRecyclerview=new AdapterMyVotesRecyclerview(HelperCLass.arrayListMyVotes,getActivity());
        histotyRecyclerview.setAdapter(adapterHistoryRecyclerview);
        histotyRecyclerview.setLayoutManager(gridLayoutManager);

        getMyVotesList();
        return v;
    }

    public void getMyVotesList()
    {
        try {


            for (int i = 0; i < myVotesLength; i++) {
                String candidatesIVoted = HelperCLass.mainContract.getVotedCandidatesAddress("", BigInteger.valueOf(i)).send();
                setGetMyVotes = new SetGetMyVotes();

                setGetMyVotes.setCity(HelperCLass.mainContract.getVoterCity(candidatesIVoted).send());
                setGetMyVotes.setName(HelperCLass.mainContract.getCandidateName(candidatesIVoted).send());
                setGetMyVotes.setYear(HelperCLass.mainContract.getCandidateYear(candidatesIVoted).send());
                HelperCLass.arrayListMyVotes.add(setGetMyVotes);
            }
            adapterHistoryRecyclerview.notifyDataSetChanged();
        }
        catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

    }
}
