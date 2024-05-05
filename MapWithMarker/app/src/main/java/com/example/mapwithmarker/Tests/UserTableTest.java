package com.example.mapwithmarker.Tests;

import static org.junit.jupiter.api.Assertions.*;

import com.example.mapwithmarker.Database.UserTable;

import org.junit.Test;

public class UserTableTest {

    @Test
    public void getId() {
        UserTable user = new UserTable(1, "john_doe", "password123", false, "New York");
        assertEquals(1, user.getId());
    }

    @Test
    public void getUsername() {
        UserTable user = new UserTable(1, "john_doe", "password123", false, "New York");
        assertEquals("john_doe", user.getUsername());
    }

    @Test
    public void getPassword() {
        UserTable user = new UserTable(1, "john_doe", "password123", false, "New York");
        assertEquals("password123", user.getPassword());
    }

    @Test
    public void isAdmin() {
        UserTable user = new UserTable(1, "john_doe", "password123", false, "New York");
        assertFalse(user.isAdmin());
    }


    @Test
    public void setId() {
        UserTable user = new UserTable(1, "john_doe", "password123", false, "New York");
        user.setId(2);
        assertEquals(2, user.getId());
    }

    @Test
    public void setUsername() {
        UserTable user = new UserTable(1, "john_doe", "password123", false, "New York");
        user.setUsername("jane_doe");
        assertEquals("jane_doe", user.getUsername());
    }

    @Test
    public void setPassword() {
        UserTable user = new UserTable(1, "john_doe", "password123", false, "New York");
        user.setPassword("new_password");
        assertEquals("new_password", user.getPassword());
    }

    @Test
    public void setAdmin() {
        UserTable user = new UserTable(1, "john_doe", "password123", false, "New York");
        user.setAdmin(true);
        assertTrue(user.isAdmin());
    }
}