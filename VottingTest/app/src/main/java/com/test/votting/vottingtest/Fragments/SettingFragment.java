package com.test.votting.vottingtest.Fragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.votting.vottingtest.HelperCLass;
import com.test.votting.vottingtest.Main2Activity;
import com.test.votting.vottingtest.Moduls.SetGetMyVotes;
import com.test.votting.vottingtest.R;
import com.test.votting.vottingtest.RegistrationActivity;

import java.math.BigInteger;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    TextView nationalID,voterName,birthOfDate,city,year,signOut;
    HelperCLass helperCLass;
    String yearString,cityString,birthOfDateString,voterNameString,nationalIDString;
    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_setting, container, false);
        helperCLass=new HelperCLass(getActivity());
        nationalID=(TextView)v.findViewById(R.id.nationalID);
        voterName=(TextView)v.findViewById(R.id.voterName);
        birthOfDate=(TextView)v.findViewById(R.id.birthOfDate);
        city=(TextView)v.findViewById(R.id.city);
        year=(TextView)v.findViewById(R.id.year);
        signOut=(TextView)v.findViewById(R.id.signOut);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RegistrationActivity.class));
                getActivity().finish();

            }
        });
        if(HelperCLass.myYear.equals(""))
        {
         new LongOperation().execute("");
        }
        else
        {
            year.setText(HelperCLass.myYear);
            city.setText( HelperCLass.myCity);
            birthOfDate.setText(HelperCLass.myBD);
            voterName.setText(HelperCLass.myName);
            nationalID.setText(helperCLass.getSharedPreferences().getString("nationalID",""));
        }
        return v;
    }

    class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try {
                String voterAddress=helperCLass.getSharedPreferences().getString("MyAddress","");
                HelperCLass.myYear=HelperCLass.mainContract.getVoterYear(voterAddress).send();
                HelperCLass.myCity= HelperCLass.mainContract.getVoterCity(voterAddress).send();
                HelperCLass.myBD=HelperCLass.mainContract.getVoterDateOfBirth(voterAddress).send();
                HelperCLass.myName=HelperCLass.mainContract.getVoterName(voterAddress).send();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            year.setText(HelperCLass.myYear);
            city.setText( HelperCLass.myCity);
            birthOfDate.setText(HelperCLass.myBD);
            voterName.setText(HelperCLass.myName);
            nationalID.setText(helperCLass.getSharedPreferences().getString("nationalID",""));
            }
    }





}
