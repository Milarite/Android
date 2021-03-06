package com.test.votting.vottingtest;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.test.votting.vottingtest.Fragments.CandidatesFramgnet;
import com.test.votting.vottingtest.Fragments.MyVotesFragment;
import com.test.votting.vottingtest.Fragments.SettingFragment;
import com.test.votting.vottingtest.SolCode.MainContract;
import com.test.votting.vottingtest.SolCode.Voters;

import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

public class Main2Activity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    BottomNavigationView navigation;
    HelperCLass helperCLass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        fragmentManager=getFragmentManager();
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_home);
        navigation.getMenu().getItem(1).setChecked(true);
        helperCLass=new HelperCLass(Main2Activity.this);
        helperCLass.keepScreenLight();

        helperCLass.getLanguage(helperCLass.getSharedPreferences().getString("Lang",""));
        changeFragments(new CandidatesFramgnet());
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()==R.id.navigation_home  && !navigation.getMenu().getItem(1).isChecked() )
                {
                    navigation.getMenu().getItem(1).setChecked(true);
                    changeFragments(new CandidatesFramgnet());
                }
                else
                    if(item.getItemId()==R.id.navigation_history  && !navigation.getMenu().getItem(0).isChecked())
                    {

                        navigation.getMenu().getItem(0).setChecked(true);

                        changeFragments(new MyVotesFragment());
                    }

                    else
                    if(item.getItemId()==R.id.navigation_setting  && !navigation.getMenu().getItem(2).isChecked())
                    {

                        navigation.getMenu().getItem(2).setChecked(true);

                        changeFragments(new SettingFragment());
                    }
                return false;
            }
        });

    }

    public void changeFragments(Fragment fragment)
    {
        transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.mainFramlayout,fragment);
        transaction.commit();
       // return  transaction;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HelperCLass.mWakeLock.release();
    }
}
