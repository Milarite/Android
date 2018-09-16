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
       // helperCLass.getEditor().clear().commit();
        registrationTitle=(TextView)findViewById(R.id.registrationTitle);
//        progressDialog=new ProgressDialog(RegistrationActivity.this);
//        progressDialog.setMessage("Please Wait");
//        if(helperCLass.getSharedPreferences().getString("ContractAddressVoter","").equals(""))
//            progressDialog.show();
        LongOperation longOperation=new LongOperation();
        longOperation.execute("");
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

    class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {


            try {



                    HelperCLass.voters = HelperCLass.voters.load("0xd598964c540fbe6e49f121c381887032a62483fd",HelperCLass.web3,HelperCLass.credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
                    HelperCLass.candidates = HelperCLass.candidates.load("0xc3c1987d618f2fa91e0f775519a21477ca67312f",HelperCLass.web3,HelperCLass.credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
                    HelperCLass.judgment = HelperCLass.judgment.load("0x037f8f496f33428acafa2ae53f49b5c1d05003db",HelperCLass.web3,HelperCLass.credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
                    HelperCLass.mainContract = HelperCLass.mainContract.load("0x39b87fb1061f819c4273e97a08de237943f7b40d",HelperCLass.web3,HelperCLass.credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);


    //                if(helperCLass.getSharedPreferences().getString("ContractAddressVoter","").equals(""))
//                {
//                    HelperCLass.voters = HelperCLass.voters.deploy(HelperCLass.web3,HelperCLass.credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT).send();
//                    helperCLass.getEditor().putString("ContractAddressVoter",HelperCLass.voters.getContractAddress());
//                    helperCLass.getEditor().commit();
//                    Log.d("VoterAddress",helperCLass.getSharedPreferences().getString("ContractAddressVoter",""));
//
//                }
//                else
//                {
//                    HelperCLass.voters = HelperCLass.voters.load(helperCLass.getSharedPreferences().getString("ContractAddressVoter",""),HelperCLass.web3,HelperCLass.credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
//                    Log.d("VoterAddress",helperCLass.getSharedPreferences().getString("ContractAddressVoter",""));
//
//                }
//
//
//
//
//                if(helperCLass.getSharedPreferences().getString("ContractAddressCandidate","").equals(""))
//                {
//                    HelperCLass.candidates = HelperCLass.candidates.deploy(HelperCLass.web3,HelperCLass.credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT).send();
//                    helperCLass.getEditor().putString("ContractAddressCandidate",HelperCLass.candidates.getContractAddress());
//                    helperCLass.getEditor().commit();
//                    //  Toast.makeText(Main2Activity.this, String.valueOf(HelperCLass.candidates.getNationalIDArrayLength().send()), Toast.LENGTH_SHORT).show();
//                    Log.d("MyLengthDeploy", String.valueOf(HelperCLass.candidates.getNationalIDArrayLength().send()));
//                    Log.d("CandidateAddress",HelperCLass.candidates.getContractAddress());
//
//                }
//                else
//                {
//                    HelperCLass.candidates = HelperCLass.candidates.load(helperCLass.getSharedPreferences().getString("ContractAddressCandidate",""),HelperCLass.web3,HelperCLass.credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
//                    //      Toast.makeText(Main2Activity.this, String.valueOf(HelperCLass.candidates.getNationalIDArrayLength().send()), Toast.LENGTH_SHORT).show();
//                    Log.d("MyLengthLod", String.valueOf(HelperCLass.candidates.getNationalIDArrayLength().send()));
//                    Log.d("CandidateAddress",HelperCLass.candidates.getContractAddress());
//
//                }
//
//                if(helperCLass.getSharedPreferences().getString("ContractAddressJudgment","").equals(""))
//                {
//                    HelperCLass.judgment = HelperCLass.judgment.deploy(HelperCLass.web3,HelperCLass.credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT).send();
//                    helperCLass.getEditor().putString("ContractAddressJudgment",HelperCLass.judgment.getContractAddress());
//                    helperCLass.getEditor().commit();
//
//                    Log.d("JudgmentAddress",helperCLass.getSharedPreferences().getString("ContractAddressJudgment",""));
//
//                }
//                else
//                {
//                    HelperCLass.judgment = HelperCLass.judgment.load(helperCLass.getSharedPreferences().getString("ContractAddressJudgment",""),HelperCLass.web3,HelperCLass.credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
//                    Log.d("JudgmentAddress",helperCLass.getSharedPreferences().getString("ContractAddressJudgment",""));
//
//                }
//
//
//                if(helperCLass.getSharedPreferences().getString("ContractAddressMain","").equals(""))
//                {
//
//                    HelperCLass.mainContract = HelperCLass.mainContract.deploy(HelperCLass.web3,HelperCLass.credentials,
//                            ManagedTransaction.GAS_PRICE,
//                            Contract.GAS_LIMIT,
//                            helperCLass.getSharedPreferences().getString("ContractAddressCandidate","")
//                            ,helperCLass.getSharedPreferences().getString("ContractAddressVoter","")
//                            ,helperCLass.getSharedPreferences().getString("ContractAddressJudgment","")).send();
//
//
//                    helperCLass.getEditor().putString("ContractAddressMain",HelperCLass.judgment.getContractAddress());
//                    helperCLass.getEditor().commit();
//
//                    Log.d("MainAddress",helperCLass.getSharedPreferences().getString("ContractAddressMain",""));
//
//                }
//                else
//                {
//                    HelperCLass.mainContract = HelperCLass.mainContract.load(helperCLass.getSharedPreferences().getString("ContractAddressMain",""),HelperCLass.web3,HelperCLass.credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
//                    Log.d("MainAddress",helperCLass.getSharedPreferences().getString("ContractAddressMain",""));
//
//                }

                //      Log.d("Rajai", String.valueOf(test.getCounter().send()));

            } catch (Exception e) {
                e.printStackTrace();
                Log.d("MyExcError",e.getMessage());
                //    Toast.makeText(Main2Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

         //   progressDialog.dismiss();
            return null;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(registrationTitle.getText().toString().equals("SIGN-UP"))
            changeFragments(new SigninVoterFragment());
                   // else
//        if(registrationTitle.getText().toString().equals("SIGN-IN"))
//            finish();

    }

}
