package com.example.mapwithmarker.Utils;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

public class StringParser {
    public static String pascalCase(String input) {
        String[] words = input.split(" ");

        StringBuilder result = new StringBuilder();
        for (String word : words) {
            String lowerCaseWord = word.toLowerCase();
            String capitalizedWord = lowerCaseWord.substring(0, 1).toUpperCase() + lowerCaseWord.substring(1);
            result.append(capitalizedWord).append(" ");
        }
        return result.toString().trim();
    }

    public static String parseDestinations(Steps steps) {
        if (steps.getSteps().size() == 0) {
            return "0";
        }
        StringBuilder ret = new StringBuilder("" + steps.getSteps().size());
        for (TripStepView tsv : steps.getSteps()) {
            ret.append(",").append(tsv.stepTextView.getText().toString());
        }
        return ret.toString();
    }

    public static ArrayList<RoadTripView> parseDBtoRoadTrips(String s, Context context) {
        ArrayList<Integer> sizes = new ArrayList<>();
        ArrayList<String> roadTrips = new ArrayList<>();
        ArrayList<String> roadTripSteps = new ArrayList<>();

        if (s.equals("0")) {
            return new ArrayList<RoadTripView>();
        }

        String[] parts = s.split(",");
        int tripCount = Integer.parseInt(parts[0]);
        int currentIndex = 1;
        for (int i = 0; i < tripCount; i++) {
            int tripSize = Integer.parseInt(parts[currentIndex]);
            sizes.add(tripSize);
            currentIndex++;

            StringBuilder tripStops = new StringBuilder();
            StringBuilder tripSteps = new StringBuilder();
            for (int j = 0; j < tripSize; j++) {
                tripStops.append(parts[currentIndex]);
                tripSteps.append(parts[currentIndex]);
                currentIndex++;
                if (j < tripSize - 1) {
                    tripStops.append(", ");
                    tripSteps.append(",");
                }
            }
            roadTrips.add(tripStops.toString());
            roadTripSteps.add(tripSteps.toString());
        }

        ArrayList<RoadTripView> rt = new ArrayList<RoadTripView>();

        for (int i = 0; i < roadTrips.size(); i++) {
            RoadTripView roadTripView = new RoadTripView(context, roadTripSteps.get(i));
            roadTripView.setTittle(roadTrips.get(i));
            roadTripView.setStepCount(sizes.get(i));
            rt.add(roadTripView);
        }

        return rt;
    }

    public static String RoadTripArrToStr(ArrayList<RoadTripView> arr) {
        String ret = "" + arr.size();
        for (RoadTripView r : arr) {
            ret += "," + (r.getTrip().split(",").length) + "," + r.getTrip();
        }
        Log.d("parsed String", ret);
        return ret;
    }

    public static String RoadTripArrToStr(ArrayList<RoadTripView> arr, RoadTripView exclude) {
        String ret = "" + (arr.size() -1);
        for (RoadTripView r : arr) {
            if (!r.equals(exclude)) ret += "," + (r.getTrip().split(",").length) + "," + r.getTrip();
        }
        Log.d("parsed String", ret);
        return ret;
    }
}
