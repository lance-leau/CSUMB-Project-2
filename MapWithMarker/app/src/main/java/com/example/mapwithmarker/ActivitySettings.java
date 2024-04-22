package com.example.mapwithmarker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivitySettings extends AppCompatActivity {
    private EditText usernameEditText;
    Button btnSignedOut;
    Spinner colorSpinner;
    private static final String USERNAME = "USERNAME";
    DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        btnSignedOut = findViewById(R.id.signOutButton);
        colorSpinner = findViewById(R.id.colorSpinner);

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

                if (selectedColor.equals("#87CEEB")) { // Sky Blue
                    getWindow().getDecorView().setBackgroundColor(Color.parseColor("#87CEEB"));
                    Log.d("BackgroundColor", "Sky Blue");
                } else if (selectedColor.equals("#228B22")) { // Forest Green
                    getWindow().getDecorView().setBackgroundColor(Color.parseColor("#228B22"));
                    Log.d("BackgroundColor", "Forest Green");
                } else if (selectedColor.equals("#EDC9AF")) { // Desert Sand
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

        usernameEditText = findViewById(R.id.usernameEditText);
        String username = getUsernameFromDatabase();

        usernameEditText.setText(username);
    }

    @SuppressLint("Range")
    private String getUsernameFromDatabase() {
        // Assuming DBHelper is in the same package
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String username = "";

        Cursor cursor = db.rawQuery("SELECT username FROM users LIMIT 1", null);
        if (cursor != null && cursor.moveToFirst()) {
            username = cursor.getString(cursor.getColumnIndex("username"));
            cursor.close();
        }

        db.close();
        return username;
    }
}
