package com.example.sit708_task5_1_2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class VideoPlayerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        String url = getIntent().getStringExtra("YOUTUBE_URL");
        String videoId = extractVideoId(url);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0);
            }
        });
    }

    private String extractVideoId(String url) {
        // Improved extraction for common YouTube URL formats
        if (url == null) return "";
        String videoId = "";
        if (url.contains("v=")) {
            int start = url.indexOf("v=") + 2;
            int end = url.indexOf('&', start);
            if (end == -1) end = url.length();
            videoId = url.substring(start, end);
        } else if (url.contains("youtu.be/")) {
            int start = url.indexOf("youtu.be/") + 9;
            int end = url.indexOf('?', start);
            if (end == -1) end = url.length();
            videoId = url.substring(start, end);
        }
        return videoId;
    }
} 