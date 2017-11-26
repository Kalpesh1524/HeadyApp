package com.example.admin.volleywithheaders.Adapters;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.volleywithheaders.Activities.DetailsActivity;
import com.example.admin.volleywithheaders.PojoClass.MyViewCount;
import com.example.admin.volleywithheaders.R;

import java.util.List;

/**
 * Created by admin on 5/16/17.
 */

public class ViewCountAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<MyViewCount> viewCount;
    private RecyclerView.Adapter mAdapter;

    public ViewCountAdapter(Context context, List<MyViewCount> viewCount) {
        this.context = context;
        this.viewCount = viewCount;
    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.response_count, parent, false);

        return new MyViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyViewholder headerHolder = (MyViewholder) holder;

        MyViewCount vCount = viewCount.get(position);
        headerHolder.raankId.setText(vCount.getId());
        headerHolder.count.setText(vCount.getView_count());

        ((MyViewholder) holder).ll_resList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyViewCount myRank = viewCount.get(position);
                //headerHolder.count.setText(myRank.getId());
                String Id = myRank.getId();
                String id= Id;

                Intent intent = new Intent(context, DetailsActivity.class);
                PreferenceManager.getDefaultSharedPreferences(context).edit().putString("id", id).commit();
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return viewCount.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder
    {

        TextView raankId, count, tv_count;
        LinearLayout ll_resList;

        public MyViewholder(View itemView) {
            super(itemView);
            raankId = (TextView)itemView.findViewById(R.id.raankId);
            count = (TextView)itemView.findViewById(R.id.count);
            tv_count = (TextView)itemView.findViewById(R.id.tv_count);
            ll_resList = (LinearLayout) itemView.findViewById(R.id.ll_resList);
            tv_count.setText("View Count");
        }
    }
}
