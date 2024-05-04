package com.example.task51c;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.security.PublicKey;
import java.util.ArrayList;

public class TopNews extends RecyclerView.Adapter<TopNews.MyViewHolder> {
    private Context mContext;
    private  ArrayList<NewsItems> newsList;


    public TopNews(Context mContext, ArrayList<NewsItems> newsList) {
        this.mContext = mContext;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public TopNews.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.top_news_pic, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopNews.MyViewHolder holder, int position) {
        holder.topNewsImage.setImageResource(newsList.get(position).getImage());
        //set the image to suit imageView
        holder.topNewsImage.setScaleType(ImageView.ScaleType.FIT_XY);


    }



    public int getItemCount() {
        return newsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView topNewsImage;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            topNewsImage = itemView.findViewById(R.id.newImage);
        }

    }
}
