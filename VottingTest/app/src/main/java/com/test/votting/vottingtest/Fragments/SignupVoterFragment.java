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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.test.votting.vottingtest.CreatePrivateAndPublicKeys;
import com.test.votting.vottingtest.DialogDatePicker;
import com.test.votting.vottingtest.HelperCLass;
import com.test.votting.vottingtest.InternetConnection;
import com.test.votting.vottingtest.R;
import com.test.votting.vottingtest.RegistrationActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.web3j.abi.datatypes.Bool;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupVoterFragment extends Fragment {
    Exception ee;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    public static TextView birthOfDate;
    EditText nationalID, password, name, year;
    Spinner spinnerCity;
    ArrayAdapter arrayAdapterSpinner;
    String citySelected="";
    String citiesList[]={"Amman","Zarqa","Irbid"};
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

      RegistrationActivity.registrationTitle.setText("SIGN-UP");

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

        arrayAdapterSpinner=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,citiesList);
        spinnerCity.setAdapter(arrayAdapterSpinner);
        citySelected=spinnerCity.getSelectedItem().toString();

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                citySelected=citiesList[position].toLowerCase().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( InternetConnection.ifConnect(getActivity()))
                    signupFunc();
                else
                    Toast.makeText(getActivity(), "No internet connection", Toast.LENGTH_SHORT).show();
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
//        else if (citySelected=="Select")
//            Toast.makeText(getActivity(), "Select city", Toast.LENGTH_SHORT).show();
        else if (year.getText().toString().isEmpty())
            Toast.makeText(getActivity(), "Year is required", Toast.LENGTH_SHORT).show();
        else {
          progressDialog=helperCLass.getProgress("Signup","Please wait");
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


                HelperCLass.voters = HelperCLass.voters.load("0xf8d7dc55188b29190d5e4dcc894b594ab5d189ed",HelperCLass.web3,HelperCLass.credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
                HelperCLass.candidates = HelperCLass.candidates.load("0xbec4ffa286100049fe83e1be587023fad86805e1",HelperCLass.web3,HelperCLass.credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
                HelperCLass.judgment = HelperCLass.judgment.load("0x5c370326026c333850fa1929c2f6a296e6ea0e5a",HelperCLass.web3,HelperCLass.credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
                HelperCLass.mainContract = HelperCLass.mainContract.load("0x8cb777633e94138e6be575707746c4a248a8719e",HelperCLass.web3,HelperCLass.credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);




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
                Toast.makeText(getActivity(), "This national id is exist", Toast.LENGTH_SHORT).show();
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
            if(progressDialog.isShowing())
                progressDialog.dismiss();

                  Toast.makeText(getActivity(), "Done", Toast.LENGTH_SHORT).show();
                transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.linearRegitration,new SigninVoterFragment());
                transaction.addToBackStack("");
                transaction.commit();
            }

        @Override
        protected String doInBackground(String... params) {


            String seed = UUID.randomUUID().toString();
            JSONObject result = createPrivateAndPublicKeys.process(seed);
            try {
                HelperCLass.mainContract.signUpVoter(result.getString("address"),nationalID.getText().toString()
                        , password.getText().toString(), name.getText().toString(), birthOfDate.getText().toString()
                        , citySelected, year.getText().toString()).send();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
