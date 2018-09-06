package com.test.votting.vottingtest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.test.votting.vottingtest.SolCode.Candidates;
import com.test.votting.vottingtest.SolCode.Judgment;
import com.test.votting.vottingtest.SolCode.Voters;

import org.json.JSONException;
import org.json.JSONObject;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.JsonRpc2_0Web3j;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.UUID;

public class LoginActivity extends AppCompatActivity {

    EditText nationalID,password;
    HelperCLass helperCLass;
    SharedPreferences.Editor editor;
    CreatePrivateAndPublicKeys createPrivateAndPublicKeys;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        helperCLass=new HelperCLass(LoginActivity.this);
//        nationalID=(EditText)findViewById(R.id.nationalID);
//        password=(EditText)findViewById(R.id.password);
//        progressDialog=new ProgressDialog(LoginActivity.this);
//        progressDialog.setMessage("Please Wait");
//        if(helperCLass.getSharedPreferences().getString("ContractAddressVoter","").equals(""))
//        progressDialog.show();
//        LongOperation longOperation=new LongOperation();
//        longOperation.execute("");
//        createPrivateAndPublicKeys=new CreatePrivateAndPublicKeys(LoginActivity.this);
//        String seed = UUID.randomUUID().toString();
//        JSONObject result = createPrivateAndPublicKeys.process(seed); // get a json containing private key and address
//        Toast.makeText(LoginActivity.this, result.toString(),Toast.LENGTH_SHORT).show();
//        Log.d("MyKey",result.toString());
//        helperCLass.getEditor().clear();
//        helperCLass.getEditor().commit();
    }
//
//    public void loginFunc(View view) {
//        if(nationalID.getText().toString().isEmpty())
//            Toast.makeText(LoginActivity.this, "NationalID is required", Toast.LENGTH_SHORT).show();
//        else if(password.getText().toString().isEmpty())
//            Toast.makeText(LoginActivity.this, "Password is required", Toast.LENGTH_SHORT).show();
//        else {
//
//            try {
//                if( !HelperCLass.voters.checkIdAndPassword(nationalID.getText().toString(),password.getText().toString()).send().equals(""))
//                {
//                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    editor=helperCLass.getEditor();
//                    editor.putString("nationalID",nationalID.getText().toString());
//                    editor.putString("MyAddress",HelperCLass.voters.checkIdAndPassword(nationalID.getText().toString(),password.getText().toString()).send());
//
//                    editor.commit();
//                    startActivity(new Intent(LoginActivity.this, Main2Activity.class));
//                    finish();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//
//    }
//
//
//
//
//
//
//    class LongOperation extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//
//
//            try {
//
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
//
//                //      Log.d("Rajai", String.valueOf(test.getCounter().send()));
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                Log.d("MyExcError",e.getMessage());
//                //    Toast.makeText(Main2Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//            progressDialog.dismiss();
//            return null;
//        }
//
//    }
//
}