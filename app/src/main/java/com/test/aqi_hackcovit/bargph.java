package com.test.aqi_hackcovit;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class bargph extends AppCompatActivity {

    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList barEntries;
    //TextView T7;
    public String aqi1,aqi2,aqi3,aqi4,aqi5,aqi6;
    int AQI1= 152 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bargph);
       // T7 = findViewById(R.id.t7);

        barChart = findViewById(R.id.barchar);

        getEntries();
        barDataSet = new BarDataSet(barEntries,"Data Set");
        barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);




        //JSON-1
        final RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://api.waqi.info/search/?token=ba874cb225ae40a7f27495bcf7a65ac5c5b7b2bb&keyword=India", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i =0; i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //Log.d("myapp", "The response is"+ response.getString("aqi"));
                        aqi1= jsonObject.getString("aqi");
                        //Toast.makeText(bargph.this,aqi1,Toast.LENGTH_LONG).show();
                        //T7.setText(aqi1);
                        //AQI1 = Integer.parseInt(T7.getText().toString());





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


        //JSON-2

        final RequestQueue reqquestQueue;
        reqquestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jjsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://api.waqi.info/search/?token=ba874cb225ae40a7f27495bcf7a65ac5c5b7b2bb&keyword=China", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i =0; i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //Log.d("myapp", "The response is"+ response.getString("aqi"));
                        aqi2= jsonObject.getString("aqi");


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


    private void getEntries()
    {

        barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1f,AQI1));
        barEntries.add(new BarEntry(2f,180));
        barEntries.add(new BarEntry(3f,200));
        barEntries.add(new BarEntry(4f,96));
        barEntries.add(new BarEntry(5f,215));
        barEntries.add(new BarEntry(6f,69));
    }
}
