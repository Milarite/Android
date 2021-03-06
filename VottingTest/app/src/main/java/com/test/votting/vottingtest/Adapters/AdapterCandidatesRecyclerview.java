package com.test.votting.vottingtest.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.test.votting.vottingtest.HelperCLass;
import com.test.votting.vottingtest.InternetConnection;
import com.test.votting.vottingtest.Moduls.SetGetCandidatesInformations;
import com.test.votting.vottingtest.Moduls.SetGetMyVotes;
import com.test.votting.vottingtest.R;

import org.web3j.protocol.core.methods.request.Transaction;

import java.math.BigInteger;
import java.security.PublicKey;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AdapterCandidatesRecyclerview  extends RecyclerView.Adapter<AdapterCandidatesRecyclerview.MyRec> {
    long unixSecondsDateTime;
    boolean checkDateTimeAvailable;
    AlertDialog alertDialog;
    AlertDialog.Builder builder;
    LayoutInflater inflater;
    HelperCLass helperCLass;
    int publicPosition;
    String currentTime,currentDate;
    ArrayList<SetGetCandidatesInformations> arrayList = new ArrayList();
    public Activity act;
    String grantVoteStauts;
    String transactionHash;
    ProgressDialog progressDialog;
   // public AdapterCallbBack adapterCallbBack;

    public AdapterCandidatesRecyclerview(ArrayList<SetGetCandidatesInformations> list, Activity context) {
        arrayList = list;
        inflater = LayoutInflater.from(context);
        act = context;
        helperCLass = new HelperCLass(context);
    }

    @Override
    public AdapterCandidatesRecyclerview.MyRec onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.adapter_candidates_recyclerview, parent, false);
        MyRec obj = new MyRec(v);
        return obj;

    }

    @Override
    public void onBindViewHolder(final MyRec holder, final int position) {
        holder.candidateName.setText(arrayList.get(position).getName());
        holder.candidateCampign.setText(arrayList.get(position).getCampaign());
        holder.city.setText(arrayList.get(position).getCity());
//        if (arrayList.get(position).getStatusVoted() == 0)
//            holder.grantYourVote.setText("Grant Your Vote");
//        else
//            holder.grantYourVote.setText("Voted");
        holder.grantYourVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("MyAddressLL", helperCLass.getSharedPreferences().getString("MyAddress", ""));

                //    if (arrayList.get(position).getStatusVoted() == 0) {
                builder = new AlertDialog.Builder(act);
                builder.setTitle(act.getResources().getString(R.string.Vote));
                builder.setMessage(act.getResources().getString(R.string.Areyousureyouwanttovoteto) + arrayList.get(position).getName());
                builder.setPositiveButton(act.getResources().getString(R.string.Yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if( InternetConnection.ifConnect(act)) {
                            progressDialog = helperCLass.getProgress(act.getResources().getString(R.string.Voting), act.getResources().getString(R.string.Pleasewait));
                            progressDialog.show();
                            publicPosition = position;
                            LongOperation longOperation = new LongOperation();
                            longOperation.execute("");
                        }
                        else
                            Toast.makeText(act, act.getResources().getString(R.string.Nointernetconnection), Toast.LENGTH_SHORT).show();

                    }
                });

                builder.setNegativeButton(act.getResources().getString(R.string.No), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();


                //     }

            }
        });
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyRec extends RecyclerView.ViewHolder {
        TextView candidateName, candidateCampign, city, grantYourVote;
        ImageView candidateImage;

        public MyRec(View v) {
            super(v);
            candidateName = (TextView) v.findViewById(R.id.candidateName);
            candidateCampign = (TextView) v.findViewById(R.id.candidateCampign);
            city = (TextView) v.findViewById(R.id.city);
            grantYourVote = (TextView) v.findViewById(R.id.grantYourVote);

            candidateImage = (ImageView) v.findViewById(R.id.candidateImage);

            if(helperCLass.getSharedPreferences().getString("Lang","").equals("ar")) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.LEFT;
                params.leftMargin = 10;
                grantYourVote.setLayoutParams(params);
            }
        }
    }

    class LongOperation extends AsyncTask<String, Void, String> {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);





                try {
                    Log.e("TTT", String.valueOf(HelperCLass.simpleTimeFormat.parse(HelperCLass.toTime)));
                    Log.e("FFF", String.valueOf(HelperCLass.simpleTimeFormat.parse(HelperCLass.fromTime)));
                    Log.e("CCC", String.valueOf(HelperCLass.simpleTimeFormat.parse(currentTime)));


                } catch (ParseException e) {
                    e.printStackTrace();
                }

            if (checkDateTimeAvailable == true)
            {
                if (grantVoteStauts.equals("Done")) {
                    LongOperationGrantVote longOperationGrantVote = new LongOperationGrantVote();
                    longOperationGrantVote.execute("");


                } else {
                    Toast.makeText(act, grantVoteStauts, Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
        }

        else
            {
                Toast.makeText(act, act.getResources().getString(R.string.TimeOut), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            try {

             //   if(HelperCLass.mainContract.getCurrentTime())


                unixSecondsDateTime = Long.parseLong(String.valueOf(HelperCLass.mainContract.getCurrentTime().send()));
                Date dateTime = new Date(unixSecondsDateTime*1000L);
                currentTime=HelperCLass.simpleTimeFormat.format(dateTime);
                currentDate=HelperCLass.simpleDateFormat.format(dateTime);

                /* ........................................... */

//                fromDate=HelperCLass.mainContract.getStartDate().send();
//                times=HelperCLass.mainContract.getPeriod().send().split("-");
//                fromTime=times[0];
//                toTime=times[1];

                /* ........................................... */
//                Long diff1=HelperCLass.simpleTimeFormat.parse( toTime).getTime()-HelperCLass.simpleTimeFormat.parse( currentTime).getTime();
//                Long diff2=HelperCLass.simpleTimeFormat.parse( toTime).getTime()-HelperCLass.simpleTimeFormat.parse( currentTime).getTime();

//                 diff1=HelperCLass.simpleTimeFormat.parse( toTime).getTime() - HelperCLass.simpleTimeFormat.parse( currentTime).getTime();
//                 diff2=HelperCLass.simpleTimeFormat.parse( currentTime).getTime() - HelperCLass.simpleTimeFormat.parse( fromTime).getTime();

//                long diff1 = HelperCLass.simpleTimeFormat.parse(toTime).getTime() - HelperCLass.simpleTimeFormat.parse(currentTime).getTime();
//
//                long diff2 = HelperCLass.simpleTimeFormat.parse(currentTime).getTime() - HelperCLass.simpleTimeFormat.parse(fromTime).getTime();

//                if(currentDate.equals(fromDate)
//                               &&
//                               HelperCLass.simpleTimeFormat.parse( currentTime).before(HelperCLass.simpleTimeFormat.parse(toTime))
//                               &&
//                               HelperCLass.simpleTimeFormat.parse( currentTime).after(HelperCLass.simpleTimeFormat.parse(fromTime)))

                if(!HelperCLass.threshouldFlag)
                {
                    // / HelperCLass.mainContract.getNumberOfVoters().send()
                    int totalVotes=Integer.parseInt(String.valueOf(HelperCLass.mainContract.getTotalVotes().send()));
                    int totalVoters=Integer.parseInt(String.valueOf(HelperCLass.mainContract.getNumberOfVoters().send()));
                    if(Integer.parseInt( HelperCLass.mainContract.getPercentageOfVoters().send())/100
                            <= totalVotes/totalVoters)
                    {
                        checkDate();
                    }
                }
                else
                    checkDate();





            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Zift",e.getMessage());
            }


//
            return null;

        }

    }


    class LongOperationGrantVote extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            LongOperationSendTxHashVoter longOperationSentTxHash = new LongOperationSendTxHashVoter();
            longOperationSentTxHash.execute("");
         //   adapterCallbBack.cellAction();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                transactionHash = HelperCLass.mainContract.grantYourVote(helperCLass.getSharedPreferences().getString("MyAddress", ""), arrayList.get(publicPosition).getCandidateNationalID()).send().getTransactionHash();
            } catch (Exception e) {
                e.printStackTrace();
            }


//
            return null;

        }
    }


    class LongOperationSendTxHashVoter extends AsyncTask<String, Void, String> {


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();

            builder=new AlertDialog.Builder(act);
            builder.setTitle(act.getResources().getString(R.string.Successful));
            builder.setMessage(act.getResources().getString(R.string.Youhavebeenvoted));
            builder.setCancelable(false);
            builder.setPositiveButton(act.getResources().getString(R.string.Close), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                }
            });
            alertDialog=builder.create();
            alertDialog.show();
            //Rajai
