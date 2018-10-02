package com.test.votting.vottingtest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.PowerManager;

import com.test.votting.vottingtest.Moduls.SetGetCandidatesInformations;
import com.test.votting.vottingtest.Moduls.SetGetMyVotes;
import com.test.votting.vottingtest.SolCode.Candidates;
import com.test.votting.vottingtest.SolCode.Judgment;
import com.test.votting.vottingtest.SolCode.MainContract;
import com.test.votting.vottingtest.SolCode.Voters;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.http.HttpService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HelperCLass
{
    public static PowerManager.WakeLock mWakeLock;

    public static   String fromTime,toTime,fromDate;

    public static boolean threshouldFlag=false;
    public static   SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy"); // the format of your date
    public static  SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");
    ProgressDialog progressDialog;
    //https://rinkeby.infura.io/v3/afbac1a223484d84a7784a133d1f2010  Moath
    //https://rinkeby.infura.io/v3/203f4b27aa6a41c6958b6c8ff6f4d729 Rajai
    //4274b048585600a5732d24d055e5ca2ed6df5311b895d4ed6c1aea0019881021 Rajai
    //50FBEE34A355F70931B95C5C114AED5FB21BAF14971C1CDCC067BA46024C7275 Moath
    public static String myCity,myYear="",myBD,myName;
//"https://mainnet.infura.io/v3/afbac1a223484d84a7784a133d1f2010"
    //https://rinkeby.infura.io/v3/203f4b27aa6a41c6958b6c8ff6f4d729
    public static Web3j   web3 = Web3jFactory.build(new HttpService("https://rinkeby.infura.io/v3/203f4b27aa6a41c6958b6c8ff6f4d729"));// defaults to http://localhost:8545/
    public static String privateKey="4274b048585600a5732d24d055e5ca2ed6df5311b895d4ed6c1aea0019881021"
            ,mainAddress="0xf21fe27e0721423577c46cd649970a15358a9726"
            ,voterAddress="0xd1dcbeb004033102e5d3b5ba78f6cf75d0fe2590"
            ,judgmentAddress="0x46b4468a764349f3b37147e0675bc5dcbd234671"
            ,candidateAddress="0xdb6cc19deb7abb73d062a328512e95659f306da9";
    public static Credentials credentials = Credentials.create(privateKey);

    public static ArrayList<SetGetMyVotes> arrayListMyVotes =new ArrayList<>();
    public static ArrayList<SetGetCandidatesInformations>arrayList=new ArrayList<>();

    public static Voters voters;
    public static Judgment judgment=null;
    public static Candidates candidates=null;
    public static MainContract mainContract=null;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Activity activity;
       public HelperCLass(Activity act)
       {
           activity=act;
       }
    public SharedPreferences getSharedPreferences()
    {
     if(sharedPreferences ==null) {
         sharedPreferences = activity.getSharedPreferences("MyShared", Context.MODE_PRIVATE);
     }
     return  sharedPreferences;
    }
    public SharedPreferences.Editor getEditor()
    {
        if(sharedPreferences ==null)
            sharedPreferences = activity.getSharedPreferences("MyShared", Context.MODE_PRIVATE);
        if(editor ==null)
        editor=sharedPreferences.edit();
        return editor;
    }
    public ProgressDialog getProgress(String title,String message)
    {
        progressDialog=new ProgressDialog(activity);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        return progressDialog;

    }



    public void keepScreenLight()
    {
        final PowerManager pm = (PowerManager)activity.getSystemService(Context.POWER_SERVICE);
        this.mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
        this.mWakeLock.acquire();
    }
    public void getLanguage()
    {
        String languageToLoad  = "en"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        activity.getResources().updateConfiguration(config,
                activity.getResources().getDisplayMetrics());
    }

    public static Credentials ChangeCredentials(String pk){
        credentials = Credentials.create(pk);

        return credentials;
    }

}
