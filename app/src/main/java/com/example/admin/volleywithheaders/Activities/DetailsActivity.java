package com.example.admin.volleywithheaders.Activities;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
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
import com.example.admin.volleywithheaders.Network.ConnectionDetector;
import com.example.admin.volleywithheaders.Network.ParserClass;
import com.example.admin.volleywithheaders.R;

/**
 * Created by Pankaj on 11/25/2017.
 */

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener{

    public RecyclerView homeList;
    private RecyclerView.Adapter mAdapter;
    TextView tv_resID,tv_resName, tv_resSubName;
    String id;
    SparseArray<String> result;

    LinearLayout llResponce, llProgressbar;
    Button bRetry;
    ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);
        llResponce = (LinearLayout) findViewById(R.id.llResponce);
        llProgressbar = (LinearLayout) findViewById(R.id.llProgressbar);
        bRetry = (Button) findViewById(R.id.bRetry);
        bRetry.setOnClickListener(this);
        tv_resID = (TextView) findViewById(R.id.tv_resID);
        tv_resName = (TextView) findViewById(R.id.tv_resName);
        tv_resSubName = (TextView) findViewById(R.id.tv_resSubName);
        id = PreferenceManager
                .getDefaultSharedPreferences(this).getString("id", "1");
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

    public void getDetails()
    {
        String baseURL = "https://stark-spire-93433.herokuapp.com/json";

        Log.e("ToolFragment",baseURL.toString());
        RequestQueue queue = Volley.newRequestQueue(DetailsActivity.this);
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, baseURL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d("response", response);

                        if(response != null)
                        {
                            result = ParserClass.parseDetailsData(response,id);

                            tv_resID.setText(result.get(1));
                            tv_resName.setText(result.get(2));
                            tv_resSubName.setText(result.get(3));
                            int size = result.size();
                            /*for (int s=4; s<=size; s++){
                                String color = result.get(s);
                                String size1 = result.get(s+1);
                                String price = result.get(s+2);
                            }*/

                            LinearLayout MainLL= (LinearLayout) findViewById(R.id.myLayoutId);
                            for(int i=4; i<=size; i++){
                                TextView text = new TextView(DetailsActivity.this);
                                text.setText("Varients Details " +"  " + result.get(i)); // <-- does it really compile without the + sign?
                                text.setTextSize(18);
                                text.setTextColor(getResources().getColor(R.color.black));
                                text.setGravity(Gravity.LEFT);
                               /* text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                                params.setMargins(10,20,30,20);
*/
                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                params.setMargins(10,2,10,0);
                                text.setLayoutParams(params);

                                MainLL.addView(text);
                            }
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

                Toast.makeText(DetailsActivity.this, "We are sorry, our server is inaccessible at present, please try again later.", Toast.LENGTH_SHORT).show();

                Log.d("ToolFragment", "Error: " + error.getMessage());
            }
        });
        queue.add(jsonObjReq);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bRetry:
                bRetry.setVisibility(View.GONE);
                llProgressbar.setVisibility(View.VISIBLE);
                llResponce.setVisibility(View.INVISIBLE);
                getDetails();
                break;
        }
    }
}
