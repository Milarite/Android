package com.test.votting.vottingtest;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.TextView;

import com.test.votting.vottingtest.Fragments.SignupVoterFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DialogDatePicker  extends Dialog {

    DatePicker datePickerBD;
    Activity activity;
    public TextView yes, no;
    SimpleDateFormat simpleDateFormat;
    public DialogDatePicker(Activity a) {
        super(a);
        activity = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_datepicker);
        simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        yes = (TextView) findViewById(R.id.yes);
        no = (TextView) findViewById(R.id.no);
        datePickerBD=(DatePicker)findViewById(R.id.datePickerBD);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SignupVoterFragment.birthOfDate.setText(datePickerBD.getDayOfMonth() +"/"+ String.valueOf(Integer.parseInt(String.valueOf(datePickerBD.getMonth()))+1) +"/"+ datePickerBD.getYear() );
                dismiss();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }
}
