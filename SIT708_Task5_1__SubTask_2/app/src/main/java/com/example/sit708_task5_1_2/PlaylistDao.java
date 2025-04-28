package com.example.sit708_task5_1_2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface PlaylistDao {
    @Insert
    void insert(PlaylistItem item);

    @Query("SELECT * FROM PlaylistItem WHERE userId = :userId")
    List<PlaylistItem> getPlaylistForUser(int userId);
} 