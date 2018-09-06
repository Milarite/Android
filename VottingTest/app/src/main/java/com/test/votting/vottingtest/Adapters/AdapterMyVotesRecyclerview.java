package com.test.votting.vottingtest.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.votting.vottingtest.HelperCLass;
import com.test.votting.vottingtest.Moduls.SetGetMyVotes;
import com.test.votting.vottingtest.R;

import java.util.ArrayList;

public class AdapterMyVotesRecyclerview extends RecyclerView.Adapter<AdapterMyVotesRecyclerview.MyRec> {

    AlertDialog alertDialog;
    AlertDialog.Builder builder;
    LayoutInflater inflater;
    ArrayList<SetGetMyVotes> arrayList=new ArrayList();
    public Activity act;

    public AdapterMyVotesRecyclerview(ArrayList<SetGetMyVotes> list, Activity context)
    {
        arrayList = list;
        inflater=LayoutInflater.from(context);
        act=context;
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
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder=new AlertDialog.Builder(act);
                builder.setTitle("Revoke vote");
                builder.setMessage("Are you sure you want to revoke this vote from "+arrayList.get(position).getName());
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        for(int i=0;i<HelperCLass.arrayList.size();i++)
                        {

                                if (HelperCLass.arrayList !=null && HelperCLass.arrayList.get(i).getCandidateNationalID()==(arrayList.get(position).getNationalID())) {
                                    HelperCLass.arrayList.get(i).setStatusVoted(0);
                                    arrayList.remove(position);
                                    notifyDataSetChanged();
                                    alertDialog.dismiss();
                                    break;
                                }
                        }

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
        TextView candidateName,candidateCampign,city,status,year;


        public MyRec(View v) {
            super(v);
            candidateName=(TextView)v.findViewById(R.id.candidateName);
            candidateCampign=(TextView)v.findViewById(R.id.candidateCampign);
            city=(TextView)v.findViewById(R.id.city);
            status=(TextView)v.findViewById(R.id.status);
            year=(TextView)v.findViewById(R.id.year);

            candidateImage=(ImageView)v.findViewById(R.id.candidateImage);
            delete=(ImageView)v.findViewById(R.id.delete);

        }
    }
}
