package com.example.mapwithmarker.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserTable.class}, version = 3)

public abstract class MyDatabase extends RoomDatabase {
    public abstract UserDao getDao();

    private static volatile MyDatabase INSTANCE;

    public static synchronized MyDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyDatabase.class, "my_database")
                    .build();
        }
        return INSTANCE;
    }

    public void addAdminUser() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                UserDao userDao = getDao();
                if (!userDao.is_taken("admin")) { // Check if "admin" user already exists
                    UserTable adminUser = new UserTable(0, "admin", "admin", true,"None");//TODO
                    userDao.insertUser(adminUser);
                }
            }
        }).start();
    }

}
