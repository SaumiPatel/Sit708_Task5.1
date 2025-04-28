package com.example.sit708_task5_1_2;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PlaylistItem {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int userId;
    public String youtubeUrl;
} 