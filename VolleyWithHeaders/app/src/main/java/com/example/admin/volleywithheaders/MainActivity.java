package com.example.admin.volleywithheaders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView homeList;
    private RecyclerView.Adapter mAdapter;
    TextView txtResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // homeList = (RecyclerView)findViewById(R.id.recyclerview);
        txtResponse = (TextView)findViewById(R.id.response);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        homeList.setHasFixedSize(true);
//        homeList.setLayoutManager(mLayoutManager);
        getDetails();
    }

    private void getDetails()
    {
        String baseURL = "http://88.99.217.226:8000/api/customer";

        Log.e("ToolFragment",baseURL.toString());
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, baseURL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d("response", response);

                        if(response != null)
                        {
                            Gson gson  = new Gson();
//                            MyPojo toolRes = gson.fromJson(response,MyPojo.class);
//                            List<String> listData = new ArrayList<>();

                            Toast.makeText(MainActivity.this, "Reponse" + response , Toast.LENGTH_LONG).show();
                            txtResponse.setText(response);
////                            String Phone = toolRes.getPhone();
////                            String PostalCode = toolRes.getPostalCode();
////                            String Region = toolRes.getRegion();
////                            String ContactName = toolRes.getContactName();
////                            String Fax = toolRes.getFax();
////                            String Address = toolRes.getAddress();
////                            String CustomerID = toolRes.getCustomerID();
////                            String CompanyName = toolRes.getCompanyName();
////                            String Country = toolRes.getCountry();
////                            String City = toolRes.getCity();
////                            String ContactTitle = toolRes.getContactTitle();
////                            listData.add(Phone);
////                            mAdapter=new MyAdapterClass(MainActivity.this,listData);
//                            homeList.setAdapter(mAdapter);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "We are sorry, our server is inaccessible at present, please try again later.", Toast.LENGTH_SHORT).show();

                Log.d("ToolFragment", "Error: " + error.getMessage());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();

               // String creds = String.format("%s:%s","Username","Password");
                String credentials = "admin:admin";
                String auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.DEFAULT);
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("Authorization", auth);
                headers.put("lat", "20.2");
                headers.put("long", "70.2");
                return headers;
            }


        };
        queue.add(jsonObjReq);
    }
}
