package com.example.mapwithmarker.Database;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ImageTableTest {

    @Test
    void getId() {
        ImageTable image = new ImageTable(1, "New York", "image_url");
        assertEquals(1, image.getId());
    }

    @Test
    void getCity() {
        ImageTable image = new ImageTable(1, "New York", "image_url");
        assertEquals("New York", image.getCity());
    }

    @Test
    void getImage() {
        ImageTable image = new ImageTable(1, "New York", "image_url");
        assertEquals("image_url", image.getImage());
    }

    @Test
    void setId() {
        ImageTable image = new ImageTable(1, "New York", "image_url");
        image.setId(2);
        assertEquals(2, image.getId());
    }

    @Test
    void setCity() {
        ImageTable image = new ImageTable(1, "New York", "image_url");
        image.setCity("Los Angeles");
        assertEquals("Los Angeles", image.getCity());
    }

    @Test
    void setImage() {
        ImageTable image = new ImageTable(1, "New York", "image_url");
        image.setImage("new_image_url");
        assertEquals("new_image_url", image.getImage());
    }
}