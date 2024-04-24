package com.example.mapwithmarker;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mapwithmarker.Database.MyDatabase;
import com.example.mapwithmarker.Database.UserDao;
import com.example.mapwithmarker.Database.UserTable;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mapwithmarker.Database.MyDatabase;
import com.example.mapwithmarker.Database.UserDao;
import com.example.mapwithmarker.Database.UserTable;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ActivityAdmin extends AppCompatActivity {
    private LinearLayout userListLayout;
    private MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        userListLayout = findViewById(R.id.users);
        myDatabase = MyDatabase.getInstance(this);

        // Load users from the database and display them
        loadUsersFromDatabase();
    }

    private void loadUsersFromDatabase() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<UserTable> userList = myDatabase.getDao().getAllUsers();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Clear any previous views in userListLayout
                        userListLayout.removeAllViews();

                        // Add TextViews for each user to userListLayout
                        for (UserTable user : userList) {
                            TextView userTextView = new TextView(ActivityAdmin.this);
                            userTextView.setText("Username: " + user.getUsername() +
                                    ", isAdmin: " + user.isAdmin());
                            userTextView.setTextSize(16);
                            userListLayout.addView(userTextView);
                        }
                    }
                });
            }
        }).start();
    }
}

