package com.example.task51c;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewsDetailFragment  extends Fragment {


        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    private String news_title;
    private String news_desc;
    private TextView news_title_view, news_desc_view;
    private ImageView news_image_view;
    private int news_image;

        public NewsDetailFragment() {
            // Required empty public constructor
        }


        public static NewsDetailFragment newInstance(String param1, String param2) {
            NewsDetailFragment fragment = new NewsDetailFragment();
            Bundle args = new Bundle();
            args.putString(ARG_PARAM1, param1);
            args.putString(ARG_PARAM2, param2);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                mParam1 = getArguments().getString(ARG_PARAM1);
                mParam2 = getArguments().getString(ARG_PARAM2);
            }

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.news_detail_fragment, container, false);

        Bundle bundle = getArguments();
        if(bundle!=null){
            news_title = bundle.getString("news_title");
            news_desc = bundle.getString("news_desc");
            news_image = bundle.getInt("news_image");
        }

        //set title, description, and an image for detailed news fragment
        news_title_view = rootView.findViewById(R.id.news_detail_title);
        news_desc_view = rootView.findViewById(R.id.news_detail_desc);
        news_image_view = rootView.findViewById(R.id.news_detail_image);
        news_title_view.setText(news_title);
        news_desc_view.setText(news_desc);
        news_image_view.setImageResource(news_image);
        news_image_view.setScaleType(ImageView.ScaleType.FIT_XY);
        return rootView;
    }
}
