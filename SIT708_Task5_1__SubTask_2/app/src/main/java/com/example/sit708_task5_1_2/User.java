package com.example.sit708_task5_1_2;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String fullName;
    public String username;
    public String password;
} 