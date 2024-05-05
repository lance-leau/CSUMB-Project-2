package com.example.mapwithmarker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mapwithmarker.Database.MyDatabase;
import com.example.mapwithmarker.Database.ReviewDao;
import com.example.mapwithmarker.Database.UserDao;
import com.example.mapwithmarker.Database.UserTable;

import java.util.List;

import androidx.room.Room;

public class ActivityAdmin extends AppCompatActivity {
    private TableLayout userListLayout;
    private MyDatabase myDb;
    private UserDao userDao;

    ReviewDao reviewDao;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btnBack = findViewById(R.id.backButton);

        userListLayout = findViewById(R.id.usersTable);
        userListLayout.setGravity(Gravity.CENTER_HORIZONTAL); // Center the table horizontally
        myDb = Room.databaseBuilder(this, MyDatabase.class, "usertable")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        userDao = myDb.getDao();
        reviewDao = myDb.getReviewDao();
        userListLayout.removeAllViews();

        showAllUsers();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityAdmin.this, ActivitySettings.class);
                intent.putExtra("USERNAME", getIntent().getStringExtra("USERNAME"));
                startActivity(intent);
            }
        });

    }

    private void showAllUsers() {
        List<UserTable> users = userDao.getAllUsers();

        // Add column titles if they are not already added
        if (userListLayout.getChildCount() == 0) {
            addColumnTitles(); // Add column titles separately
        }

        for (UserTable user : users) {
            TableRow row = new TableRow(this);
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT
            );
            row.setLayoutParams(layoutParams);
            row.setGravity(Gravity.CENTER_HORIZONTAL);

            // Add username TextView
            TextView usernameTextView = new TextView(this);
            usernameTextView.setText(user.getUsername());
            usernameTextView.setGravity(Gravity.CENTER);
            row.addView(usernameTextView);

            // Add CheckBox for admin status
            CheckBox adminCheckBox = new CheckBox(this);
            adminCheckBox.setChecked(user.isAdmin());
            adminCheckBox.setGravity(Gravity.CENTER);

            // Handle checkbox click
            adminCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!user.isAdmin()) {
                        showConfirmationDialog(user, adminCheckBox);
                    }
                }
            });

            adminCheckBox.setEnabled(!user.isAdmin());
            row.addView(adminCheckBox);

            // Add delete button
            Button deleteButton = new Button(this);
            deleteButton.setText("Delete");
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteUser(user.getUsername());
                }
            });
            row.addView(deleteButton);

            userListLayout.addView(row);
        }
    }

    private void addColumnTitles() {
        TableRow headerRow = new TableRow(this);
        headerRow.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
        ));
        headerRow.setGravity(Gravity.CENTER_HORIZONTAL);

        // Username Title
        TextView usernameTitle = new TextView(this);
        usernameTitle.setText("Username");
        usernameTitle.setGravity(Gravity.CENTER);
        usernameTitle.setPadding(10, 10, 10, 10);
        usernameTitle.setTextSize(18); // Set text size
        usernameTitle.setTypeface(Typeface.DEFAULT_BOLD); // Set bold
        headerRow.addView(usernameTitle);

        // Admin Status Title
        TextView adminTitle = new TextView(this);
        adminTitle.setText("Admin Status");
        adminTitle.setGravity(Gravity.CENTER);
        adminTitle.setPadding(10, 10, 10, 10);
        adminTitle.setTextSize(18); // Set text size
        adminTitle.setTypeface(Typeface.DEFAULT_BOLD); // Set bold
        headerRow.addView(adminTitle);

        // Delete Title
        TextView deleteTitle = new TextView(this);
        deleteTitle.setText("Delete");
        deleteTitle.setGravity(Gravity.CENTER);
        deleteTitle.setPadding(10, 10, 10, 10);
        deleteTitle.setTextSize(18); // Set text size
        deleteTitle.setTypeface(Typeface.DEFAULT_BOLD); // Set bold

        headerRow.addView(deleteTitle);

        userListLayout.addView(headerRow);
    }

        private void showConfirmationDialog(UserTable user, CheckBox adminCheckBox) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Make User Admin");
            builder.setMessage("Do you want to make this user an admin?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Set the user as admin and update the database
                    user.setAdmin(true);
                    Log.d("user", user.getCities());
                    userDao.updateUser(user);
                    Log.d("user", user.getCities());
                    // Set the checkbox to checked
                    adminCheckBox.setChecked(true);
                    // Refresh the user list
                    refreshUserList();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    adminCheckBox.setChecked(false);
                    Log.d("user", user.getCities());
                    dialog.dismiss(); // Dismiss the dialog
                }
            });
            builder.create().show();
        }

    private void deleteUser(String username) {
        if (!userDao.isAdminUser(username)) {
            userDao.deleteUser(username);
            reviewDao.deleteReview(username);
            refreshUserList();
        } else {
            Toast.makeText(ActivityAdmin.this, "An admin account can't be deleted ", Toast.LENGTH_LONG).show();
        }
    }

    private void refreshUserList() {
        userListLayout.removeAllViews();
        showAllUsers();
    }
}
