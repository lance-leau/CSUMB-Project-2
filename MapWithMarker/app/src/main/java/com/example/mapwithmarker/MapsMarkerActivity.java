package com.example.mapwithmarker;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mapwithmarker.Utils.*;
import com.example.mapwithmarker.databinding.ActivityMapsBinding;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;


/**
 * An activity that displays a Google map with a marker (pin) to indicate a particular location.
 */
// [START maps_marker_on_map_ready]
public class MapsMarkerActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    ActivityMapsBinding binding;

    boolean menuIsOpen = false;
    static final int MENU_CLOSED_SIZE = 100;
    static final int MENU_OPENED_SIZE = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());

        // Retrieve the content view that renders the map.
        setContentView(binding.getRoot());

        // set the title bar size
        View scrollView = binding.stepsScrollView;
        ViewGroup.LayoutParams layoutParams = scrollView.getLayoutParams();
        layoutParams.height = 250; // Change this to your desired height
        scrollView.setLayoutParams(layoutParams);
        scrollView.requestLayout();

        TextView activityTitle = findViewById(R.id.textView);
        activityTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuIsOpen){
                    Animations.animateViewHeight(scrollView, 1500, 250, 1);
                    menuIsOpen = false;
                } else {
                    Animations.animateViewHeight(scrollView, 250, 1500, 1);
                    menuIsOpen = true;
                }
            }
        });



        // Get the SupportMapFragment and request notification when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng france = new LatLng(46.2000, 1.8000);
        // [START_EXCLUDE silent]

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(france, (float)(5.3)));
        // [END_EXCLUDE]
    }
    // [END maps_marker_on_map_ready_add_marker]
}
// [END maps_marker_on_map_ready]


// TODO HOW TO ADD A MARKER :
/*
LatLng sydney = new LatLng(-33.852, 151.211);
googleMap.addMarker(new MarkerOptions()
    .position(sydney)
    .title("Marker in Sydney"));
googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
 */
