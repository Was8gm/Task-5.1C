package com.example.task51c;

import static java.security.AccessController.getContext;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private RecyclerView mainNewsRecycleView;
    private RecyclerView.LayoutManager layoutManager4Main;
    private ListAdapter mainNewsAdapter;
    private ArrayList<NewsItems> newsList;

    public ListFragment() {
        // Required empty public constructor
    }
    public ListFragment(ArrayList<NewsItems> newsList) {
        this.newsList = newsList;
    }


    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
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
        View rootView = inflater.inflate(R.layout.news_list_fragment, container, false);
        mainNewsRecycleView = rootView.findViewById(R.id.mainNewsRecycleView);
        mainNewsAdapter = new ListAdapter(getContext(),newsList);
        layoutManager4Main = new GridLayoutManager(getContext(), 2);
        mainNewsRecycleView.setLayoutManager(layoutManager4Main);
        mainNewsRecycleView.setAdapter(mainNewsAdapter);
        return rootView;
    }
}
