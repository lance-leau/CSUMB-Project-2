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
                if(!imageDao.is_taken("Rome")){
                    ImageTable imageTable = new ImageTable(0,"Rome","https://i.pinimg.com/736x/52/7f/20/527f20de41723fb99114c045f211a877.jpg~https://www.my-webspot.com/upload/blog/195/article/_470x410_0038_the-best-activities-in-paris-and-the-attractions-to-enjoy.jpg~https://a.cdn-hotels.com/gdcs/production107/d625/d22d2448-0238-4573-b055-6b079e972dbb.jpg?impolicy=fcrop&w=800&h=533&q=medium" );
                    imageDao.insertCity(imageTable);
                }

            }
        }).start();

    }

}
