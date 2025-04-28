package com.example.sit708_task5_1_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class HomeActivity extends AppCompatActivity {
    EditText youtubeUrl;
    Button playBtn, addToPlaylistBtn, myPlaylistBtn;
    int userId;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        youtubeUrl = findViewById(R.id.youtubeUrl);
        playBtn = findViewById(R.id.playBtn);
        addToPlaylistBtn = findViewById(R.id.addToPlaylistBtn);
        myPlaylistBtn = findViewById(R.id.myPlaylistBtn);

        userId = getIntent().getIntExtra("USER_ID", -1);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "itube-db").allowMainThreadQueries().build();

        playBtn.setOnClickListener(v -> {
            String url = youtubeUrl.getText().toString().trim();
            if (url.isEmpty() || !url.startsWith("http")) {
                Toast.makeText(this, "Please enter a valid URL", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(this, VideoPlayerActivity.class);
            intent.putExtra("YOUTUBE_URL", url);
            startActivity(intent);
        });

        addToPlaylistBtn.setOnClickListener(v -> {
            String url = youtubeUrl.getText().toString().trim();
            if (url.isEmpty() || !url.startsWith("http")) {
                Toast.makeText(this, "Please enter a valid URL", Toast.LENGTH_SHORT).show();
                return;
            }
            PlaylistItem item = new PlaylistItem();
            item.userId = userId;
            item.youtubeUrl = url;
            db.playlistDao().insert(item);
            Toast.makeText(this, "Added to playlist!", Toast.LENGTH_SHORT).show();
        });

        myPlaylistBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, PlaylistActivity.class);
            intent.putExtra("USER_ID", userId);
            startActivity(intent);
        });
    }
} 