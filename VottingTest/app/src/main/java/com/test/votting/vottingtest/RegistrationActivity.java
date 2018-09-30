package com.test.votting.vottingtest;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.test.votting.vottingtest.Fragments.SigninVoterFragment;
import com.test.votting.vottingtest.Fragments.SignupVoterFragment;

import org.json.JSONObject;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class RegistrationActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    public static   TextView registrationTitle;
    HelperCLass helperCLass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        helperCLass=new HelperCLass(RegistrationActivity.this);
        helperCLass.keepScreenLight();
        helperCLass.getLanguage();
        // helperCLass.getEditor().clear().commit();
        registrationTitle=(TextView)findViewById(R.id.registrationTitle);
//        progressDialog=new ProgressDialog(RegistrationActivity.this);
//        progressDialog.setMessage("Please Wait");
//        if(helperCLass.getSharedPreferences().getString("ContractAddressVoter","").equals(""))
//            progressDialog.show();

        fragmentManager=getFragmentManager();
        changeFragments(new SigninVoterFragment());

    }

    public void changeFragments(Fragment fragment)
    {
        transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.linearRegitration,fragment);
       // transaction.addToBackStack("");
        transaction.commit();
      //  return  transaction;
    }

    public void signinFunc(View view) {
        if(!registrationTitle.getText().toString().equals("SIGN-IN"))
        {
            changeFragments(new SigninVoterFragment());
        }
    }
    public void signupFunc(View view) {
        if(!registrationTitle.getText().toString().equals("SIGN-UP"))
        {
            changeFragments(new SignupVoterFragment());
        }
    }



    @Override
    public void onBackPressed() {

        if(registrationTitle.getText().toString().equals("SIGN-UP"))
            changeFragments(new SigninVoterFragment());
        else
            super.onBackPressed();                   // else
//        if(registrationTitle.getText().toString().equals("SIGN-IN"))
//            finish();

    }

}
