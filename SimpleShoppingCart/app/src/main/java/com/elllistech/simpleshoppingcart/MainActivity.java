package com.elllistech.simpleshoppingcart;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageButton
                pcButton = (ImageButton) findViewById(R.id.pcServicesButton);
        ImageButton
                phoneButton = (ImageButton) findViewById(R.id.phoneAppButton);
        ImageButton
                webButton = (ImageButton) findViewById(R.id.webAppButton);
        ImageButton
                basicButton = (ImageButton) findViewById(R.id.basicPageButton);
        ImageButton
                serverButton = (ImageButton) findViewById(R.id.serverDBButton);
        final String
                pcServiceTitle = getResources().getString(R.string.pc_page_title),
                pcServiceText = getResources().getString(R.string.pc_text_description),
                pcServiceValue = getResources().getString(R.string.pc_service_price),
                phoneAppTitle = getResources().getString(R.string.phoneApp_page_title),
                phoneAppText = getResources().getString(R.string.phoneApp_text_description),
                phoneAppValue = getResources().getString(R.string.phoneApp_service_price),
                webAppTitle = getResources().getString(R.string.webApp_page_title),
                webAppText = getResources().getString(R.string.webApp_text_description),
                webAppValue = getResources().getString(R.string.webApp_service_price),
                webPageTitle = getResources().getString(R.string.basicWebSite_page_title),
                webPageText = getResources().getString(R.string.basicPage_text_description),
                webPageValue = getResources().getString(R.string.basicPage_service_price),
                serverDBTitle = getResources().getString(R.string.serverDB_page_title),
                serverDBText = getResources().getString(R.string.serverDB_text_description),
                serverDBValue = getResources().getString(R.string.serverDB_service_price);


        final Context context = this;

        pcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent descriptionIntent =
                        new Intent(context, ServicesDescriptionActivity.class);
                descriptionIntent.putExtra("titles", pcServiceTitle);
                descriptionIntent.putExtra("services", pcServiceText);
                descriptionIntent.putExtra("totalCost", pcServiceValue);
                startActivity(descriptionIntent);
            }
        });

        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent descriptionIntent =
                        new Intent(context, ServicesDescriptionActivity.class);
                descriptionIntent.putExtra("titles", phoneAppTitle);
                descriptionIntent.putExtra("services", phoneAppText);
                descriptionIntent.putExtra("totalCost", phoneAppValue);
                startActivity(descriptionIntent);
            }
        });

        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent descriptionIntent =
                        new Intent(context, ServicesDescriptionActivity.class);
                descriptionIntent.putExtra("titles", webAppTitle);
                descriptionIntent.putExtra("services", webAppText);
                descriptionIntent.putExtra("totalCost", webAppValue);
                startActivity(descriptionIntent);
            }
        });

        basicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent descriptionIntent =
                        new Intent(context, ServicesDescriptionActivity.class);
                descriptionIntent.putExtra("titles", webPageTitle);
                descriptionIntent.putExtra("services", webPageText);
                descriptionIntent.putExtra("totalCost", webPageValue);
                startActivity(descriptionIntent);
            }
        });

        serverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent descriptionIntent =
                        new Intent(context, ServicesDescriptionActivity.class);
                descriptionIntent.putExtra("titles", serverDBTitle);
                descriptionIntent.putExtra("services", serverDBText);
                descriptionIntent.putExtra("totalCost", serverDBValue);
                startActivity(descriptionIntent);
            }
        });
    }


}
