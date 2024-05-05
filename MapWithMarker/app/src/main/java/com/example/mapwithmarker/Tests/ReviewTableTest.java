package com.example.mapwithmarker.Tests;

import static org.junit.jupiter.api.Assertions.*;

import com.example.mapwithmarker.Database.ReviewTable;

import org.junit.jupiter.api.Test;

class ReviewTableTest {

    @Test
    void getId() {
        ReviewTable review = new ReviewTable(1, "john_doe", "Great place!");
        assertEquals(1, review.getId());
    }

    @Test
    void getUsername() {
        ReviewTable review = new ReviewTable(1, "john_doe", "Great place!");
        assertEquals("john_doe", review.getUsername());
    }

    @Test
    void getReview() {
        ReviewTable review = new ReviewTable(1, "john_doe", "Great place!");
        assertEquals("Great place!", review.getReview());
    }

    @Test
    void setId() {
        ReviewTable review = new ReviewTable(1, "john_doe", "Great place!");
        review.setId(2);
        assertEquals(2, review.getId());
    }

    @Test
    void setUsername() {
        ReviewTable review = new ReviewTable(1, "john_doe", "Great place!");
        review.setUsername("jane_doe");
        assertEquals("jane_doe", review.getUsername());
    }

    @Test
    void setReview() {
        ReviewTable review = new ReviewTable(1, "john_doe", "Great place!");
        review.setReview("Not so great place.");
        assertEquals("Not so great place.", review.getReview());
    }
}
