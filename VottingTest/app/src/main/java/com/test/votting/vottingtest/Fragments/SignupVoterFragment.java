package com.test.votting.vottingtest.Fragments;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.test.votting.vottingtest.CreatePrivateAndPublicKeys;
import com.test.votting.vottingtest.DialogDatePicker;
import com.test.votting.vottingtest.HelperCLass;
import com.test.votting.vottingtest.InternetConnection;
import com.test.votting.vottingtest.R;
import com.test.votting.vottingtest.RegistrationActivity;
import com.test.votting.vottingtest.SendEth;

import org.json.JSONException;
import org.json.JSONObject;
import org.web3j.abi.datatypes.Bool;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupVoterFragment extends Fragment {
    Snackbar snackbar;
    JsonObjectRequest jsonObjectRequest;
    SendEth sendEth;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    BigInteger nonce;
    EthSendTransaction ethSendTransaction;
    public static TextView birthOfDate;
    EditText nationalID, password, name, year;
    Spinner spinnerCity;
    ArrayAdapter arrayAdapterSpinner;
    HelperCLass helperCLass;
    CreatePrivateAndPublicKeys createPrivateAndPublicKeys;
    RegistrationActivity registrationActivity;
    TextView signup;
    boolean signUpStatus;
    ProgressDialog progressDialog;
    JSONObject result;
    View v;
    public SignupVoterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v= inflater.inflate(R.layout.fragment_signup_voter, container, false);
        RegistrationActivity.registrationTitle.setText(getActivity().getResources().getString(R.string.SIGNUP));
        helperCLass=new HelperCLass(getActivity());
        fragmentManager=getFragmentManager();
        signup=(TextView)v.findViewById(R.id.signup);
        registrationActivity=new RegistrationActivity();
        nationalID = (EditText)v. findViewById(R.id.nationalID);
        password = (EditText)v. findViewById(R.id.password);
        name = (EditText)v. findViewById(R.id.name);
        birthOfDate = (TextView) v. findViewById(R.id.birthOfDate);
        spinnerCity = (Spinner)v. findViewById(R.id.city);
        year = (EditText)v. findViewById(R.id.year);
        birthOfDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogDatePicker(getActivity()).show();
            }
        });

        createPrivateAndPublicKeys = new CreatePrivateAndPublicKeys(getActivity());
        if(!HelperCLass.arrayListSpinnerCities.isEmpty()) {
            arrayAdapterSpinner = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, HelperCLass.arrayListSpinnerCities);
            spinnerCity.setAdapter(arrayAdapterSpinner);
        }
        else {
            if (helperCLass.getSharedPreferences().getString("Lang", "").equals("en"))
                getCities(HelperCLass.urlIPFSEnglish);
            else
                getCities(HelperCLass.urlIPFSArabic);

        }





        if(helperCLass.getSharedPreferences().getString("Lang","").equals("ar")) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.LEFT;
            params.leftMargin = 60;
            signup.setLayoutParams(params);
        }
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( InternetConnection.ifConnect(getActivity()))
                    signupFunc();
                else
                    Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.Nointernetconnection), Toast.LENGTH_SHORT).show();
            }

        });
        return v;
    }

    public void signupFunc() {
        if (nationalID.getText().toString().isEmpty())
            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.NationalIDisrequired), Toast.LENGTH_SHORT).show();
        else if (password.getText().toString().isEmpty())
            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.Passwordisrequired), Toast.LENGTH_SHORT).show();
        else if (name.getText().toString().isEmpty())
            Toast.makeText(getActivity(),             getActivity().getResources().getString(R.string.Nameisrequired), Toast.LENGTH_SHORT).show();
        else if (birthOfDate.getText().toString().isEmpty())
            Toast.makeText(getActivity(),             getActivity().getResources().getString(R.string.Birthofdateisrequired), Toast.LENGTH_SHORT).show();
