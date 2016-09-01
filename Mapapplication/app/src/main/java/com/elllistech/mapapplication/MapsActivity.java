package com.elllistech.mapapplication;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_SATELLITE;

public class    MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap exampleMap;
    private UiSettings exampleUiSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        RadioGroup
//                mapTypeSelection = (RadioGroup)findViewById(R.id.map_views);
//
//        mapTypeSelection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int mapTypeID) {
//                if (mapTypeID == R.id.map_normal) {
//                    exampleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//                }
//                else if (mapTypeID == R.id.map_satellite) {
//                    exampleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
//                }
//            }
//        });

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Rigby, Idaho.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        exampleMap = googleMap;

        exampleUiSettings = exampleMap.getUiSettings();

        // Add a marker in Sydney and move the camera
        LatLng rigby = new LatLng(43.673312, -111.912756);
        exampleMap.addMarker(new MarkerOptions().position(rigby).title("Marker in Rigby, Idaho!"));
        exampleMap.moveCamera(CameraUpdateFactory.newLatLng(rigby));
        exampleUiSettings.setZoomControlsEnabled(true);
        exampleUiSettings.setMapToolbarEnabled(false);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    public void renderNormalMap(View view) {
        exampleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    public void renderSatelliteMap(View view) {
        exampleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }
}
