package com.test.votting.vottingtest.Fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
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
        candidateRecyclerview=(RecyclerView)v.findViewById(R.id.candidateRecyclerview);
        gridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        adapterCandidatesRecyclerview=new AdapterCandidatesRecyclerview(HelperCLass.arrayList,getActivity());
        candidateRecyclerview.setAdapter(adapterCandidatesRecyclerview);
        candidateRecyclerview.setLayoutManager(gridLayoutManager);

        if(HelperCLass.arrayList.isEmpty()) {
            LongOperation longOperation = new LongOperation();
            longOperation.execute("");
        }
        //adapterCandidatesRecyclerview.notifyDataSetChanged();
//
//        if(HelperCLass.arrayList.isEmpty())
//            getCandidates();

        return v;
    }
    public void getCandidates()  {
    //    Toast.makeText(getActivity(), "Accessed", Toast.LENGTH_SHORT).show();
        if(!HelperCLass.arrayList.isEmpty() && HelperCLass.arrayList !=null)
            HelperCLass.  arrayList.clear();



//        setGetCandidatesInformations=new SetGetCandidatesInformations();
//        setGetCandidatesInformations.setName("Rajai Abu-Saqer");
//        setGetCandidatesInformations.setCandidateNationalID("999");
//        setGetCandidatesInformations.setCity("AMMAN");
//        setGetCandidatesInformations.setYear("2018");
//        setGetCandidatesInformations.setStatusVoted(0);
//        setGetCandidatesInformations.setCampaign("خير الناس انفعهم للناس ، الوطن للجميع والجميع للوطن ، القضية الفلسطينية اولوية ،وباسمكم نتكلم ومن اجلكم نعمل ، بصوتك يبدأ التغيير");
//        HelperCLass.  arrayList.add(setGetCandidatesInformations);
//
//
//        setGetCandidatesInformations=new SetGetCandidatesInformations();
//        setGetCandidatesInformations.setName("Yousef Baydoun");
//        setGetCandidatesInformations.setCandidateNationalID("888");
//        setGetCandidatesInformations.setYear("2018");
//        setGetCandidatesInformations.setCity("IRBID");
//        setGetCandidatesInformations.setStatusVoted(0);
//        setGetCandidatesInformations.setCampaign(" قادمون للتجديد ، الاردن بحاجة للتجديد ، قادمون للتجديد فكونوا عونا لنا");
//        HelperCLass. arrayList.add(setGetCandidatesInformations);
//
//
//
//        setGetCandidatesInformations=new SetGetCandidatesInformations();
//        setGetCandidatesInformations.setName("Moath Animation");
//        setGetCandidatesInformations.setCandidateNationalID("777");
//        setGetCandidatesInformations.setYear("2018");
//        setGetCandidatesInformations.setCity("ZARQA");
//        setGetCandidatesInformations.setStatusVoted(0);
//        setGetCandidatesInformations.setCampaign("امن الاردن خط احمر ،محاربة الفساد واجب وطني");
//        HelperCLass. arrayList.add(setGetCandidatesInformations);
//
//
//        setGetCandidatesInformations=new SetGetCandidatesInformations();
//        setGetCandidatesInformations.setName("Yaqeen Mohammad");
//        setGetCandidatesInformations.setCandidateNationalID("666");
//        setGetCandidatesInformations.setYear("2018");
//        setGetCandidatesInformations.setCity("SALT");
//        setGetCandidatesInformations.setStatusVoted(0);
//        setGetCandidatesInformations.setCampaign("التعليم والتأمين حق للجميع ، معكم نصنع التغير وبصوت الجميع نحقق الهد ");
//        HelperCLass.  arrayList.add(setGetCandidatesInformations);
    }

    class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try {
                String nationalID;
//        int XX;
                try {
                    candidateLength=Integer.parseInt(String.valueOf(HelperCLass.candidates.getNationalIDArrayLength().send()));
                    Log.d("candidateLength", HelperCLass.candidates.getNationalIDArrayLength().send().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

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

//                adapterCandidatesRecyclerview.notifyDataSetChanged();

            } catch (Exception e) {
                e.printStackTrace();
                //     Log.d("ErrorSignUp",e.getMessage());
            }

            return null;
        }

    }

}