//            LongOperationSendTxHashCandidate longOperationSendTxHashCandidate = new LongOperationSendTxHashCandidate();
//            longOperationSendTxHashCandidate.execute("");
        }

        @Override
        protected String doInBackground(String... params) {

            try {

                HelperCLass.mainContract.addTxtHashVoter(helperCLass.getSharedPreferences().getString("MyAddress", "")
                        , transactionHash, arrayList.get(publicPosition).getCandidateNationalID()).send();
                HelperCLass.mainContract.addTxtHashToCandidate(arrayList.get(publicPosition).getCandidateNationalID(), transactionHash).send();

            } catch (Exception e) {
                e.printStackTrace();
            }


//
            return null;

        }
    }




    public void checkDate()  {
            try {
                if(currentDate.equals(HelperCLass.fromDate)
                        &&
                        HelperCLass.simpleTimeFormat.parse(HelperCLass.toTime).after( HelperCLass.simpleTimeFormat.parse( currentTime))
                        &&
                        HelperCLass.simpleTimeFormat.parse(currentTime).after(  HelperCLass.simpleTimeFormat.parse(HelperCLass.fromTime))) {

                    grantVoteStauts = HelperCLass.mainContract.checkIfVoted(
                            helperCLass.getSharedPreferences().getString("MyAddress", ""),
                            arrayList.get(publicPosition).getCandidateNationalID()).send();
                    checkDateTimeAvailable = true;
                }
                else {

                    checkDateTimeAvailable = false;
                }
            }

            catch (Exception e) {
                e.printStackTrace();
            }



        }

//        interface AdapterCallbBack{
//        void cellAction();
//        }
}

