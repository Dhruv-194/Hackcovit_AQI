package com.test.aqi_hackcovit;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
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
    ArrayList<String> barLabels ;
    TextView T7;
    Button checkbt;
    int n,m,l;
    public String aqi1,aqi2,aqi3,aqi4,aqi5,aqi6;
    int AQI1= 152 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bargph);
       T7 = findViewById(R.id.t7);
checkbt=findViewById(R.id.checkBT);
T7.setText(""+getIntent().getStringExtra("Search"));

    aqi4 = T7.getText().toString();





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
                        n = aqi1.isEmpty()?0:Integer.parseInt(aqi1);
                        //int check = Integer.parseInt(aqi1);
                     /*  getEntries();
                        barChart = findViewById(R.id.barchar);
                        barDataSet = new BarDataSet(barEntries,"Data Set");
                        barData = new BarData(barDataSet);
                        barChart.setData(barData);
                        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                        barDataSet.setValueTextColor(Color.BLACK);
                        barDataSet.setValueTextSize(16f);
                        barChart.invalidate();*/
                     json2(n);







                        Toast.makeText(bargph.this,aqi1,Toast.LENGTH_LONG).show();
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






    }


    public void json2(final Integer n){
        //JSON-2
        final RequestQueue rrequestQueue;
        rrequestQueue = Volley.newRequestQueue(this);


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
                        m = aqi2.isEmpty()?0:Integer.parseInt(aqi2);
                        //int check = Integer.parseInt(aqi1);
                       /* getEntries(n,m);
                        barChart = findViewById(R.id.barchar);
                        barDataSet = new BarDataSet(barEntries,"Data Set");
                        barData = new BarData(barDataSet);
                        barChart.setData(barData);
                        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                        barDataSet.setValueTextColor(Color.BLACK);
                        barDataSet.setValueTextSize(16f);
                        barChart.invalidate();*/
                       json3(n,m);



                        Toast.makeText(bargph.this,aqi2,Toast.LENGTH_LONG).show();
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

        rrequestQueue.add(jjsonObjectRequest);}




    public void json3(final Integer n, final Integer m){
        //JSON-3
        final RequestQueue rrrequestQueue;
        rrrequestQueue = Volley.newRequestQueue(this);


        JsonObjectRequest jjjsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://api.waqi.info/search/?token=ba874cb225ae40a7f27495bcf7a65ac5c5b7b2bb&keyword="+aqi4, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i =0; i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //Log.d("myapp", "The response is"+ response.getString("aqi"));
                        aqi3= jsonObject.getString("aqi");
                        l = aqi3.isEmpty()?0:Integer.parseInt(aqi3);
                        //int check = Integer.parseInt(aqi1);
                        getEntries(n,m,l);
                       // getLabel();
                        barChart = findViewById(R.id.barchar);
                        barDataSet = new BarDataSet(barEntries,"Data Set");
                        barData = new BarData(barDataSet);
                        barChart.setData(barData);
                        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                        barDataSet.setValueTextColor(Color.BLACK);
                        barDataSet.setValueTextSize(16f);
                        barChart.invalidate();
                       /* final ArrayList<String> xAxisLabel = new ArrayList<>();
                        xAxisLabel.add("Mon");
                        xAxisLabel.add("Tue");
                        xAxisLabel.add("Wed");
                        xAxisLabel.add("Thu");
                        xAxisLabel.add("Fri");
                        xAxisLabel.add("Sat");
                        XAxis xAxis = barChart.getXAxis();
                        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);

                        ValueFormatter formatter = new ValueFormatter() {


                            @Override
                            public String getFormattedValue(float value) {
                                return xAxisLabel.get((int) value);
                            }
                        };

                        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
                        xAxis.setValueFormatter(formatter);*/



                        Toast.makeText(bargph.this,aqi3,Toast.LENGTH_LONG).show();
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

        rrrequestQueue.add(jjjsonObjectRequest);}












    public void getEntries(Integer n,Integer m, Integer l)
    {

        barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1f,n));
        barEntries.add(new BarEntry(2f,m));
        barEntries.add(new BarEntry(3f,l));
        barEntries.add(new BarEntry(4f,96));
        barEntries.add(new BarEntry(5f,215));
        barEntries.add(new BarEntry(6f,69));
    }
  /*  public void getLabel()
    {

        final ArrayList<String> xAxisLabel = new ArrayList<>();
        xAxisLabel.add("Mon");
        xAxisLabel.add("Tue");
        xAxisLabel.add("Wed");
        xAxisLabel.add("Thu");
        xAxisLabel.add("Fri");
        xAxisLabel.add("Sat");
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);

        ValueFormatter formatter = new ValueFormatter() {


            @Override
            public String getFormattedValue(float value) {
                return xAxisLabel.get((int) value);
            }
        };

        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);
    }*/
}
