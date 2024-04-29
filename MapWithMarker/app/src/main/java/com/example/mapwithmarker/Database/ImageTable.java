package com.example.mapwithmarker.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class ImageTable {
    @PrimaryKey(autoGenerate = true)

    private int id;
    private String city;
    private String image;

    public ImageTable(int id, String city, String image) {
        this.id = id;
        this.city = city;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
