package com.introtoandroid.distancecalculator;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {

    //Declares the location manager to find a provider for location services
    LocationManager userLocationManager;
    String locationProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        //Initializes the location manager
        userLocationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        Criteria userLocationCriteria = new Criteria();
        userLocationCriteria.setAccuracy(Criteria.ACCURACY_FINE);

        //Gets the best provider based on the phone location and is set to false so
        //application can find all providers not just enabled providers
        locationProvider = userLocationManager.getBestProvider(userLocationCriteria, false);

        //A check to determine if a provider can be found
        if (locationProvider != null && !locationProvider.equals(""))
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        //The last known location is cached in the device once the first set of GPS coordinates are
        //found.  In order to set first coordinates I clicked on extended controls and clicked send
        //for the longitude and latitude coordinates provided by the emulator.
        //The requestLocationUpdates method gets the GPS coordinates once a provider has been found
        Location userLocation = userLocationManager.getLastKnownLocation(userLocationManager.GPS_PROVIDER);
        userLocationManager.requestLocationUpdates(locationProvider,
                15000, 1, this);

        if (userLocation != null)
        {
            onLocationChanged(userLocation);
        }
        else
        {
            Toast.makeText(getBaseContext(), "Location provider does not exist!!!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //Sets the TextView boxes with the values of the longitude and latitude coordinates
    @Override
    public void onLocationChanged(Location location) {
        TextView longitude = (TextView)findViewById(R.id.txtLongitude);
        TextView latitude = (TextView)findViewById(R.id.txtLatitude);

        longitude.setText("The Longitude is: " + location.getLongitude());
        latitude.setText("The Latitude is: " + location.getLatitude());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
