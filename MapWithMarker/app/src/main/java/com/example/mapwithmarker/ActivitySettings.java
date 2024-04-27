package com.example.mapwithmarker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mapwithmarker.Database.MyDatabase;
import com.example.mapwithmarker.Database.UserDao;


public class ActivitySettings extends AppCompatActivity {
    private TextView usernameTextView, passwordTextView;
    boolean isPasswordVisible = false;
    Button btnSignedOut, btnAdmin, btnArrowBack;
    Spinner colorSpinner;
    private static final String USERNAME = "USERNAME";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        btnSignedOut = findViewById(R.id.signOutButton);
        colorSpinner = findViewById(R.id.colorSpinner);
        passwordTextView = findViewById(R.id.passwordEditText);
        usernameTextView = findViewById(R.id.usernameEditText);

        btnAdmin =  findViewById(R.id.seeDatabaseButton);
        btnArrowBack = findViewById(R.id.arrowBack);

        usernameTextView.setText(getIntent().getStringExtra("USERNAME"));
        passwordTextView.setText(getIntent().getStringExtra("PASSWORD"));

        if (getIntent().getBooleanExtra("ISADMIN", false)) {
            btnAdmin.setVisibility(View.VISIBLE);
        } else {
            btnAdmin.setVisibility(View.GONE);
        }

        btnArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivitySettings.this, Landing.class);
                startActivity(intent);
            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.color_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapter);

        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedColor = parent.getItemAtPosition(position).toString();
                Log.d("Selected Value", selectedColor);
                Log.d("SkyBlueValue", String.valueOf(selectedColor.equals("#87CEEB")));
                Log.d("ForestGreenValue", getString(R.string.forest_green));
                Log.d("DesertSandValue", getString(R.string.desert_sand));

                if (selectedColor.equals("sky_blue")) { // Sky Blue
                    getWindow().getDecorView().setBackgroundColor(Color.parseColor("#87CEEB"));
                    Log.d("BackgroundColor", "Sky Blue");
                } else if (selectedColor.equals("forest_green")) { // Forest Green
                    getWindow().getDecorView().setBackgroundColor(Color.parseColor("#228B22"));
                    Log.d("BackgroundColor", "Forest Green");
                } else if (selectedColor.equals("desert_sand")) { // Desert Sand
                    getWindow().getDecorView().setBackgroundColor(Color.parseColor("#EDC9AF"));
                    Log.d("BackgroundColor", "Desert Sand");
                } else {
                    Log.d("BackgroundColor", "No Match Found");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                getWindow().getDecorView().setBackgroundColor(Color.parseColor("#87CEEB"));
            }
        });


        btnSignedOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivitySettings.this, ActivityStartUp.class);
                startActivity(intent);
            }
        });

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivitySettings.this, ActivityAdmin.class);
                startActivity(intent);
            }
        });

        passwordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("d", isPasswordVisible + "");
                if (isPasswordVisible) {
                    isPasswordVisible = false;
                    passwordTextView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    isPasswordVisible = true;
                    passwordTextView.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
            }
        });

    }


}
