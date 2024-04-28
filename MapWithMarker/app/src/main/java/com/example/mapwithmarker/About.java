package com.example.mapwithmarker;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;
import com.example.mapwithmarker.PixelBay.PixabayApiService;
import com.example.mapwithmarker.PixelBay.PixabayImage;
import com.example.mapwithmarker.PixelBay.PixabayResponse;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class About extends AppCompatActivity {
    private ImageView imageView0, imageView1, imageView2, imageView3, imageView4, imageView5, imageView6;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Initialize ImageViews
        imageView0 = findViewById(R.id.imageView0);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        //imageView4 = findViewById(R.id.imageView4);
        //imageView5 = findViewById(R.id.imageView5);
        //imageView6 = findViewById(R.id.imageView6);

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of the Retrofit interface
        PixabayApiService apiService = retrofit.create(PixabayApiService.class);

        // Make API calls to search for photos based on keywords and load them into ImageViews
        loadImages(apiService, "Paris at  night", imageView0);
        loadImages(apiService, "paris", imageView1);
        loadImages(apiService, "paris restaurant", imageView2);
        loadImages(apiService, "Paris activity", imageView3);

    }

    private void loadImages(PixabayApiService apiService, String keyword, ImageView imageView) {
        // Make an API call to search for photos based on a keyword
        Call<PixabayResponse> call = apiService.searchPhotos("43624621-bfc7c00642558abb036e7faf6", keyword);

        call.enqueue(new Callback<PixabayResponse>() {
            @Override
            public void onResponse(Call<PixabayResponse> call, Response<PixabayResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PixabayResponse pixabayResponse = response.body();
                    if (!pixabayResponse.getHits().isEmpty()) {
                        PixabayImage firstImage = pixabayResponse.getHits().get(0);
                        String imageUrl = firstImage.getWebformatURL();
                        // Use Picasso or another image loading library to load the image into the ImageView
                        Picasso.get().load(imageUrl).into(imageView);
                    } else {
                        // Handle no images found
                        Toast.makeText(About.this, "No Image found for " + keyword, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Handle API error
                    Toast.makeText(About.this, "API error for " + keyword, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PixabayResponse> call, Throwable t) {
                // Handle network or API call failure
                Toast.makeText(About.this, "On failure for " + keyword, Toast.LENGTH_SHORT).show();
            }
        });
    }
}