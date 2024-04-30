package com.example.mapwithmarker.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class ReviewTable {

    @PrimaryKey(autoGenerate = true)

    private int id;
    private String username;
    private String review;

    public ReviewTable(int id, String username, String review) {
        this.id = id;
        this.username = username;
        this.review = review;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}