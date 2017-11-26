package com.example.admin.volleywithheaders.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.volleywithheaders.Adapters.ShareCountAdapter;
import com.example.admin.volleywithheaders.Network.ConnectionDetector;
import com.example.admin.volleywithheaders.Network.ParserClass;
import com.example.admin.volleywithheaders.PojoClass.MyShareCount;
import com.example.admin.volleywithheaders.R;

import java.util.List;

/**
 * Created by Pankaj on 11/25/2017.
 */

public class ShareCountActivity extends AppCompatActivity implements View.OnClickListener{

    public RecyclerView homeList;
    private RecyclerView.Adapter mAdapter;
    TextView response;
    List<MyShareCount> gamesList;
    LinearLayout llResponce, llProgressbar;
    Button bRetry;
    ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llResponce = (LinearLayout) findViewById(R.id.llResponce);
        llProgressbar = (LinearLayout) findViewById(R.id.llProgressbar);
        bRetry = (Button) findViewById(R.id.bRetry);
        bRetry.setOnClickListener(this);

        homeList = (RecyclerView) findViewById(R.id.recyclerview);
        response = (TextView) findViewById(R.id.response);
        response.setText("MOST SHARED PRODUCT");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        homeList.setHasFixedSize(true);
        homeList.setLayoutManager(mLayoutManager);
        cd = new ConnectionDetector(this);

        try {
            if (cd.isConnectingToInternet()) {
                getDetails();
            } else {
                bRetry.setVisibility(View.VISIBLE);
                llProgressbar.setVisibility(View.GONE);
                Toast.makeText(this, "Network Fail !", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void getDetails() {
        String baseURL = "https://stark-spire-93433.herokuapp.com/json";

        Log.e("ToolFragment", baseURL.toString());
        RequestQueue queue = Volley.newRequestQueue(ShareCountActivity.this);
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, baseURL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d("response", response);

                        if (response != null) {
                            gamesList = ParserClass.parseShareData(response);

                            mAdapter = new ShareCountAdapter(ShareCountActivity.this, gamesList);
                            homeList.setAdapter(mAdapter);
                            llProgressbar.setVisibility(View.INVISIBLE);
                            bRetry.setVisibility(View.INVISIBLE);
                            llResponce.setVisibility(View.VISIBLE);

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                llProgressbar.setVisibility(View.INVISIBLE);
                bRetry.setVisibility(View.VISIBLE);
                llResponce.setVisibility(View.INVISIBLE);

                Toast.makeText(ShareCountActivity.this, "We are sorry, our server is inaccessible at present, please try again later.", Toast.LENGTH_SHORT).show();

                Log.d("ToolFragment", "Error: " + error.getMessage());
            }
        });
        queue.add(jsonObjReq);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bRetry:
                bRetry.setVisibility(View.GONE);
                llProgressbar.setVisibility(View.VISIBLE);
                llResponce.setVisibility(View.INVISIBLE);
                getDetails();
                break;
        }
    }
}