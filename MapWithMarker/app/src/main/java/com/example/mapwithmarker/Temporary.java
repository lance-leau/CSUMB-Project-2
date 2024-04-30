package com.example.mapwithmarker;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.mapwithmarker.Database.MyDatabase;
import com.example.mapwithmarker.Database.ReviewDao;
import com.example.mapwithmarker.Database.ReviewTable;

import java.util.List;

public class Temporary extends AppCompatActivity {

    MyDatabase myDb;
    ReviewDao reviewDao;
    ReviewTable reviewTable;
    Button saveButton;

    TextView textView;

    EditText editText;

    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporary);
        editText = findViewById(R.id.imageViewCity);
        saveButton = findViewById(R.id.saveButton);
        textView = findViewById(R.id.showReview);

        myDb = Room.databaseBuilder(this, MyDatabase.class, "usertable").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();

        reviewDao = myDb.getReviewDao();

        List<ReviewTable> users = reviewDao.getReview();
        StringBuilder s = new StringBuilder();
        for (ReviewTable r: users){
            s.append(r.getReview()).append("\n");
        }

        textView.append(s.toString());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Text", editText.getText().toString());
                int id = reviewDao.retrieveID("Kim");
                ReviewTable r = new ReviewTable( 0,"Kim", editText.getText().toString());
                reviewDao.updateReview(r);

                List<ReviewTable> users = reviewDao.getReview();
                StringBuilder s = new StringBuilder();
                for (ReviewTable re: users){
                    s.append(re.getReview()).append('\n');
                }
                textView.setText(s.toString());
                //Toast.makeText(Temporary.this, reviewDao.getReview("Kim"), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
