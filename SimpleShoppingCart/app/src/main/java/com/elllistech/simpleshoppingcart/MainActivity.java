package com.elllistech.simpleshoppingcart;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton
                pcButton = (ImageButton) findViewById(R.id.pcServicesButton);
        ImageButton
                phoneButton = (ImageButton) findViewById(R.id.phoneAppButton);
        ImageButton
                webButton = (ImageButton) findViewById(R.id.webAppButton);
        ImageButton
                basicButton = (ImageButton) findViewById(R.id.basicPageButton);
        ImageButton
                serverButton = (ImageButton) findViewById(R.id.serverDBButton);

        final Context context = this;

        pcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, ServicesDescriptionActivity.class));
            }
        });

        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, ServicesDescriptionActivity.class));
            }
        });

        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, ServicesDescriptionActivity.class));
            }
        });

        basicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, ServicesDescriptionActivity.class));
            }
        });

        serverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, ServicesDescriptionActivity.class));
            }
        });
    }


}
