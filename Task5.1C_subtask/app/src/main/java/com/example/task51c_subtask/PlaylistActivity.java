package com.example.task51c_subtask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
public class PlaylistActivity extends AppCompatActivity {
    private ListView listViewPlaylist;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> playlist;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);


        Intent intent = getIntent();
        if (intent != null) {
            username = intent.getStringExtra("username");
        }


        listViewPlaylist = findViewById(R.id.listViewPlaylist);
        playlist = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, playlist);
        listViewPlaylist.setAdapter(adapter);


        retrievePlaylistFromDatabase();

        listViewPlaylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String videoUrl = playlist.get(position);


                Intent intent = new Intent(PlaylistActivity.this, youtubeActivity.class);
                intent.putExtra("videoUrl", videoUrl);
                startActivity(intent);
            }
        });
    }

    private void retrievePlaylistFromDatabase() {
        // Create an instance of the database helper class
        Database db = new Database(PlaylistActivity.this);

        // Get playlist data from the database
        playlist = db.retrievePlaylist(username);

        // Update the adapter to reflect the new playlist data
        adapter.clear();
        adapter.addAll(playlist);
        adapter.notifyDataSetChanged();
    }
}