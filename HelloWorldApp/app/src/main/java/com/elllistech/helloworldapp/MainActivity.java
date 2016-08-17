package com.elllistech.helloworldapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonName1 = (Button)findViewById(R.id.btnName1);
        Button buttonName2 = (Button)findViewById(R.id.btnName2);
        final TextView textHello = (TextView)findViewById(R.id.textViewHelloWorld);

        buttonName1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textHello.setTextColor(Color.parseColor("#ffffff"));
                textHello.setTextSize(22);
            }
        });

        buttonName2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textHello.setTextColor(Color.parseColor("#000000"));
                textHello.setTextSize(14);
            }
        });
    }
}
