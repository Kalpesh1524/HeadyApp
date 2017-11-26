package com.example.admin.volleywithheaders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by admin on 5/16/17.
 */

public class MyAdapterClass extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<MyPojo> list;


    public MyAdapterClass(Context context, List<MyPojo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.response_layout, parent, false);

        return new MyViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MyViewholder headerHolder = (MyViewholder) holder;

        MyPojo myPojo = list.get(position);
        headerHolder.CustomerID.setText(myPojo.getCustomerID());
        headerHolder.CompanyName.setText(myPojo.getCompanyName());
        headerHolder.ContactName.setText(myPojo.getContactName());
        headerHolder.ContactTitle.setText(myPojo.getContactTitle());
        headerHolder.Address.setText(myPojo.getAddress());
        headerHolder.City.setText(myPojo.getCity());
        headerHolder.Region.setText(myPojo.getRegion());
        headerHolder.PostalCode.setText(myPojo.getPostalCode());
        headerHolder.Country.setText(myPojo.getCountry());
        headerHolder.Phone.setText(myPojo.getPhone());
        headerHolder.Fax.setText(myPojo.getFax());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder
    {

        TextView CustomerID,CompanyName,ContactName,ContactTitle,Address,City,Region,PostalCode,Country,Phone,Fax;

        public MyViewholder(View itemView) {
            super(itemView);
            CustomerID = (TextView)itemView.findViewById(R.id.CustomerID);
            CompanyName = (TextView)itemView.findViewById(R.id.CompanyName);
            ContactName = (TextView)itemView.findViewById(R.id.ContactName);
            ContactTitle = (TextView)itemView.findViewById(R.id.ContactTitle);
            Address = (TextView)itemView.findViewById(R.id.Address);
            City = (TextView)itemView.findViewById(R.id.City);
            Region = (TextView)itemView.findViewById(R.id.Region);
            PostalCode = (TextView)itemView.findViewById(R.id.PostalCode);
            Country = (TextView)itemView.findViewById(R.id.Country);
            Phone = (TextView)itemView.findViewById(R.id.Phone);
            Fax = (TextView)itemView.findViewById(R.id.Fax);

        }
    }
}
