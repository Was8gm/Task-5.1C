package com.example.task51c;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<NewsItems> newsList;
    RecyclerView topNewsRecycleView;
    RecyclerView.LayoutManager layoutManager4Top;
    TopNews topNews;
    private Handler handler;
    private Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialNews();
        topNewsRecycleView = findViewById(R.id.topNewsView);
        //set top news images is horizontally scrolling
        layoutManager4Top = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        topNews= new TopNews(this, newsList);
        topNewsRecycleView.setLayoutManager(layoutManager4Top);
        topNewsRecycleView.setAdapter(topNews);
        //news list fragment will come firstly
        ListFragment listFragment = new ListFragment(newsList);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.testFragment, listFragment);
        fragmentTransaction.commit();
        startAutoScroll();

    }
    private void startAutoScroll(){
        int count = topNews.getItemCount();
        handler = new Handler();
        if(count>0){
            if(runnable!=null){
                handler.removeCallbacks(runnable);
            }
            runnable = new Runnable() {
                int currentPosition = topNewsRecycleView.getVerticalScrollbarPosition();
                @Override
                public void run() {
                    if (currentPosition == count - 1) {
                        //if it's end of recycle, it will scroll to first image
                        currentPosition = 0;
                    } else {
                        currentPosition++;
                    }
                    topNewsRecycleView.smoothScrollToPosition(currentPosition);
                    //play the next image every 5s
                    handler.postDelayed(this, 5000);
                }
            };
            handler.postDelayed(runnable,5000);
        }

    }
    private void initialNews() {
        newsList = new ArrayList<>();
        newsList.add(new NewsItems(
                getString(R.string.news1_title),
                getString(R.string.news1_desc),
                R.drawable.news1
        ));
        newsList.add(new NewsItems(
                getString(R.string.news2_title),
                getString(R.string.news2_desc),
                R.drawable.news2
        ));
        newsList.add(new NewsItems(
                getString(R.string.news3_title),
                getString(R.string.news3_desc),
                R.drawable.news3
        ));
        newsList.add(new NewsItems(
                getString(R.string.news4_title),
                getString(R.string.news4_desc),
                R.drawable.news4
        ));
        newsList.add(new NewsItems(
                getString(R.string.news5_title),
                getString(R.string.news5_desc),
                R.drawable.news5
        ));
        newsList.add(new NewsItems(
                getString(R.string.news6_title),
                getString(R.string.news6_desc),
                R.drawable.news6
        ));
    }
}