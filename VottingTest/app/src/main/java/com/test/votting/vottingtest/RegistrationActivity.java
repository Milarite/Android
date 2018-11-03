package com.test.votting.vottingtest;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.test.votting.vottingtest.Fragments.SigninVoterFragment;
import com.test.votting.vottingtest.Fragments.SignupVoterFragment;

import org.json.JSONException;
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
    TextView language;
    HelperCLass helperCLass;
    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helperCLass=new HelperCLass(RegistrationActivity.this);
        HelperCLass.arrayListSpinnerCities.clear();
        helperCLass.keepScreenLight();
        helperCLass.getLanguage(helperCLass.getSharedPreferences().getString("Lang",""));
        setContentView(R.layout.activity_registration);
        registrationTitle=(TextView)findViewById(R.id.registrationTitle);
        language=(TextView)findViewById(R.id.language);
        HelperCLass.myYear="";
        HelperCLass.myCity="";
        HelperCLass.myName="";
        HelperCLass.myBD="";
       // helperCLass.getEditor().putString("Lang","").commit();

      //  language.setText(getResources().getString(R.string.Lang));
        fragmentManager=getFragmentManager();
        changeFragments(new SigninVoterFragment());
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(helperCLass.getSharedPreferences().getString("Lang","").equals("en"))
                    helperCLass.getEditor().putString("Lang","ar").commit();
                else
                    helperCLass.getEditor().putString("Lang","en").commit();
                helperCLass.getLanguage(helperCLass.getSharedPreferences().getString("Lang",""));
                startActivity(new Intent(RegistrationActivity.this,RegistrationActivity.class));
                finish();
            }
        });
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
        if(!registrationTitle.getText().toString().equals("SIGN-IN") && !registrationTitle.getText().toString().equals("تسجيل دخول"))
        {
            changeFragments(new SigninVoterFragment());
        }
    }
    public void signupFunc(View view) {

        if(!registrationTitle.getText().toString().equals("SIGN-UP") && !registrationTitle.getText().toString().equals("انشاء حساب"))
        {
            changeFragments(new SignupVoterFragment());
        }
    }



    @Override
    public void onBackPressed() {

        if(registrationTitle.getText().toString().equals("SIGN-UP")|| registrationTitle.getText().toString().equals("انشاء حساب"))
            changeFragments(new SigninVoterFragment());
        else
            super.onBackPressed();                   // else
//        if(registrationTitle.getText().toString().equals("SIGN-IN"))
//            finish();

    }

}
