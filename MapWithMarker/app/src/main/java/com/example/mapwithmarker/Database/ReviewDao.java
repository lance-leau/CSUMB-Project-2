package com.example.mapwithmarker.Database;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ReviewDao {

    @Insert
    void insertReview(ReviewTable reviewTable);

    @Update
    void updateReview(ReviewTable reviewTable);

    @Query("SELECT * FROM ReviewTable WHERE userName=:username")
    public  boolean is_taken(String username);

    @Query("SELECT id FROM ReviewTable WHERE userName=:username")
    int retrieveID(String username);

    @Query("SELECT * FROM ReviewTable")
    List<ReviewTable> getReview();

    @Query("SELECT username FROM ReviewTable WHERE username=:username")
    String getReviewByUsername(String username);
}
