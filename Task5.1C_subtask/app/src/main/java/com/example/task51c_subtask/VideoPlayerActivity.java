package com.example.task51c_subtask;


import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class VideoPlayerActivity extends AppCompatActivity {

    private EditText urlEditText;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        Intent intent = getIntent();
        if (intent != null) {
            username = intent.getStringExtra("username");
        }

        urlEditText = findViewById(R.id.youtubeUrlEditText);
        Button playButton = findViewById(R.id.playButton);
        Button addToPlaylistButton = findViewById(R.id.addToPlaylistButton);
        Button viewPlaylistButton = findViewById(R.id.viewPlaylistButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里处理播放按钮点击事件
                String url = urlEditText.getText().toString().trim();
                if (!url.isEmpty()) {
                    // 实现播放逻辑
                    Intent intent = new Intent(VideoPlayerActivity.this, youtubeActivity.class);
                    intent.putExtra("VIDEO_URL", url);
                    startActivity(intent);
                } else {
                    Toast.makeText(VideoPlayerActivity.this, "Please enter the video URL", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 获取当前视频URL
        addToPlaylistButton.setOnClickListener(v -> {
            // 在这里处理添加到播放列表按钮点击事件
            String url = urlEditText.getText().toString().trim();
            if (!url.isEmpty()) {
                // 实现添加到播放列表逻辑
                Database db = new Database(VideoPlayerActivity.this);
                db.addUrlToPlaylist(username, url);
                Toast.makeText(VideoPlayerActivity.this, "The video URL has been added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(VideoPlayerActivity.this, "Please enter the video URL", Toast.LENGTH_SHORT).show();
            }
        });

        viewPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里处理查询我的播放列表按钮点击事件
                // 实现查询播放列表逻辑
                Intent intent = new Intent(VideoPlayerActivity.this, PlaylistActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
    }
}


