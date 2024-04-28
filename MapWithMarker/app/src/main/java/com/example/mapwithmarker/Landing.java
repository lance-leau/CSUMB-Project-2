package com.example.mapwithmarker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mapwithmarker.Database.MyDatabase;
import com.example.mapwithmarker.Database.UserDao;


public class Landing extends AppCompatActivity {

    Button signOut, btnAdmin, plusButton, btnLogoSettings, btnSettings;

    String username;
    String password;
    MyDatabase myDb;
    UserDao userDao;
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
                intent.putExtra("PASSWORD", getIntent().getStringExtra("PASSWORD"));
                intent.putExtra("ISADMIN", getIntent().getBooleanExtra("ISADMIN", false));
                startActivity(intent);
            }
        });
    }
}