package com.example.mapwithmarker;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.mapwithmarker.Database.MyDatabase;
import com.example.mapwithmarker.Database.UserDao;
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

    String username;

    MyDatabase myDb;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        steps = new Steps();

        myDb = Room.databaseBuilder(this, MyDatabase.class, "usertable").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();

        userDao= myDb.getDao();

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

        this.username = getIntent().getStringExtra("USERNAME");

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
                String address = StringParser.pascalCase(binding.AddNewDestinationEditText.getText().toString());

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

        binding.backToLandingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = userDao.getRoadTrips(username);
                String parsedSteps = StringParser.parseDestinations(steps);
                if (!parsedSteps.equals("0")) {
                    if (s.equals("0")) {
                        userDao.updateCities(username, "1," + parsedSteps);
                    } else {
                        String[] parts = s.split(",", 2);
                        String tripNum = parts[0];
                        String restOfString = parts[1];
                        String parsedString = (Integer.parseInt(tripNum) + 1) + "," + restOfString + "," + parsedSteps;
                        userDao.updateCities(username, parsedString);
                    }
                }
                Intent intent = new Intent(MapsMarkerActivity.this, Landing.class);
                intent.putExtra("USERNAME", username);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        GM = googleMap;
        LatLng france = new LatLng(46.2000, 1.8000);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(france, (float)(5.3)));

        String parsedString = getIntent().getStringExtra("TRIP");
        if (parsedString != null) {
            for (String s : parsedString.split(",")) {
                LatLng coordinates = CityCoordinatesUtils.getCoordinates(MapsMarkerActivity.this, s);
                if (coordinates == null){
                    Toast.makeText(MapsMarkerActivity.this, "address is invalid", Toast.LENGTH_SHORT).show();
                    return;
                }
                Marker marker = GM.addMarker(new MarkerOptions().position(coordinates).title(s));

                TripStepView tsv = new TripStepView(MapsMarkerActivity.this, binding, steps, marker);
                tsv.setStepText(s);
                binding.stepsListLinearLayout.addView(tsv, binding.stepsListLinearLayout.getChildCount()-1);

                // add the Step to the dest list
                steps.addStep(tsv);
            }
        }
    }

    public void goToAbout(String address, LatLng coords) {
        Intent intent = new Intent(MapsMarkerActivity.this, About.class);
        intent.putExtra("ADDRESS", address);
        intent.putExtra("LatLng", coords);
        startActivity(intent);
    }
}