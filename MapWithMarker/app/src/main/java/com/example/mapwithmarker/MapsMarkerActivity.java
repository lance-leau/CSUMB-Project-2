package com.example.mapwithmarker;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mapwithmarker.Utils.Animations;
import com.example.mapwithmarker.Utils.CityCoordinatesUtils;
import com.example.mapwithmarker.Utils.Steps;
import com.example.mapwithmarker.Utils.StringParser;
import com.example.mapwithmarker.Utils.TripStepView;
import com.example.mapwithmarker.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * An activity that displays a Google map with a marker (pin) to indicate a particular location.
 */
// [START maps_marker_on_map_ready]
public class MapsMarkerActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    Steps steps;
    GoogleMap GM;

    ActivityMapsBinding binding;

    boolean menuIsOpen = false;
    static final int MENU_CLOSED_SIZE = 250;
    static final int MENU_OPENED_SIZE = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        steps = new Steps();

        binding = ActivityMapsBinding.inflate(getLayoutInflater());

        // Retrieve the content view that renders the map.
        setContentView(binding.getRoot());

        // set the title bar size
        View scrollView = binding.daddyLinearLayout;
        ViewGroup.LayoutParams layoutParams = scrollView.getLayoutParams();
        layoutParams.height = MENU_CLOSED_SIZE; // Change this to your desired height
        scrollView.setLayoutParams(layoutParams);
        scrollView.requestLayout();


        // Get the SupportMapFragment and request notification when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        binding.PlanTripTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.stepsScrollView.smoothScrollTo(0, 0);
                if (menuIsOpen){
                    Animations.animateViewHeight(scrollView, MENU_OPENED_SIZE, MENU_CLOSED_SIZE, 1);
                    menuIsOpen = false;
                } else {
                    Animations.animateViewHeight(scrollView, MENU_CLOSED_SIZE, MENU_OPENED_SIZE, 1);
                    menuIsOpen = true;
                }
            }
        });

        binding.AddNewDestinationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the address
                String address = StringParser.parseString(binding.AddNewDestinationEditText.getText().toString());

                // create the new marker
                LatLng coordinates = CityCoordinatesUtils.getCoordinates(MapsMarkerActivity.this, address);
                if (coordinates == null){
                    binding.AddNewDestinationEditText.setText("");
                    Toast.makeText(MapsMarkerActivity.this, "address is invalid", Toast.LENGTH_SHORT).show();
                    return;
                }
                Marker marker = GM.addMarker(new MarkerOptions().position(coordinates).title(address));

                TripStepView tsv = new TripStepView(MapsMarkerActivity.this, binding, steps, marker);
                tsv.setStepText(address);
                binding.stepsListLinearLayout.addView(tsv, binding.stepsListLinearLayout.getChildCount()-1);

                // add the Step to the dest list
                steps.addStep(tsv);

                // clear the text
                binding.AddNewDestinationEditText.setText("");

                // close the menu
                if (menuIsOpen) {
                    Animations.animateViewHeight(scrollView, MENU_OPENED_SIZE, MENU_CLOSED_SIZE, 0);
                    binding.stepsScrollView.smoothScrollTo(0, 0);
                    menuIsOpen = false;
                }

                // center on new marker
                GM.moveCamera(CameraUpdateFactory.newLatLng(coordinates));
            }
        });
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        GM = googleMap;
        LatLng france = new LatLng(46.2000, 1.8000);
        // [START_EXCLUDE silent]

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(france, (float)(5.3)));
        // [END_EXCLUDE]
    }

    public void goToAbout(String address, LatLng coords) {
        Intent intent = new Intent(MapsMarkerActivity.this, About.class);
        intent.putExtra("ADDRESS", address);
        intent.putExtra("LatLng", coords);
        startActivity(intent);
    }
}


// TODO HOW TO ADD A MARKER :
/*
    LatLng sydney = new LatLng(-33.852, 151.211);
googleMap.addMarker(new MarkerOptions()
    .position(sydney)
    .title("Marker in Sydney"));
googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
 */
