package com.test.votting.vottingtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.UUID;

public class SignupVoterActivity extends AppCompatActivity {
    EditText nationalID, password, name, birthOfDate, city, year;
    HelperCLass helperCLass;
    SharedPreferences.Editor editor;
    CreatePrivateAndPublicKeys createPrivateAndPublicKeys;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_voter);
//        nationalID = (EditText) findViewById(R.id.nationalID);
//        password = (EditText) findViewById(R.id.password);
//        name = (EditText) findViewById(R.id.name);
//        birthOfDate = (EditText) findViewById(R.id.birthOfDate);
//        city = (EditText) findViewById(R.id.city);
//        year = (EditText) findViewById(R.id.year);
//        createPrivateAndPublicKeys = new CreatePrivateAndPublicKeys(SignupVoterActivity.this);

    }

//    public void signupFunc(View view) {
//        if (nationalID.getText().toString().isEmpty())
//            Toast.makeText(SignupVoterActivity.this, "NationalID is required", Toast.LENGTH_SHORT).show();
//        else if (password.getText().toString().isEmpty())
//            Toast.makeText(SignupVoterActivity.this, "Password is required", Toast.LENGTH_SHORT).show();
//        else if (name.getText().toString().isEmpty())
//            Toast.makeText(SignupVoterActivity.this, "Name is required", Toast.LENGTH_SHORT).show();
//        else if (birthOfDate.getText().toString().isEmpty())
//            Toast.makeText(SignupVoterActivity.this, "Birth of date is required", Toast.LENGTH_SHORT).show();
//        else if (city.getText().toString().isEmpty())
//            Toast.makeText(SignupVoterActivity.this, "City is required", Toast.LENGTH_SHORT).show();
//        else if (year.getText().toString().isEmpty())
//            Toast.makeText(SignupVoterActivity.this, "Year is required", Toast.LENGTH_SHORT).show();
//        else {
//
//            try {
//                if(HelperCLass.voters.checkNationalID(nationalID.getText().toString()).send()==true)
//                {
//                    String seed = UUID.randomUUID().toString();
//                    JSONObject result = createPrivateAndPublicKeys.process(seed); // get a json containing private key and address
//
//                    HelperCLass.voters.signUpVoter(result.getString("address"),nationalID.getText().toString()
//                            , password.getText().toString(), name.getText().toString(), birthOfDate.getText().toString()
//                            , city.getText().toString(), year.getText().toString()).send();
//                    Toast.makeText(SignupVoterActivity.this, "Done", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(SignupVoterActivity.this, LoginActivity.class));
//                    finish();
//                }
//                else
//                {
//                    Toast.makeText(SignupVoterActivity.this, "This national id is exist", Toast.LENGTH_SHORT).show();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                Toast.makeText(SignupVoterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//
//
//
//
//
//        }
//    }
}
