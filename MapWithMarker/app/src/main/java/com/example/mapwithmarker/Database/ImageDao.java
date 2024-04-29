package com.example.mapwithmarker.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface ImageDao {
    @Insert
    void insertCity(ImageTable imageTable);

    @Query("SELECT * FROM ImageTable WHERE city=:city")
    public  boolean is_taken(String city);

    @Query("SELECT image FROM ImageTable WHERE city=:city")
    String getImages(String city);
}
