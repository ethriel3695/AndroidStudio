package com.example.ethri.myfirstandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MyFirstAndroidAppActivity extends AppCompatActivity {

    private static final String
        DEBUG_TAG= "MyFirstAppLogging";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_first_android_app);

        Log.i(DEBUG_TAG, "In the onCreate() method of the MyFirstAndroidAppActivity Class");
    }
//    public void forceError() {
//        if(true) {
//            throw new Error("Whoops");
//        }
    }
