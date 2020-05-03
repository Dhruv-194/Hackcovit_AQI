package com.test.aqi_hackcovit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Info extends AppCompatActivity {
    public TextView ClickTxt;
    public TextView ClickTxt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ClickTxt = findViewById(R.id.txtclick);
        ClickTxt2 = findViewById(R.id.txtclick2);

        ClickTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Info.this, Web2.class);
                startActivity(intent);
            }
        });

        ClickTxt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Info.this, Web3.class);
                startActivity(intent);
            }
        });
    }
}
