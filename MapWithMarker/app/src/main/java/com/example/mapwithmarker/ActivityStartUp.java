package com.example.mapwithmarker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityStartUp extends AppCompatActivity {
    Button btnLogin, btnRegister;

    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            Intent intent = new Intent(ActivityStartUp.this, Landing.class);
            startActivity(intent);
            finish();
        }
        else{
            setContentView(R.layout.activity_startup);

            btnLogin = findViewById(R.id.startupLogin);
            btnRegister = findViewById(R.id.startupRegister);

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ActivityStartUp.this, ActivityLogin.class);
                    startActivity(intent);
                }
            });

            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ActivityStartUp.this, ActivityRegister.class);
                    startActivity(intent);
                }
            });
        }
    }
}
