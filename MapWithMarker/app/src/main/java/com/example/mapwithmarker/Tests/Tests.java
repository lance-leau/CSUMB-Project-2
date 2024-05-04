package com.example.mapwithmarker.Tests;

import static com.example.mapwithmarker.Utils.StringParser.addressParser;
import static com.example.mapwithmarker.Utils.StringParser.parseDBtoRoadTrips;
import static junit.framework.TestCase.assertEquals;

import com.example.mapwithmarker.Utils.StringParser;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Tests {
    @Test
    public void testCityParser() {
        String cities = "2,4,Rennes,Lyon,Strasbourg,Lille,4,Lyon,Marseille,Barcelona,Madrid";

        ArrayList<Integer> sizes = new ArrayList<>();
        ArrayList<String> roadTrips = new ArrayList<>();
        ArrayList<String> roadTripSteps = new ArrayList<>();

        ArrayList<Integer> expectedSizes = new ArrayList<Integer>();
        ArrayList<String> expectedRoadTrips = new ArrayList<>();
        ArrayList<String> expectedRoadTripSteps = new ArrayList<>();
        expectedSizes.add(4);
        expectedSizes.add(4);
        expectedRoadTrips.add("Rennes, Lyon, Strasbourg, Lille");
        expectedRoadTrips.add("Lyon, Marseille, Barcelona, Madrid");
        expectedRoadTripSteps.add("Rennes,Lyon,Strasbourg,Lille");
        expectedRoadTripSteps.add("Lyon,Marseille,Barcelona,Madrid");

        parseDBtoRoadTrips(cities, sizes, roadTrips, roadTripSteps);

        for (int i = 0; i < roadTrips.size(); i++) {
            assertEquals(sizes.get(i), expectedSizes.get(i));
            assertEquals(roadTrips.get(i), expectedRoadTrips.get(i));
            assertEquals(roadTripSteps.get(i), expectedRoadTripSteps.get(i));
        }
    }


    @Test
    public void addressParserTest() {
        String actual = "new YORK, UNITED statEs Of AmeriCA";
        String expected = "New York, United States Of America";

        assertEquals(expected, addressParser(actual));
    }

}
