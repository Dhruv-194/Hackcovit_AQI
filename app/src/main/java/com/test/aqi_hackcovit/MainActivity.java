package com.test.aqi_hackcovit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.test.aqi_hackcovit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Animation topanim;

    public static ImageButton buttonc;
    public static  Button Butt;
    public static Button BUtt3;
    public static TextView Tc;
    public String Ct;
    public double lon;
    public double lat;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topanim = AnimationUtils.loadAnimation(this, R.anim.topanim);

        buttonc = (ImageButton) findViewById(R.id.bt);
        Tc = (TextView)findViewById(R.id.ct);
        Butt = findViewById(R.id.butt2);
        BUtt3 = findViewById(R.id.butt3);

        buttonc.setAnimation(topanim);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        Butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Search.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slideinright, R.anim.slideoutleft);
            }
        });

        BUtt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, Info.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slideinleft, R.anim.slideoutright);
            }
        });



        final RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

        buttonc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                    getLocation();
                }
                else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
                }



                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                        "https://api.waqi.info/feed/geo:"+lat+";"+lon+"/?token=ba874cb225ae40a7f27495bcf7a65ac5c5b7b2bb", null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonobj = response.getJSONObject("data");
                            Ct = jsonobj.getString("aqi");
                            Tc.setText(Ct);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener()
                { @Override
                public void onErrorResponse(VolleyError error){
                    Log.d("myapp", "something went wrong");

                }
                });

                requestQueue.add(jsonObjectRequest);


                Toast.makeText(MainActivity.this,lat+" --"+lon ,Toast.LENGTH_LONG).show();
            }

        });


    }
    private  void getLocation()
    {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location!=null)
                {
                    Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                    try {
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

                        lon = addresses.get(0).getLongitude();
                        lat = addresses.get(0).getLatitude();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
