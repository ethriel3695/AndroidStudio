package com.elllistech.cardealership;

/**
 * Created by ethri on 9/7/2016.
 */
public class Customer {
    private long
        customerID;

    private String
        customerFirstName,
        customerLastName,
        carMake;

    private double
        carCost;

    public Customer (String firstName, String lastName, String make, double cost) {
        this.customerFirstName = firstName;
        this.customerLastName = lastName;
        this.carMake = make;
        this.carCost = cost;
    }
    public long getCustomerID() { return customerID; }
    public void setCustomerID(long id) { this.customerID = id; }

    public String getFirstName() { return customerFirstName; }
    public void setFirstName(String firstName) { this.customerFirstName = firstName; }

    public String getLastName() { return customerLastName; }
    public void setLastName(String lastName) { this.customerLastName = lastName; }

    public String getCarMake() { return carMake; }
    public void setCarMake(String make) { this.carMake = make; }

    public double getCarCost() { return carCost; }
    public void setCarCost(double cost) { this.carCost = cost; }

}
