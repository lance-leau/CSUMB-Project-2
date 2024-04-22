package com.example.mapwithmarker.Database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM users WHERE username = :username")
    User getUserByUsername(String username);

    @Query("SELECT COUNT(*) FROM users WHERE username = :username")
    int countUsers(String username);

    @Query("SELECT COUNT(*) FROM users WHERE username = :username AND password = :password")
    int checkUser(String username, String password);

    @Query("SELECT COUNT(*) FROM users WHERE username = 'admin' AND password = 'admin'")
    int isAdmin();
}