//        else if (citySelected=="Select")
//            Toast.makeText(getActivity(), "Select city", Toast.LENGTH_SHORT).show();
        else if (year.getText().toString().isEmpty())
            Toast.makeText(getActivity(),             getActivity().getResources().getString(R.string.Yearisrequired), Toast.LENGTH_SHORT).show();
        else if(HelperCLass.arrayListSpinnerCities.isEmpty())
            Toast.makeText(getActivity(),             getActivity().getResources().getString(R.string.LoadCites), Toast.LENGTH_SHORT).show();

        else {
          progressDialog=helperCLass.getProgress(            getActivity().getResources().getString(R.string.Signup),getActivity().getResources().getString(R.string.Pleasewait));
          progressDialog.show();
            fragmentManager=getFragmentManager();
            LongOperationCheck longOperation=new LongOperationCheck();
            longOperation.execute();

        }
    }


    class LongOperationCheck extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            try {


                HelperCLass.voters = HelperCLass.voters.load(HelperCLass.voterAddress,HelperCLass.web3,HelperCLass.credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
                HelperCLass.candidates = HelperCLass.candidates.load(HelperCLass.candidateAddress,HelperCLass.web3,HelperCLass.credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
                HelperCLass.judgment = HelperCLass.judgment.load(HelperCLass.judgmentAddress,HelperCLass.web3,HelperCLass.credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
                HelperCLass.mainContract = HelperCLass.mainContract.load(HelperCLass.mainAddress,HelperCLass.web3,HelperCLass.credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);





                if(HelperCLass.mainContract.checkNationalIDVoter(nationalID.getText().toString()).send())
                signUpStatus=true;
                else
                    signUpStatus=false;
        } catch (Exception e) {
            e.printStackTrace();


        }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(signUpStatus==false)
            {
                LongOperationSignup longOperationSignup=new LongOperationSignup();
                longOperationSignup.execute("");
             //   Toast.makeText(getActivity(), "5ara", Toast.LENGTH_SHORT).show();

            }
            else if(signUpStatus==true)
            {
                Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.Thisnationalidisexist), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }

        //
//        @Override
//        protected boolean doInBackground(String... params) {
//           // signUpStatus=true;
//
//            try {
//                signUpStatus=true;
//
//                // String id = nationalID.getText().toString();
//             //   HelperCLass.mainContract.checkNationalID(nationalID.getText().toString()).send();
//           //     signUpStatus=HelperCLass.mainContract.checkNationalID(nationalID.getText().toString()).send();
//            } catch (Exception e) {
//                e.printStackTrace();
//                ee=e;
//
//            }
//            return signUpStatus;
//        }

    }

    class LongOperationSignup extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            LongOperationSendEther sendEther = new LongOperationSendEther();
            sendEther.execute(s);

            }

        @Override
        protected String doInBackground(String... params) {


            String seed = UUID.randomUUID().toString();
            result = createPrivateAndPublicKeys.process(seed);
            try {

                HelperCLass.mainContract.signUpVoter(result.getString("address"),
                        result.getString("privatekey"),
                        nationalID.getText().toString()
                        , password.getText().toString(), name.getText().toString(), birthOfDate.getText().toString()
                        , spinnerCity.getSelectedItem().toString().toLowerCase(), year.getText().toString()).send();

            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }
    }



    class LongOperationSendEther extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(progressDialog.isShowing())
                progressDialog.dismiss();

            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.Successful), Toast.LENGTH_SHORT).show();
            transaction=fragmentManager.beginTransaction();
            transaction.replace(R.id.linearRegitration,new SigninVoterFragment());
            transaction.addToBackStack("");
            transaction.commit();
        }
        @Override
        protected String doInBackground(String... params) {

            try {
//                Log.e("param",params[0]);
                nonce = HelperCLass.web3.ethGetTransactionCount("0xb248d34f2431824afe5147170fb98a7aa0f7499d",
                        DefaultBlockParameterName.LATEST).send().getTransactionCount();
                RawTransaction rawTransaction  = RawTransaction.createEtherTransaction(
                        nonce,
                        Contract.GAS_PRICE,
                        Contract.GAS_LIMIT,
                        result.getString("address"),
                      //  params[0].toString(),
                        new BigInteger("500000000000000000"));
                byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction,HelperCLass.credentials);
                String hexValue = Numeric.toHexString(signedMessage);
                ethSendTransaction = HelperCLass.web3.ethSendRawTransaction(hexValue).send();
         //    return ethSendTransaction.getTransactionHash().toString();





            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


    }


     public void getCities(String url) {

        if(HelperCLass.requestQueue==null)
            HelperCLass.requestQueue=Volley.newRequestQueue(getActivity());
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                    try {


                        for(int i=0;i<response.getJSONArray("lang").length();i++)
                        {
                            HelperCLass.arrayListSpinnerCities.add(response.getJSONArray("lang").getString(i));

                        }
                        arrayAdapterSpinner=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,HelperCLass.arrayListSpinnerCities);
                        spinnerCity.setAdapter(arrayAdapterSpinner);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
           snackbar=Snackbar.make(v, getActivity().getResources().getString(R.string.Nointernetconnectionpleasereloadthecities), Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction(getActivity().getResources().getString(R.string.Reload), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                if (helperCLass.getSharedPreferences().getString("Lang", "").equals("en"))
                                    getCities(HelperCLass.urlIPFSEnglish);
                                else
                                    getCities(HelperCLass.urlIPFSArabic);

                            }
                        })
                        .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ));



                snackbar.show();
            }
        });
       HelperCLass.requestQueue.add(jsonObjectRequest);

    }

    @Override
    public void onPause() {
        super.onPause();
        if(jsonObjectRequest!=null)
            jsonObjectRequest.cancel();
        if(snackbar !=null && snackbar.isShown())
            snackbar.dismiss();
    }
}
