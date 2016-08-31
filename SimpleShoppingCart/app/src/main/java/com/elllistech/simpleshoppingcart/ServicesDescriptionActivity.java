package com.elllistech.simpleshoppingcart;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ServicesDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services_description);

        Intent servicesIntent = getIntent();

        String
                title = servicesIntent.getExtras().getString("titles"),
                service = servicesIntent.getExtras().getString("services"),
                totalCost = servicesIntent.getExtras().getString("totalCost");

        TextView
            serviceTitle = (TextView)findViewById(R.id.descriptionPageTitle),
            serviceDescription = (TextView)findViewById(R.id.txtServiceFeatures),
            servicePrice = (TextView)findViewById(R.id.txtPriceValue);

        serviceTitle.setText(title);
        serviceDescription.setText(service);
        servicePrice.setText(totalCost);

        Button
                phoneButton = (Button)findViewById(R.id.backToHome),
                addToCartButton = (Button)findViewById(R.id.addToCartButton);

        final Context context = this;

        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homePageIntent =
                        new Intent(context, MainActivity.class);
                startActivity(homePageIntent);
            }
        });

        final TextView
                shoppingCartBadgeText = (TextView)findViewById(R.id.shoppingCartBadge);
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String badgeText = shoppingCartBadgeText.getText().toString();
                int shoppingCartCounter = Integer.parseInt(badgeText);
                shoppingCartCounter++;
                shoppingCartBadgeText.setText(String.valueOf(shoppingCartCounter));
            }
        });

    }
}
