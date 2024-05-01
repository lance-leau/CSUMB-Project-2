package com.example.mapwithmarker.Utils;
import com.google.android.gms.maps.model.LatLng;

public class DirectionsEstimation {
    public static String estimateTravelTime(LatLng origin, LatLng destination) {
        double distance = calculateDistance(origin.latitude, origin.longitude, destination.latitude, destination.longitude);
        double travelTimeHours = 60 * distance / 80;
        return String.format("%02dh%02d", (int)(travelTimeHours / 60), (int)(travelTimeHours % 60));
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