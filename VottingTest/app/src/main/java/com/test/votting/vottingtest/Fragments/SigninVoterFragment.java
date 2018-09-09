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
import com.test.votting.vottingtest.Main2Activity;
import com.test.votting.vottingtest.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SigninVoterFragment extends Fragment {

    EditText nationalID,password;
    HelperCLass helperCLass;
    SharedPreferences.Editor editor;
    TextView signin;
    String signInStatus;
    ProgressDialog progressDialog;
    public SigninVoterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_signin_voter, container, false);
        signin=(TextView)v.findViewById(R.id.signin);

        helperCLass=new HelperCLass(getActivity());
        nationalID=(EditText)v.findViewById(R.id.nationalID);
        password=(EditText)v.findViewById(R.id.password);
        helperCLass.getEditor().putString("MyPrivate","");
        helperCLass.getEditor().putString("MyAddress","");
        helperCLass.getEditor().commit();



//        helperCLass.getEditor().clear();
//        helperCLass.getEditor().commit();


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFunc();
            }
        });

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


            if(signInStatus.equals("signInStatus"))
            {
                Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_SHORT).show();
            }
            else
            {
                editor=helperCLass.getEditor();
                    editor.putString("nationalID",nationalID.getText().toString());
                    editor.putString("MyAddress",signInStatus);
                    editor.commit();
                    startActivity(new Intent(getActivity(), Main2Activity.class));
                    getActivity().finish();
            }
            progressDialog.dismiss();

        }

        @Override
        protected String doInBackground(String... params) {


            try {

                signInStatus=HelperCLass.voters.checkIdAndPassword(nationalID.getText().toString(),password.getText().toString()).send();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

    }





}


