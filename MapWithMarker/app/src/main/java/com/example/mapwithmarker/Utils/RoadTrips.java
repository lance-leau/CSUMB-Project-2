package com.example.mapwithmarker.Utils;

import android.widget.LinearLayout;

import java.util.ArrayList;

public class RoadTrips {
    ArrayList<RoadTripView> roadTripViews;

    public RoadTrips(ArrayList<RoadTripView> roadTripViews) {
        this.roadTripViews = roadTripViews;
    }

    public RoadTrips() {
        this.roadTripViews = new ArrayList<RoadTripView>();
    }

    public ArrayList<RoadTripView> getRoadTripViews() {
        return roadTripViews;
    }

    public void setRoadTripViews(ArrayList<RoadTripView> roadTripViews) {
        this.roadTripViews = roadTripViews;
    }
}
