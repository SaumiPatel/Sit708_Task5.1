package com.example.sit708_task5_1_2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, PlaylistItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract PlaylistDao playlistDao();
} 