package com.example.mapwithmarker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.mapwithmarker.Database.UserDao;
import com.example.mapwithmarker.databinding.ActivityLoginBinding;
import com.example.mapwithmarker.databinding.ActivityRegisterBinding;


public class ActivityLogin  extends AppCompatActivity {

    private static final String IS_USER_ADMIN = "IS_USER_ADMIN";

    ActivityLoginBinding binding;

    EditText etUser, etPwd;
    Button btnLogin;
    MyDatabase myDb;
    UserDao userDao;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_login);
        etUser = findViewById(R.id.etUsername);
        etPwd = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        myDb = Room.databaseBuilder(this, MyDatabase.class, "usertable").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
        userDao = myDb.getDao();
        Log.d("WHY","ARE U WITH ME");
        myDb.addAdminUser();
        Log.d("WHY","Works ?");


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("WHY","1");

                if(userDao.isAdminUser(etUser.getText().toString())) {
                    Toast.makeText(ActivityLogin.this, "An admin has login", Toast.LENGTH_SHORT).show();
                }
                if(userDao.login(etUser.getText().toString(),etPwd.getText().toString())){
                    Log.d("WHY","2");
                    Intent intent = new Intent(ActivityLogin.this, Landing.class);
                    intent.putExtra("USERNAME", etUser.getText().toString());
                    intent.putExtra("PASSWORD", etPwd.getText().toString());
                    intent.putExtra("ISADMIN", userDao.isAdminUser(etUser.getText().toString()));
                    startActivity(intent);
                }
                else {
                    Toast.makeText(ActivityLogin.this, "Invalid Name or password", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}