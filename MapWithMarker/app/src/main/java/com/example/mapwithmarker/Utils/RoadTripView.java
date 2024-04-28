package com.example.mapwithmarker.Utils;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mapwithmarker.ActivityStartUp;
import com.example.mapwithmarker.Landing;
import com.example.mapwithmarker.MapsMarkerActivity;
import com.example.mapwithmarker.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class RoadTripView extends LinearLayout {
    String trip;
    TextView textViewTittle;
    TextView textViewStepCount;
    LinearLayout linearLayout;
    Context context;
    public RoadTripView(Context context, String trip) {
        super(context);
        this.trip = trip;
        this.context = context;
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.road_trip_view, this, true);

        this.textViewTittle = findViewById(R.id.text_step_name);
        this.textViewStepCount = findViewById(R.id.roadTripStepCounterTextView);
        this.linearLayout = findViewById(R.id.roadTripViewLayout);

        linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Landing)context).editActivity(RoadTripView.this);
            }
        });
    }

    public String getTrip() {
        return trip;
    }

    public void setStepCount(int i) {
        this.textViewStepCount.setText(i + " stp");
    }

    public void setTittle(String tittle) {
        String[] s = tittle.split(",");
        this.textViewTittle.setText(s[0]); //  + " --> " + s[s.length-1]
    }

    public void deleteFromParent() {
        // Get the parent of the RoadTripView
        ViewGroup parent = (ViewGroup) getParent();
        if (parent != null) {
            // Remove the RoadTripView from its parent
            parent.removeView(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        return ((RoadTripView)o).getTrip().equals(this.getTrip());
    }
}
