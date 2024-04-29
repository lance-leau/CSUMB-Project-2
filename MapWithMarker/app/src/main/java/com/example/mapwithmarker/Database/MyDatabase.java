package com.example.mapwithmarker.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;

@Database(entities = {UserTable.class, ImageTable.class}, version = 4)

public abstract class MyDatabase extends RoomDatabase {
    public abstract UserDao getDao();
    public abstract ImageDao getImageDao();

    private static volatile MyDatabase INSTANCE;

   /* public static synchronized MyDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyDatabase.class, "my_database")
                    .build();
        }
        return INSTANCE;
    }*/

    public static synchronized MyDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyDatabase.class, "my_database")
                    .addMigrations(Migrations.MIGRATION_1_3)
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
                    UserTable adminUser = new UserTable(0, "admin", "admin", true,"0");//TODO
                    userDao.insertUser(adminUser);
                }
            }
        }).start();
    }

    public void addCity() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ImageDao imageDao = getImageDao();
                if(!imageDao.is_taken("Lyon")){
                    ImageTable imageTable = new ImageTable(0,"Lyon","https://media.cntraveller.com/photos/653783ab9da3a22eb97452f9/4:3/w_4608,h_3456,c_limit/Cheapest_time_to_go_to_Paris_October23_Getty_Images.jpg" );
                    imageDao.insertCity(imageTable);
                }

            }
        }).start();

    }

}
