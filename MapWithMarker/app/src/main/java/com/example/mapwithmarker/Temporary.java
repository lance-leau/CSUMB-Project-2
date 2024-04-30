package com.example.mapwithmarker;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.mapwithmarker.Database.ImageDao;
import com.example.mapwithmarker.Database.MyDatabase;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Temporary extends AppCompatActivity {
    MyDatabase myDb;
    ImageDao imageDao;
    ImageView imageView;
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporary);
        imageView = findViewById(R.id.imageViewCity);

        myDb = Room.databaseBuilder(this, MyDatabase.class, "usertable").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();

        imageDao= myDb.getImageDao();

        String image = imageDao.getImages("Lyon");

        Picasso.get().load(image).into(imageView);
    }
}
