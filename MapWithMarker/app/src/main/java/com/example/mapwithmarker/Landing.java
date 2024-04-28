package com.example.mapwithmarker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mapwithmarker.Database.MyDatabase;
import com.example.mapwithmarker.Database.UserDao;
import com.example.mapwithmarker.Utils.RoadTripView;
import com.example.mapwithmarker.Utils.StringParser;
import com.example.mapwithmarker.databinding.RoadTripViewBinding;

import java.util.ArrayList;


public class Landing extends AppCompatActivity {

    Button signOut, btnAdmin, plusButton, btnLogoSettings, btnSettings;

    String username;
    MyDatabase myDb;
    UserDao userDao;
    boolean IS_ADMIN = false;
    ArrayList<RoadTripView> roadTripViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        IS_ADMIN = getIntent().getBooleanExtra("IS_USER_ADMIN", false);
        username = getIntent().getStringExtra("USERNAME");

        signOut = findViewById(R.id.signOutButton);
        btnAdmin = findViewById(R.id.admin_button);
        plusButton = findViewById(R.id.plusButton);
        btnSettings = findViewById(R.id.settings);
        btnLogoSettings = findViewById(R.id.LogoSettings);

        myDb = Room.databaseBuilder(this, MyDatabase.class, "usertable").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();

        userDao= myDb.getDao();

        if (IS_ADMIN) {
            btnAdmin.setVisibility(View.VISIBLE); // Show admin button
        } else {
            btnAdmin.setVisibility(View.GONE); // Hide admin button
        }

        btnSettings.setVisibility(View.GONE);
        signOut.setVisibility(View.GONE);
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Landing.this, ActivityLogin.class);//TODO
                startActivity(intent);
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Landing.this, ActivityStartUp.class);
                startActivity(intent);
            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Landing.this, MapsMarkerActivity.class);
                intent.putExtra("USERNAME", getIntent().getStringExtra("USERNAME"));
                startActivity(intent);
            }
        });

        btnLogoSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnSettings.getVisibility() == View.GONE) {
                    btnSettings.setVisibility(View.VISIBLE);
                    signOut.setVisibility(View.VISIBLE);
                }
                else {
                    btnSettings.setVisibility(View.GONE);
                    signOut.setVisibility(View.GONE);

                }

            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Landing.this, ActivitySettings.class);
                intent.putExtra("USERNAME", getIntent().getStringExtra("USERNAME"));
                startActivity(intent);
            }
        });

        Log.d("10293847", "ASFDADF");
        createRoadTripList();
    }

    private void createRoadTripList() {
        roadTripViews = StringParser.parseDBtoRoadTrips(userDao.getRoadTrips(username), this);
        LinearLayout layout = findViewById(R.id.roadTrip_list_linearLayout);
        for (RoadTripView rt : roadTripViews) {
            layout.addView(rt, 0);
        }
    }

    public void editActivity(RoadTripView roadTripView) {
        Intent intent = new Intent(this, MapsMarkerActivity.class);
        intent.putExtra("TRIP", roadTripView.getTrip());
        intent.putExtra("USERNAME", username);
        userDao.updateCities(username, StringParser.RoadTripArrToStr(roadTripViews, roadTripView));
        startActivity(intent);
    }
}