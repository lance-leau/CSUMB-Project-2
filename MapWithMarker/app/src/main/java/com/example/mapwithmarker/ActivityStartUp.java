package com.example.mapwithmarker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.mapwithmarker.Database.ImageDao;
import com.example.mapwithmarker.Database.MyDatabase;
import com.example.mapwithmarker.Database.UserDao;

public class ActivityStartUp extends AppCompatActivity {
    Button btnLogin, btnRegister;
    MyDatabase myDb;
    ImageDao imageDao;

    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        btnLogin = findViewById(R.id.startupLogin);
        btnRegister = findViewById(R.id.startupRegister);


        myDb = Room.databaseBuilder(this, MyDatabase.class, "usertable").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();

        imageDao = myDb.getImageDao();
        myDb.addCity();

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
                //Intent intent = new Intent(ActivityStartUp.this, ActivityRegister.class);
                Intent intent = new Intent(ActivityStartUp.this, Temporary.class);
                startActivity(intent);
            }
        });
    }
}
