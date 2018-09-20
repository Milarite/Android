package com.test.votting.vottingtest.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.test.votting.vottingtest.HelperCLass;
import com.test.votting.vottingtest.Moduls.SetGetCandidatesInformations;
import com.test.votting.vottingtest.Moduls.SetGetMyVotes;
import com.test.votting.vottingtest.R;

import org.web3j.protocol.core.methods.request.Transaction;

import java.math.BigInteger;
import java.security.PublicKey;
import java.util.ArrayList;

public class AdapterCandidatesRecyclerview  extends RecyclerView.Adapter<AdapterCandidatesRecyclerview.MyRec> {
    AlertDialog alertDialog;
    AlertDialog.Builder builder;
    LayoutInflater inflater;
    SetGetMyVotes setGetMyVotes;
    HelperCLass helperCLass;
    int publicPosition;
    ArrayList<SetGetCandidatesInformations> arrayList = new ArrayList();
    public Activity act;
    String grantVoteStauts;
    String transactionHash;
    ProgressDialog progressDialog;

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

                Log.d("MyAddressLL",helperCLass.getSharedPreferences().getString("MyAddress", ""));

            //    if (arrayList.get(position).getStatusVoted() == 0) {
                    builder = new AlertDialog.Builder(act);
                    builder.setTitle("Grant vote");
                    builder.setMessage("Are you sure you want to grant your vote to " + arrayList.get(position).getName());
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            publicPosition=position;
                            progressDialog=  helperCLass.getProgress("Granting","Please wait");
                            progressDialog.show();
                            LongOperation longOperation=new LongOperation();
                            longOperation.execute("");
            }
        });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
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

        }
    }
    class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
          //  Toast.makeText(act, helperCLass.getSharedPreferences().getString("MyAddress", ""), Toast.LENGTH_SHORT).show();

            if(grantVoteStauts.equals("Done"))
            {
                LongOperationGrantVote longOperationGrantVote=new LongOperationGrantVote();
                longOperationGrantVote.execute("");
                Log.d("Semsem2",grantVoteStauts);


            }
            else {

                Log.d("Semsem",grantVoteStauts);
                Toast.makeText(act, grantVoteStauts, Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
            Log.e("5ara2",helperCLass.getSharedPreferences().getString("MyAddress", ""));

            Log.e("5ara",arrayList.get(publicPosition).getCandidateNationalID());
        }

        @Override
        protected String doInBackground(String... params) {
            try {

                    grantVoteStauts=HelperCLass.mainContract.checkIfVoted (
                        helperCLass.getSharedPreferences().getString("MyAddress", ""),
                        arrayList.get(publicPosition).getCandidateNationalID()).send();


            } catch (Exception e) {
                e.printStackTrace();
            }


//
            return null;

        }

    }



    class LongOperationGrantVote extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            LongOperationSentTxHash longOperationSentTxHash=new LongOperationSentTxHash();
            longOperationSentTxHash.execute("");
        }
        @Override
        protected String doInBackground(String... params) {

            try {
                 transactionHash=HelperCLass.mainContract.grantYourVote(helperCLass.getSharedPreferences().getString("MyAddress",""),arrayList.get(publicPosition).getCandidateNationalID()).send().getTransactionHash();
            } catch (Exception e) {
                e.printStackTrace();
            }


//
            return null;

        }
    }





    class LongOperationSentTxHash extends AsyncTask<String, Void, String> {


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();
        }
        @Override
        protected String doInBackground(String... params) {

            try {
                String transactionHash=HelperCLass.mainContract.grantYourVote(helperCLass.getSharedPreferences().getString("MyAddress",""),arrayList.get(publicPosition).getCandidateNationalID()).send().getTransactionHash();
            } catch (Exception e) {
                e.printStackTrace();
            }


//
            return null;

        }
    }




    }

