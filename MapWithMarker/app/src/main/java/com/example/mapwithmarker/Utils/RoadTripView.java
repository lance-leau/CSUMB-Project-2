package com.example.mapwithmarker.Utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mapwithmarker.R;

import java.util.ArrayList;

public class RoadTripView extends LinearLayout {
    TextView textViewTittle;
    TextView textViewStepCount;
    Context context;
    public RoadTripView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.road_trip_view, this, true);

        this.textViewTittle = findViewById(R.id.text_step_name);
        this.textViewStepCount = findViewById(R.id.roadTripStepCounterTextView);

        textViewTittle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("12345678", "efrgthyjguhklokp");
            }
        });
    }

    public void setStepCount(int i) {
        this.textViewStepCount.setText("Step nb : " + i);
    }

    public void setTittle(String tittle) {
        this.textViewTittle.setText(tittle);
    }
}
