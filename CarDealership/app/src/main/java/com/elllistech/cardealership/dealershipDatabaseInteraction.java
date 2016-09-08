package com.elllistech.cardealership;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class dealershipDatabaseInteraction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealership_database_interaction);

        //TODO: Add insert functionality to the add button and create the layout
        CarDealershipDBHelper
                db = new CarDealershipDBHelper(this);
        Log.d("Add: ", "Adding Customers ...");
        db.addCustomer(new Customer("Fred", " Plantain", " Nissan", 30999.00));
        db.addCustomer(new Customer("Jack", " Smith", " Mercedes", 85550.00));
        db.addCustomer(new Customer("Lucy", " Diamond", " Lexus", 38255.00));
        db.addCustomer(new Customer("Patrick", " Nelson", " Ford", 22145.00));
        db.addCustomer(new Customer("Alice", " Jackson", " Chevrolet", 26850.00));

// Reading all shops
        Log.d("Obtaining: ", "Obtaining all Customers ...");
        List<Customer> customerList = db.getAllCustomers();

        for (Customer customer : customerList) {
            String log = "Id: " + customer.getCustomerID() + " ,First Name: " +
                    customer.getFirstName() + " ,Last Name: " + customer.getLastName() +
                    " ,Car Make:" + customer.getCarMake() + " ,Car Cost: " + customer.getCarCost();
// Writing shops to log
            Log.d("Shop: : ", log);
        }
    }
}
