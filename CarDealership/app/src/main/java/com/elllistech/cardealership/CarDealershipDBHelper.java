package com.elllistech.cardealership;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by ethri on 9/7/2016.
 */
public class CarDealershipDBHelper extends SQLiteOpenHelper{

    private static final String
            DealershipInfoDB = "dealership_info.db";

    public static final String
            Dealership_Table = "dealership",
            CustomerID = "customerID",
            CustomerFirstName = "customerFirstName",
            CustomerLastName = "customerLastName",
            CarMake = "carMake",
            CarCost = "carCost",
            Debug_Indicator = "DealershipDBAccess";

    private static final int
            DatabaseVersion = 1;

    SQLiteDatabase
            db = this.getWritableDatabase();

    ContentValues
            carValues = new ContentValues();


    public CarDealershipDBHelper(Context context) {
        super(context, DealershipInfoDB, null, DatabaseVersion);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String
                SQLTableCreation = "CREATE TABLE " + Dealership_Table + " (" + CustomerID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + CustomerFirstName + " TEXT, " +
                CustomerLastName + " TEXT, " + CarMake + " TEXT, " + CarCost + " INTEGER" + ")";
        sqLiteDatabase.execSQL(SQLTableCreation);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int currentVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Dealership_Table);
        onCreate(db);
    }
    public void addCustomer(Customer customer) {
        carValues.put(CarDealershipDBHelper.CustomerFirstName, customer.getFirstName());
        carValues.put(CarDealershipDBHelper.CustomerLastName, customer.getLastName());
        carValues.put(CarDealershipDBHelper.CarMake, customer.getCarMake());
        carValues.put(CarDealershipDBHelper.CarCost, customer.getCarCost());
        long
                customerId = db.insert(Dealership_Table, null, carValues);
        customer.setCustomerID(customerId);
        db.close();
    }
    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer>
                customerList = new ArrayList<Customer>();
        String
                selectCustomersQuery = "SELECT * FROM " + Dealership_Table;
        Cursor
                cursor = null;
        try {
            cursor = db.rawQuery(selectCustomersQuery, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Customer
                            customer = new Customer();
                    customer.setCustomerID(cursor.getLong
                            (cursor.getColumnIndex(CustomerID)));
                    customer.setFirstName(cursor.getString(
                            cursor.getColumnIndex(CustomerFirstName)));
                    customer.setLastName(cursor.getString(
                            cursor.getColumnIndex(CustomerLastName)));
                    customer.setCarMake(cursor.getString(
                            cursor.getColumnIndex(CarMake)));
                    customer.setCarCost(cursor.getDouble(
                            cursor.getColumnIndex(CarCost)));
                    customerList.add(customer);
                }
            }
        }catch (Exception e) {
            Log.d(Debug_Indicator, "Exception with a value of " + e);
        }finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return customerList;
    }
    public int updateCustomers(Customer customer) {
        carValues.put(CarDealershipDBHelper.CustomerFirstName, customer.getFirstName());
        carValues.put(CarDealershipDBHelper.CustomerLastName, customer.getLastName());
        carValues.put(CarDealershipDBHelper.CarMake, customer.getCarMake());
        carValues.put(CarDealershipDBHelper.CarCost, customer.getCarCost());

        return db.update(Dealership_Table, carValues, CustomerID + "=?",
                new String[]{String.valueOf(customer.getCustomerID())});
    }
    public void deleteCustomer(Customer customer) {
        db.delete(Dealership_Table, CustomerID + "=?",
                new String[]{String.valueOf(customer.getCustomerID())});
        db.close();
    }
}
