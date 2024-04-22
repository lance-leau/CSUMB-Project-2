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



public class ActivityLogin  extends AppCompatActivity {

    private static final String IS_USER_ADMIN = "IS_USER_ADMIN";

    EditText etUser, etPwd;
    Button btnLogin;
    DBHelper dbHelper;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUser = findViewById(R.id.etUsername);
        etPwd = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        dbHelper = new DBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isLoggedId = dbHelper.checkUser(etUser.getText().toString(), etPwd.getText().toString());
                boolean isAdmin = dbHelper.isAdmin(etUser.getText().toString(), etPwd.getText().toString());
                if(isLoggedId || isAdmin){
                    boolean isUserAdmin = dbHelper.isAdmin(etUser.getText().toString(), etPwd.getText().toString());
                    Intent intent = new Intent(ActivityLogin.this, Landing.class);
                    //Log.d("Bruh", isUserAdmin ? "true" : "false");
                    intent.putExtra(IS_USER_ADMIN, isUserAdmin);
                    startActivity(intent);
                }else{
                    Toast.makeText(ActivityLogin.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}