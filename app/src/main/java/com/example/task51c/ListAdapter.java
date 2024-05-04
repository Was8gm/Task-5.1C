package com.example.task51c;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder2> {
    private ArrayList<NewsItems> newsList;
    private Context context;
    private int position;

    public ListAdapter(Context context, ArrayList<NewsItems> newsList) {
        this.newsList = newsList;
        this.context = context;
    }

    @NonNull
    @Override
    public  ListAdapter.MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_card_item, parent, false);
        return new  ListAdapter.MyViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, @SuppressLint("RecyclerView") int position) {
        holder.mainNewsImage.setImageResource(newsList.get(position).getImage());
        holder.mainNewsImage.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.mainNewsTitles.setText(newsList.get(position).getTitle());
        //set listener for every cardView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create a new fragment
                NewsDetailFragment fragment = new NewsDetailFragment();
                //translate needed data
                Bundle myBundle = new Bundle();
                myBundle.putString("news_title",newsList.get(position).getTitle());
                myBundle.putString("news_desc",newsList.get(position).getDesc());
                myBundle.putInt("news_image",newsList.get(position).getImage());
                fragment.setArguments(myBundle);
                //replace news list fragment with detailed news fragment
                FragmentManager fragmentManager = ((MainActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.testFragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }


    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class MyViewHolder2 extends RecyclerView.ViewHolder {
        ImageView mainNewsImage;
        TextView mainNewsTitles;

        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);
            mainNewsImage = itemView.findViewById(R.id.news_item_image);
            mainNewsTitles = itemView.findViewById(R.id.news_item_title);
        }
    }
}
