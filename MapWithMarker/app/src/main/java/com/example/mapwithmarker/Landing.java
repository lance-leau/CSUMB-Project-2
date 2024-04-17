package com.example.mapwithmarker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Landing extends AppCompatActivity {

    Button signOut, btnAdmin;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        signOut = findViewById(R.id.signOutButton);
        btnAdmin = findViewById(R.id.admin_button);
        dbHelper = new DBHelper(this);

        if (dbHelper.isAdminUser()) {
            btnAdmin.setVisibility(View.VISIBLE); // Show admin button
        } else {
            btnAdmin.setVisibility(View.GONE); // Hide admin button
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
                Intent intent = new Intent(Landing.this, ActivityRegister.class);
                startActivity(intent);
            }
        });
    }
}