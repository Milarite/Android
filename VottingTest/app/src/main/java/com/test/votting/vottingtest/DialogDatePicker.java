package com.test.votting.vottingtest;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.test.votting.vottingtest.Fragments.SignupVoterFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DialogDatePicker  extends Dialog {

    DatePicker datePickerBD;
    Activity activity;
    public TextView yes, no;
    public DialogDatePicker(Activity a) {
        super(a);
        activity = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_datepicker);
        yes = (TextView) findViewById(R.id.yes);
        no = (TextView) findViewById(R.id.no);
        datePickerBD=(DatePicker)findViewById(R.id.datePickerBD);
        datePickerBD.setMaxDate(new Date().getTime());
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String date=getNum(datePickerBD.getDayOfMonth())
                        +"/"+
                        getNum(datePickerBD.getMonth()+1)
                        +"/"+
                        datePickerBD.getYear();

                SignupVoterFragment.birthOfDate.setText(date);
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
    public String getNum(int num)
    {
        if(num<10)
            return "0"+num;
        else
            return String.valueOf(num);

    }
}
