package com.example.mapwithmarker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

public class Landing extends AppCompatActivity {

    Button signOut, btnAdmin, plusButton;
    TextView usernameText;
    DBHelper dbHelper;

    boolean IS_ADMIN = false;

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        IS_ADMIN = getIntent().getBooleanExtra("IS_USER_ADMIN", false);

        signOut = findViewById(R.id.signOutButton);
        btnAdmin = findViewById(R.id.admin_button);
        plusButton = findViewById(R.id.plusButton);
        usernameText = findViewById(R.id.usernameText);

        dbHelper = new DBHelper(this);

        if (IS_ADMIN) {
            btnAdmin.setVisibility(View.VISIBLE); // Show admin button
            usernameText.setVisibility(View.GONE); // Hide Username TextView
        } else {
            btnAdmin.setVisibility(View.GONE); // Hide admin button
            usernameText.setVisibility(View.VISIBLE); // Show Username TextView
            usernameText.setText("Welcome " + getIntent().getStringExtra("USER_NAME"));
        }
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
                SharedPreferences preferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("isLoggedIn", false);
                editor.apply();
                Intent intent = new Intent(Landing.this, ActivityStartUp.class);
                startActivity(intent);
                finish();
            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Landing.this, MapsMarkerActivity.class);
                startActivity(intent);
            }
        });
    }
}