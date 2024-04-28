package com.example.mapwithmarker.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mapwithmarker.MapsMarkerActivity;
import com.example.mapwithmarker.R;
import com.example.mapwithmarker.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.Marker;

public class TripStepView extends LinearLayout {

    private Marker marker;
    public TextView  stepTextView;
    private Button removeButton;
    private Button moveUpButton;
    private Button moveDownButton;
    private int rank;
    private Steps steps;
    private ActivityMapsBinding bd;
    private Context context;

    public TripStepView(Context context, ActivityMapsBinding bd, Steps steps, Marker marker) {
        super(context);
        this.context = context;
        this.marker = marker;
        this.steps = steps;
        this.bd = bd;
        TripStepView.this.rank = getRank();
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.trip_step_view, this, true);

        // Find views
        stepTextView = findViewById(R.id.text_step_name);
        removeButton = findViewById(R.id.btn_remove);
        moveUpButton = findViewById(R.id.btn_move_up);
        moveDownButton = findViewById(R.id.btn_move_down);

        // Set onClickListeners

        stepTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MapsMarkerActivity)context).goToAbout(stepTextView.getText().toString(), marker.getPosition());
            }
        });

        removeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                TripStepView.this.rank = getRank();
                bd.stepsListLinearLayout.removeViewAt(rank);
                steps.removeStep(rank);
                marker.remove();
            }
        });

        moveUpButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                TripStepView.this.rank = getRank();
                if (rank != 0) {
                    TripStepView temp = steps.getSteps().get(rank-1);
                    TripStepView temp2 = new TripStepView(temp.context, temp.bd, temp.steps, temp.marker);
                    temp2.setStepText(temp.stepTextView.getText().toString());
                    bd.stepsListLinearLayout.addView(temp2, rank+1);
                    bd.stepsListLinearLayout.removeViewAt(rank-1);
                    steps.getSteps().remove(temp);
                    steps.getSteps().add(rank, temp2);
                    Log.d("debug", steps.toString());
                }
            }
        });

        moveDownButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                TripStepView.this.rank = getRank();
                if (rank != steps.getSize()-1) {
                    TripStepView temp = steps.getSteps().get(rank+1);
                    TripStepView temp2 = new TripStepView(temp.context, temp.bd, temp.steps, temp.marker);
                    temp2.setStepText(temp.stepTextView.getText().toString());
                    bd.stepsListLinearLayout.addView(temp2, rank);
                    bd.stepsListLinearLayout.removeViewAt(rank+2);
                    steps.getSteps().remove(temp);
                    steps.getSteps().add(rank, temp2);
                    Log.d("debug", steps.toString());
                }
            }
        });
    }

    // Method to set text of the TextView
    public void setStepText(String text) {
        stepTextView.setText(text);
    }

    public int getRank() {
        return steps.getSteps().indexOf(this);
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
