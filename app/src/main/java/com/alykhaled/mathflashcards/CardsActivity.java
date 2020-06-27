package com.alykhaled.mathflashcards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class CardsActivity extends AppCompatActivity {

    public ViewPager cardsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
        cardsList = findViewById(R.id.cardsDetailsView);
    }
}