package com.example.mapwithmarker;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mapwithmarker.Database.ImageDao;
import com.example.mapwithmarker.Database.MyDatabase;
import com.example.mapwithmarker.Database.ReviewDao;
import com.example.mapwithmarker.Database.ReviewTable;
import com.squareup.picasso.Picasso;
import com.example.mapwithmarker.PixelBay.PixabayApiService;
import com.example.mapwithmarker.PixelBay.PixabayImage;
import com.example.mapwithmarker.PixelBay.PixabayResponse;


import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class About extends AppCompatActivity {
    String city;
    MyDatabase myDb;
    ReviewDao reviewDao;
    ImageDao imageDao;
    ImageView imageView;
    String username;
    private final String[] subjects = {"restaurant", "museum", "monument", "cathedral", "activity"};
    private ImageView imageView1, imageView2, imageView3;
    Button saveButton;
    EditText editText;
    TextView commentSection;
    int ID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        username = getIntent().getStringExtra("USERNAME");

        city = getIntent().getStringExtra("ADDRESS");


        myDb = Room.databaseBuilder(this, MyDatabase.class, "usertable").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
        imageDao= myDb.getImageDao();
        reviewDao = myDb.getReviewDao();


        // Initialize ImageViews
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        saveButton = findViewById(R.id.saveButton);
        editText = findViewById(R.id.comment_editText);
        commentSection = findViewById(R.id.comment_section_textView);
        ((TextView)(findViewById(R.id.AboutTittle_TextView))).setText("Welcome to " + city);



        List<ReviewTable> users = reviewDao.getReview();
        StringBuilder s = new StringBuilder();
        for (ReviewTable r: users){
            s.append(r.getUsername().toUpperCase()).append(" : ").append(r.getReview()).append("\n");
        }
        Log.d("StringBuilder",s.toString());
        commentSection.setText(s.toString());

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of the Retrofit interface
        PixabayApiService apiService = retrofit.create(PixabayApiService.class);

        Random random = new Random();

        int sub1 = random.nextInt(5);
        int sub2 = random.nextInt(5);
        int sub3 = random.nextInt(5);

        // Ensure no two variables have the same number
        while (sub2 == sub1) {
            sub2 = random.nextInt(5);
        }

        while (sub3 == sub1 || sub3 == sub2) {
            sub3 = random.nextInt(5);
        }

        ((TextView)findViewById(R.id.sub1_tag)).setText(" " + subjects[sub1] + " ");
        ((TextView)findViewById(R.id.sub2_tag)).setText(" " + subjects[sub2] + " ");
        ((TextView)findViewById(R.id.sub3_tag)).setText(" " + subjects[sub3] + " ");

        if (!imageDao.is_taken(city)) {
            // Make API calls to search for photos based on keywords and load them into ImageViews
            loadImages(apiService, sub1 + " in " + city, imageView1);
            loadImages(apiService, sub2 + " in " + city, imageView2);
            loadImages(apiService, sub3 + " in " + city, imageView3);
        } else {
            // vue de nuit | resto | monument
            String[] urls = imageDao.getImages(city).split("~");
            Picasso.get().load(urls[sub1]).into(imageView1);
            Picasso.get().load(urls[sub2]).into(imageView2);
            Picasso.get().load(urls[sub3]).into(imageView3);
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = reviewDao.retrieveID(username);
                Log.d("ID 123",username + " ");

                ReviewTable r = new ReviewTable(id, username, editText.getText().toString());
                reviewDao.updateReview(r);

                List<ReviewTable> users = reviewDao.getReview();
                StringBuilder s = new StringBuilder();
                for (ReviewTable re: users){
                    String currUsername = re.getUsername();
                    String comment = re.getReview();
                    if (!comment.equals("")) {
                        s.append(currUsername.toUpperCase()).append(" : ").append(comment).append('\n');
                    }
                }
                commentSection.setText(s.toString());
            }
        });
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