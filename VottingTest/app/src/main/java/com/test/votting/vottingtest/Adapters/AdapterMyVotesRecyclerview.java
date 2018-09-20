package com.test.votting.vottingtest.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.test.votting.vottingtest.HelperCLass;
import com.test.votting.vottingtest.Main2Activity;
import com.test.votting.vottingtest.Moduls.SetGetMyVotes;
import com.test.votting.vottingtest.R;

import java.util.ArrayList;

public class AdapterMyVotesRecyclerview extends RecyclerView.Adapter<AdapterMyVotesRecyclerview.MyRec> {

    AlertDialog alertDialog;
    AlertDialog.Builder builder;
    LayoutInflater inflater;
    ArrayList<SetGetMyVotes> arrayList=new ArrayList();
    HelperCLass helperCLass;
    public Activity act;
    ProgressDialog progressDialog;
    int publicPosition;

    public AdapterMyVotesRecyclerview(ArrayList<SetGetMyVotes> list, Activity context)
    {
        arrayList = list;
        inflater=LayoutInflater.from(context);
        act=context;
        helperCLass=new HelperCLass(act);

    }
    @Override
    public AdapterMyVotesRecyclerview.MyRec onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v=  inflater.inflate(R.layout.adapter_my_votes_recyclerview,parent,false);
        MyRec obj=new MyRec(v);
        return obj;

    }
    @Override
    public void onBindViewHolder(final AdapterMyVotesRecyclerview.MyRec holder, final int position)
    {
        holder.candidateName.setText(arrayList.get(position).getName());
        holder.candidateCampign.setText(arrayList.get(position).getCampaign());
        holder.city.setText(arrayList.get(position).getCity());
        holder.status.setText(arrayList.get(position).getStatus());
        holder.year.setText(arrayList.get(position).getYear());
        holder.address.setText(arrayList.get(position).getNationalID());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder=new AlertDialog.Builder(act);
                builder.setTitle("Revoke vote");
                builder.setMessage("Are you sure you want to revoke this vote from "+arrayList.get(position).getName());
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        progressDialog=helperCLass.getProgress("Revoking","Please wait");
                        progressDialog.show();
                        publicPosition=position;
                        LongOperationRevoke longOperation=new LongOperationRevoke();
                        longOperation.execute("");


                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog=builder.create();
                alertDialog.show();

            }
        });

    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    class MyRec extends RecyclerView.ViewHolder
    {
        ImageView delete,candidateImage;
        TextView candidateName,candidateCampign,city,status,year,address;


        public MyRec(View v) {
            super(v);
            candidateName=(TextView)v.findViewById(R.id.candidateName);
            candidateCampign=(TextView)v.findViewById(R.id.candidateCampign);
            city=(TextView)v.findViewById(R.id.city);
            status=(TextView)v.findViewById(R.id.status);
            year=(TextView)v.findViewById(R.id.year);
            address=(TextView)v.findViewById(R.id.address);


            candidateImage=(ImageView)v.findViewById(R.id.candidateImage);
            delete=(ImageView)v.findViewById(R.id.delete);

        }
    }
    class LongOperationRevoke extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//            Toast.makeText(act, arrayList.get(publicPosition).getNationalID(), Toast.LENGTH_SHORT).show();
            arrayList.remove(publicPosition);
            notifyDataSetChanged();
            progressDialog.dismiss();

        }

        @Override
        protected String doInBackground(String... params) {

            try {

                HelperCLass.mainContract.revokeMyVote(helperCLass.getSharedPreferences().getString("MyAddress",""),arrayList.get(publicPosition).getNationalID()).send();
                HelperCLass.mainContract.removeTxtHashToCandidate(arrayList.get(publicPosition).getNationalID(),arrayList.get(publicPosition).getTxtHash()).send();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
}



    }
