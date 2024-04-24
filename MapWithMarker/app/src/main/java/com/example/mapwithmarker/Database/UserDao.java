package com.example.mapwithmarker.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUser(UserTable userTable);

    @Query("SELECT * FROM UserTable WHERE userName=:username")
    public boolean is_taken(String username);

    @Query("SELECT * FROM UserTable WHERE userName=:username AND password=:password")
    public boolean login(String username, String password);

    @Query("SELECT isAdmin FROM UserTable WHERE userName=:username")
    public boolean isAdminUser(String username);

    @Query("SELECT * FROM UserTable")
    List<UserTable> getAllUsers();

    @Query("Delete from UserTable Where username = :username")
    void deleteUser(String username);
}