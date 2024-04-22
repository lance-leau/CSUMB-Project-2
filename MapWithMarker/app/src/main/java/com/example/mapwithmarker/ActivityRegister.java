package com.example.mapwithmarker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.TaskStackBuilder;

import com.example.mapwithmarker.Database.DBHelper2;

public class ActivityRegister extends AppCompatActivity {
    EditText etUser, etPwd, etRepwd;
    Button btnRegister;
    //DBHelper dbHelper;

    DBHelper2 dbHelper;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper2(this);
        setContentView(R.layout.activity_register);
        etUser = findViewById(R.id.etUsername);
        etPwd = findViewById(R.id.etPassword);
        etRepwd = findViewById(R.id.etRePassword);

        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view ) {
                String user, pwd, rePwd;
                user = etUser.getText().toString();
                pwd = etPwd.getText().toString();
                rePwd = etRepwd.getText().toString();
                if(user.equals("") || pwd.equals("") || rePwd.equals("")){
                    Toast.makeText(ActivityRegister.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                }else {
                    if(pwd.equals(rePwd)){

                        if(dbHelper.checkUsername(user) || dbHelper.isAdmin(user, pwd)){//changes
                            Toast.makeText(ActivityRegister.this, "User Already Exists", Toast.LENGTH_LONG).show();
                            return;
                        }
                        //Proceed with the register

                        boolean success=  dbHelper.insertData(user, pwd);
                        if(success){
                            Toast.makeText(ActivityRegister.this, "User Registered Successfully", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(ActivityRegister.this, ActivityLogin.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(ActivityRegister.this, "User Registered Failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else {
                        Toast.makeText(ActivityRegister.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}