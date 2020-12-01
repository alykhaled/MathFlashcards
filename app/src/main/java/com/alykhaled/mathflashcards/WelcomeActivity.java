package com.alykhaled.mathflashcards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;

public class WelcomeActivity extends AppCompatActivity {
    private RecyclerView mInterestsView;
    private ArrayList<ListItem> mListsList;
    private ListAdapter mListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mInterestsView = findViewById(R.id.interestsView);

        GridLayoutManager layoutmanger = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        mInterestsView.setLayoutManager(layoutmanger);
        mListsList = new ArrayList<>();
        mListsList.add(new ListItem("","Math",new ArrayList<CardItem>(),"https://placekitten.com/300/300"));
        mListsList.add(new ListItem("","Physics",new ArrayList<CardItem>(),"https://placekitten.com/300/300"));
        mListsList.add(new ListItem("","Chemistry",new ArrayList<CardItem>(),"https://placekitten.com/300/300"));
        mListsList.add(new ListItem("","Languages",new ArrayList<CardItem>(),"https://i.picsum.photos/id/524/300/300.jpg?hmac=MpxUkv6gcBsbfdS64thmQBCSgpyfNIXq2Y8_Gx3SupA"));
        mListsList.add(new ListItem("","Hello",new ArrayList<CardItem>(),"https://placekitten.com/300/300"));
        mListsList.add(new ListItem("","Hello",new ArrayList<CardItem>(),"https://via.placeholder.com/150"));
        mListsList.add(new ListItem("","Hello",new ArrayList<CardItem>(),"https://placekitten.com/300/300"));
        mListsList.add(new ListItem("","Hello",new ArrayList<CardItem>(),"https://placekitten.com/300/300"));
        mListAdapter = new ListAdapter(WelcomeActivity.this,mListsList);
        mInterestsView.setAdapter(mListAdapter);
    }
}