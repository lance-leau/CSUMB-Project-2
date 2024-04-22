package com.example.mapwithmarker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Landing extends AppCompatActivity {

    Button signOut, btnAdmin, plusButton, btnLogoSettings, btnSettings;
    DBHelper dbHelper;

    boolean IS_ADMIN = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        IS_ADMIN = getIntent().getBooleanExtra("IS_USER_ADMIN", false);

        signOut = findViewById(R.id.signOutButton);
        btnAdmin = findViewById(R.id.admin_button);
        plusButton = findViewById(R.id.plusButton);
        btnSettings = findViewById(R.id.settings);
        btnLogoSettings = findViewById(R.id.LogoSettings);
        dbHelper = new DBHelper(this);

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
                startActivity(intent);
                startActivity(intent);
            }
        });
    }
}