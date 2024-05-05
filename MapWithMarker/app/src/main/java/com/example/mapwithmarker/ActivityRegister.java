package com.example.mapwithmarker;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.TaskStackBuilder;
import androidx.room.Room;

import com.example.mapwithmarker.Database.MyDatabase;
import com.example.mapwithmarker.Database.ReviewDao;
import com.example.mapwithmarker.Database.ReviewTable;
import com.example.mapwithmarker.Database.UserTable;
import com.example.mapwithmarker.Database.UserDao;
import com.example.mapwithmarker.databinding.ActivityRegisterBinding;


public class ActivityRegister extends AppCompatActivity {
    EditText etUser, etPwd, etRepwd;
    Button btnRegister;
    ActivityRegisterBinding binding;
    MyDatabase myDb;
    UserDao userDao;

    ReviewDao reviewDao;

    public static boolean isAllowed = false;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setContentView(R.layout.activity_register);

        etUser = findViewById(R.id.etUsername);
        etPwd = findViewById(R.id.etPassword);
        etRepwd = findViewById(R.id.etRePassword);

        btnRegister = findViewById(R.id.btnRegister);

        myDb = Room.databaseBuilder(this, MyDatabase.class, "usertable").allowMainThreadQueries()
                        .fallbackToDestructiveMigration().build();

        userDao= myDb.getDao();
        reviewDao = myDb.getReviewDao();
        myDb.addAdminUser();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredUsername = etUser.getText().toString();
                if(userDao.is_taken(enteredUsername)) {
                    Toast.makeText(ActivityRegister.this, "Username already exists", Toast.LENGTH_SHORT).show();
                } else {
                    if(!etPwd.getText().toString().equals(etRepwd.getText().toString())){
                        Toast.makeText(ActivityRegister.this, "The passwords does not match", Toast.LENGTH_SHORT).show();;
                    }
                    else {
                        UserTable userTable = new UserTable(0, enteredUsername, etPwd.getText().toString(), false,"0");
                        userDao.insertUser(userTable);

                        ReviewTable reviewTable = new ReviewTable(0, enteredUsername, "");
                        reviewDao.insertReview(reviewTable);

                        Toast.makeText(ActivityRegister.this, "Username added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ActivityRegister.this, ActivityLogin.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}