package com.test.votting.vottingtest.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.test.votting.vottingtest.HelperCLass;
import com.test.votting.vottingtest.InternetConnection;
import com.test.votting.vottingtest.Main2Activity;
import com.test.votting.vottingtest.R;
import com.test.votting.vottingtest.RegistrationActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SigninVoterFragment extends Fragment {

    EditText nationalID,password;
    HelperCLass helperCLass;
    SharedPreferences.Editor editor;
    TextView signin;
    String signInStatus="0x0000000000000000000000000000000000000002",city;
    ProgressDialog progressDialog;
    public SigninVoterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_signin_voter, container, false);


        RegistrationActivity.registrationTitle.setText("SIGN-IN");

        signin=(TextView)v.findViewById(R.id.signin);

        helperCLass=new HelperCLass(getActivity());
        nationalID=(EditText)v.findViewById(R.id.nationalID);
        password=(EditText)v.findViewById(R.id.password);
        helperCLass.getEditor().clear().commit();
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( InternetConnection.ifConnect(getActivity()))
                    loginFunc();
                else
                    Toast.makeText(getActivity(), "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });
        HelperCLass.myYear="";
        HelperCLass.arrayList.clear();

        return v;
    }



    public void loginFunc() {
        if(nationalID.getText().toString().isEmpty())
            Toast.makeText(getActivity(), "NationalID is required", Toast.LENGTH_SHORT).show();
        else if(password.getText().toString().isEmpty())
            Toast.makeText(getActivity(), "Password is required", Toast.LENGTH_SHORT).show();
        else {
            progressDialog=helperCLass.getProgress("Signin","Please wait");
            progressDialog.show();
            LongOperationCheck longOperation=new LongOperationCheck();
            longOperation.execute("");


        }

    }

    class LongOperationCheck extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            if(signInStatus.equals("0x0000000000000000000000000000000000000002"))
            {
                Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
            else
            {
              new LongOperation().execute("");
            }

        }

        @Override
        protected String doInBackground(String... params) {


            try {

                signInStatus=HelperCLass.mainContract.checkIdAndPasswordVoter(nationalID.getText().toString(),password.getText().toString()).send();
                city=HelperCLass.mainContract.getVoterCity(signInStatus).send();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

    }
    class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

                editor=helperCLass.getEditor();
                editor.putString("nationalID",nationalID.getText().toString());
                editor.putString("MyAddress",signInStatus);
                editor.putString("city",city);
                editor.commit();
                startActivity(new Intent(getActivity(), Main2Activity.class));
                getActivity().finish();
                progressDialog.dismiss();

        }

        @Override
        protected String doInBackground(String... params) {


            try {

                city=HelperCLass.mainContract.getVoterCity(signInStatus).send();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

    }


}


