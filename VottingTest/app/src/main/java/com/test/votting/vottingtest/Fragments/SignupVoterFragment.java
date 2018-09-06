package com.test.votting.vottingtest.Fragments;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.test.votting.vottingtest.CreatePrivateAndPublicKeys;
import com.test.votting.vottingtest.HelperCLass;
import com.test.votting.vottingtest.LoginActivity;
import com.test.votting.vottingtest.R;
import com.test.votting.vottingtest.RegistrationActivity;
import com.test.votting.vottingtest.SignupVoterActivity;

import org.json.JSONObject;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupVoterFragment extends Fragment {
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    EditText nationalID, password, name, birthOfDate, city, year;
    HelperCLass helperCLass;
    SharedPreferences.Editor editor;
    CreatePrivateAndPublicKeys createPrivateAndPublicKeys;
    RegistrationActivity registrationActivity;
    TextView signup;
    public SignupVoterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_signup_voter, container, false);
        fragmentManager=getFragmentManager();
        signup=(TextView)v.findViewById(R.id.signup);
        registrationActivity=new RegistrationActivity();
        nationalID = (EditText)v. findViewById(R.id.nationalID);
        password = (EditText)v. findViewById(R.id.password);
        name = (EditText)v. findViewById(R.id.name);
        birthOfDate = (EditText)v. findViewById(R.id.birthOfDate);
        city = (EditText)v. findViewById(R.id.city);
        year = (EditText)v. findViewById(R.id.year);
        createPrivateAndPublicKeys = new CreatePrivateAndPublicKeys(getActivity());


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupFunc();
            }
        });
        return v;
    }

    public void signupFunc() {
        if (nationalID.getText().toString().isEmpty())
            Toast.makeText(getActivity(), "NationalID is required", Toast.LENGTH_SHORT).show();
        else if (password.getText().toString().isEmpty())
            Toast.makeText(getActivity(), "Password is required", Toast.LENGTH_SHORT).show();
        else if (name.getText().toString().isEmpty())
            Toast.makeText(getActivity(), "Name is required", Toast.LENGTH_SHORT).show();
        else if (birthOfDate.getText().toString().isEmpty())
            Toast.makeText(getActivity(), "Birth of date is required", Toast.LENGTH_SHORT).show();
        else if (city.getText().toString().isEmpty())
            Toast.makeText(getActivity(), "City is required", Toast.LENGTH_SHORT).show();
        else if (year.getText().toString().isEmpty())
            Toast.makeText(getActivity(), "Year is required", Toast.LENGTH_SHORT).show();
        else {
            fragmentManager=getFragmentManager();

            LongOperation longOperation=new LongOperation();
            longOperation.execute("");

        }
    }


    class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try {

                if(HelperCLass.voters.checkNationalID(nationalID.getText().toString()).send()==true)
                {
                    String seed = UUID.randomUUID().toString();
                    JSONObject result = createPrivateAndPublicKeys.process(seed); // get a json containing private key and address
                    Log.d("resultJSon",result.toString());

                    /*"privatekey")+"//"+*/
                    HelperCLass.voters.signUpVoter(result.getString("address"),nationalID.getText().toString()
                            , password.getText().toString(), name.getText().toString(), birthOfDate.getText().toString()
                            , city.getText().toString(), year.getText().toString()).send();
                  //  Toast.makeText(getActivity(), "Done", Toast.LENGTH_SHORT).show();
                    transaction=fragmentManager.beginTransaction();
                    transaction.replace(R.id.linearRegitration,new SigninVoterFragment());
                    transaction.addToBackStack("");
                    transaction.commit();

                }
                else
                {
                  //  Toast.makeText(getActivity(), "This national id is exist", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                //     Log.d("ErrorSignUp",e.getMessage());
            }

            return null;
        }

    }




}
