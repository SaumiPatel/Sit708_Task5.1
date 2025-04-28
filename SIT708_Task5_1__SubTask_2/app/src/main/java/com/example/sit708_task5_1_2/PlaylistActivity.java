package com.example.sit708_task5_1_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import java.util.ArrayList;
import java.util.List;

public class PlaylistActivity extends AppCompatActivity {
    ListView playlistListView;
    AppDatabase db;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        playlistListView = findViewById(R.id.playlistListView);
        userId = getIntent().getIntExtra("USER_ID", -1);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "itube-db").allowMainThreadQueries().build();

        List<PlaylistItem> playlist = db.playlistDao().getPlaylistForUser(userId);
        List<String> urls = new ArrayList<>();
        for (PlaylistItem item : playlist) {
            urls.add(item.youtubeUrl);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, urls);
        playlistListView.setAdapter(adapter);

        playlistListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, VideoPlayerActivity.class);
            intent.putExtra("YOUTUBE_URL", urls.get(position));
            startActivity(intent);
        });
    }
} 