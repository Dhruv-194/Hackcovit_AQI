package com.test.aqi_hackcovit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Search extends AppCompatActivity {

    public static TextView Searchtxt;
    public static TextView ShowAQI;
    public static Button Search;
    public static Button GoBar;
    public String txt;
    public String AQItxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Search = findViewById(R.id.search);
        ShowAQI = findViewById(R.id.txtaqi);
        Searchtxt = findViewById(R.id.searchtxt);
        GoBar = findViewById(R.id.bu3);

        GoBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search.this, bargph.class);
                intent.putExtra("Search",txt);
                startActivity(intent);
            }
        });

        final RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt = Searchtxt.getText().toString();



                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                        "https://api.waqi.info/search/?token=ba874cb225ae40a7f27495bcf7a65ac5c5b7b2bb&keyword="+txt, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i =0; i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                //Log.d("myapp", "The response is"+ response.getString("aqi"));
                                AQItxt= jsonObject.getString("aqi");

                                ShowAQI.setText(AQItxt);
                            }

                            // Data.setText(death);
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener()
                { @Override
                public void onErrorResponse(VolleyError error){
                    Log.d("myapp", "something went wrond");

                }
                });

                requestQueue.add(jsonObjectRequest);
                    }

        });
    }
}
