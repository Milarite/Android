package com.test.votting.vottingtest.Fragments;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
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
import com.test.votting.vottingtest.R;
import com.test.votting.vottingtest.RegistrationActivity;

import org.json.JSONObject;

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
    boolean signUpStatus;
    ProgressDialog progressDialog;

    public SignupVoterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_signup_voter, container, false);
        helperCLass=new HelperCLass(getActivity());
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
          progressDialog=helperCLass.getProgress("Signup","Please wait");
          progressDialog.show();
            fragmentManager=getFragmentManager();
            LongOperation longOperation=new LongOperation();
            longOperation.execute("");

        }
    }


    class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(signUpStatus==true)
                Toast.makeText(registrationActivity, "Done", Toast.LENGTH_SHORT).show();
            else
                  Toast.makeText(getActivity(), "This national id is exist", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                String seed = UUID.randomUUID().toString();
                JSONObject result = createPrivateAndPublicKeys.process(seed); // get a json containing private key and address
                if(HelperCLass.voters.checkNationalID(result.getString("address"),nationalID.getText().toString()
                        , password.getText().toString(), name.getText().toString(), birthOfDate.getText().toString()
                        , city.getText().toString(), year.getText().toString()).send()==true)
                {

                    signUpStatus=true;

                    /*"privatekey")+"//"+*/
//                    HelperCLass.voters.signUpVoter(result.getString("address"),nationalID.getText().toString()
//                            , password.getText().toString(), name.getText().toString(), birthOfDate.getText().toString()
//                            , city.getText().toString(), year.getText().toString()).send();
                  //  Toast.makeText(getActivity(), "Done", Toast.LENGTH_SHORT).show();
                    transaction=fragmentManager.beginTransaction();
                    transaction.replace(R.id.linearRegitration,new SigninVoterFragment());
                    transaction.addToBackStack("");
                    transaction.commit();


                }
                else
                {
                    signUpStatus=false;

                }
            } catch (Exception e) {
                e.printStackTrace();
                //     Log.d("ErrorSignUp",e.getMessage());
            }


            return null;
        }

    }




}
