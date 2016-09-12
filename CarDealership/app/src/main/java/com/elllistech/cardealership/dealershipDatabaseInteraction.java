package com.elllistech.cardealership;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class dealershipDatabaseInteraction extends AppCompatActivity implements
        viewCustomerFragment.OnFragmentInteractionListener,
        addCustomerFragment.OnFragmentInteractionListener,
        updateCustomerFragment.OnFragmentInteractionListener,
        deleteCustomerFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealership_database_interaction);

        android.support.v4.app.Fragment fragmentManager =
                getSupportFragmentManager().findFragmentById(R.id.blankFragmentContainer);

        viewCustomerFragment fragment = new viewCustomerFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();


        fragmentTransaction.replace(R.id.blankFragmentContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        final Button
                addButton = (Button)findViewById(R.id.btnAdd),
                viewButton = (Button)findViewById(R.id.btnView),
                updateButton = (Button)findViewById(R.id.btnUpdate),
                deleteButton = (Button)findViewById(R.id.btnDelete);

        final Context context = this;

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findViewById(R.id.txtFirstName) != null) {
                    EditText
                            txtFirstName = (EditText)findViewById(R.id.txtFirstName),
                            txtLastName = (EditText)findViewById(R.id.txtLastName),
                            txtCarMake = (EditText)findViewById(R.id.txtCarMake),
                            txtCarCost = (EditText)findViewById(R.id.txtCarCost);
                    String
                            firstName = txtFirstName.getText().toString(),
                            lastName = txtLastName.getText().toString(),
                            carMake = txtCarMake.getText().toString(),
                            carCostText = txtCarCost.getText().toString();

                    if (!firstName.equals("") && !lastName.equals("") && !carMake.equals("") &&
                            !carCostText.equals("")) {
                        CarDealershipDBHelper
                                db = new CarDealershipDBHelper(context);
                        double
                                carCost = Double.parseDouble(carCostText);
                        db.addCustomer(new Customer(firstName, lastName, carMake, carCost));
                        Toast.makeText(context,
                                "Customer " + firstName + " added!",
                                Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(dealershipDatabaseInteraction.this,
                                "Please fill out all fields to add a Customer!",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    addCustomerFragment fragment = new addCustomerFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();


                    fragmentTransaction.replace(R.id.blankFragmentContainer, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewCustomerFragment fragment = new viewCustomerFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();


                fragmentTransaction.replace(R.id.blankFragmentContainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCustomerFragment fragment = new updateCustomerFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();


                fragmentTransaction.replace(R.id.blankFragmentContainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCustomerFragment fragment = new deleteCustomerFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();


                fragmentTransaction.replace(R.id.blankFragmentContainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
