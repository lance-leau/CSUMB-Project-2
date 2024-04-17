package com.example.mapwithmarker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBName = "register.db";

    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(username TEXT primary key, password TEXT)");

        ContentValues contentValues = new ContentValues();
        contentValues.put("username", "admin");
        contentValues.put("password", "123");
        db.insert("users", null, contentValues);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
    }

    public boolean insertData(String username, String password){
        SQLiteDatabase myDB =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = myDB.insert("users", null, contentValues);

        return result != -1;
    }

    public boolean checkUsername(String username){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select *  from users where username = ? ", new String[]{username});
        return cursor.getCount() > 0;
    }

    public boolean checkUser(String username, String pwd){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor =  myDB.rawQuery("select *  from users where username = ? and password = ? ", new String[]{username, pwd});
        return cursor.getCount() > 0;
    }

    public boolean isAdmin(String username, String pwd){
        return username.equals("admin") && pwd.equals("123");
    }

    public boolean isAdminUser() {
        // Check if the logged-in user is an admin using the isAdmin method from DBHelper
        String username = "admin"; // Assuming this is the admin username
        String password = "123"; // Assuming this is the admin password
        return isAdmin(username, password);
    }

}
