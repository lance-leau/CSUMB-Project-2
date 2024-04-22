package com.example.mapwithmarker.Database;


import android.content.Context;

import androidx.room.Room;

public class DBHelper2 {
    private AppDatabase appDatabase;
    private UserDao userDao;

    public DBHelper2(Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "register.db")
                .allowMainThreadQueries() // For simplicity, allow queries on the main thread (not recommended for production)
                .build();
        userDao = appDatabase.userDao();
    }

    public boolean insertData(String username, String password) {
        User user = new User(username, password);
        userDao.insert(user);
        return true; // You might want to handle exceptions and return false on failure
    }

    public boolean checkUsername(String username) {
        return userDao.countUsers(username) > 0;
    }

    public boolean checkUser(String username, String pwd) {
        return userDao.checkUser(username, pwd) > 0;
    }

    public boolean isAdmin(String username, String pwd) {
        return userDao.isAdmin() > 0 && username.equals("admin") && pwd.equals("admin");
    }
}
