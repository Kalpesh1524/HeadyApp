package com.example.admin.volleywithheaders.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.volleywithheaders.Activities.OrderCountActivity;
import com.example.admin.volleywithheaders.Activities.ShareCountActivity;
import com.example.admin.volleywithheaders.Activities.ViewCountActivity;
import com.example.admin.volleywithheaders.PojoClass.MyRankings;
import com.example.admin.volleywithheaders.R;

import java.util.List;

/**
 * Created by admin on 5/16/17.
 */

public class MyAdapterClass extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<MyRankings> rank;
    private RecyclerView.Adapter mAdapter;

    public MyAdapterClass(Context context, List<MyRankings> rank) {
        this.context = context;
        this.rank = rank;
    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.response_layout, parent, false);

        return new MyViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyViewholder headerHolder = (MyViewholder) holder;

        MyRankings myPojo = rank.get(position);
        headerHolder.CustomerID.setText(myPojo.getRanking());

        ((MyViewholder) holder).CustomerID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyRankings myRank = rank.get(position);

                headerHolder.CustomerID.setText(myRank.getRanking());
                String Id = myRank.getRanking();
                String id= Id;
                switch (position){
                    case 0:
                        Intent intent = new Intent(context, ViewCountActivity.class);
                        //PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean(Fitness_Constants.isAltitude, isAltitude).commit();
                        context.startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(context, OrderCountActivity.class);
                        //PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean(Fitness_Constants.isAltitude, isAltitude).commit();
                        context.startActivity(intent1);
                        break;
                    case  2:
                        Intent intent2 = new Intent(context, ShareCountActivity.class);
                        //PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean(Fitness_Constants.isAltitude, isAltitude).commit();
                        context.startActivity(intent2);
                        break;
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return rank.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder
    {

        TextView CustomerID;

        public MyViewholder(View itemView) {
            super(itemView);
            CustomerID = (TextView)itemView.findViewById(R.id.CustomerID);

        }
    }
}
