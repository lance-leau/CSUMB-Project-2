package com.examples.Utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class CityCoordinatesUtils {

    public static LatLng getCoordinates(Context context, String cityName) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses;
        LatLng coordinates = null;

        try {
            addresses = geocoder.getFromLocationName(cityName, 1);
            if (addresses != null && addresses.size() > 0) {
                double latitude = addresses.get(0).getLatitude();
                double longitude = addresses.get(0).getLongitude();
                coordinates = new LatLng(latitude, longitude);
            }
        } catch (IOException e) {
            Log.e("CityCoordinatesUtils", "Error obtaining coordinates", e);
        }

        return coordinates;
    }
}
