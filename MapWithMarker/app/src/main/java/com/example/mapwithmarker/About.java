package com.example.mapwithmarker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mapwithmarker.databinding.ActivityAboutBinding;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import android.os.Bundle;
import android.util.Log;

public class About extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;

    ActivityAboutBinding binding;

    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        LatLng coords = new LatLng(0, 0);

        this.address = getIntent().getStringExtra("ADDRESS");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            coords = getIntent().getParcelableExtra("LatLng", LatLng.class);
        }
        binding.AboutTitleTextView.setText("Things to do in " + address);
        Log.d("PLACE !", "place size");
    }

}