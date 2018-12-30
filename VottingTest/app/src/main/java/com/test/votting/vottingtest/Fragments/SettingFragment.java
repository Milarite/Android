package com.test.votting.vottingtest.Fragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.Gravity;
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

        year.setText(HelperCLass.myYear);
        city.setText( HelperCLass.myCity);
        birthOfDate.setText(HelperCLass.myBD);
        voterName.setText(HelperCLass.myName);
        nationalID.setText(helperCLass.getSharedPreferences().getString("nationalID",""));

        if(helperCLass.getSharedPreferences().getString("Lang","").equals("ar"))
        {
            nationalID.setGravity(Gravity.LEFT);
            voterName.setGravity(Gravity.LEFT);
            birthOfDate.setGravity(Gravity.LEFT);
            city.setGravity(Gravity.LEFT);
            year.setGravity(Gravity.LEFT);

        }



        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RegistrationActivity.class));
                getActivity().finish();

            }
        });

        return v;
    }
//    public void setText(TextView ... txt){
//        txt.setGravity(Gravity.LEFT);
//    }
}
