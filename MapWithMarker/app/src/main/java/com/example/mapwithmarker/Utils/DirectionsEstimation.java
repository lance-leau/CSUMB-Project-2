package com.example.mapwithmarker.Utils;
import com.google.android.gms.maps.model.LatLng;

public class DirectionsEstimation {

    // Average travel speed by car in kilometers per hour
    private static final double AVERAGE_CAR_SPEED_KPH = 60.0;

    // Method to estimate travel time between two LatLng points
    public static String estimateTravelTime(LatLng origin, LatLng destination) {
        // Get the distance between the two points in kilometers
        double distance = calculateDistance(origin.latitude, origin.longitude, destination.latitude, destination.longitude);

        // Calculate travel time in hours
        double travelTimeHours = distance / AVERAGE_CAR_SPEED_KPH;

        // Convert travel time from hours to minutes and round to nearest integer
        int travelTimeMinutes = (int) Math.round(travelTimeHours * 60);

        // Return travel time in the format HH:MM
        return String.format("%02dh%02d", travelTimeMinutes / 60, travelTimeMinutes % 60);
    }


    private static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
