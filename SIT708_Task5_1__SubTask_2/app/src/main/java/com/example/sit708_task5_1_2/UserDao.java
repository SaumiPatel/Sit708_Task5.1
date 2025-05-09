package com.example.sit708_task5_1_2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM User WHERE username = :username AND password = :password")
    User login(String username, String password);

    @Query("SELECT * FROM User WHERE username = :username")
    User getUserByUsername(String username);
} 