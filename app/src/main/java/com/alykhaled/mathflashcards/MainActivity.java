package com.alykhaled.mathflashcards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.AnimatorSet;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements ListAdapter.OnItemClickListener {
    private CardsOpenHelper mDbOpenHelper;
    private ArrayList<CardItem> mCardsList;
    private RecyclerView mCardsView;
    private ListAdapter mListAdapter;
    private AnimatorSet frontAnim;
    private AnimatorSet backAnim;
    private TextView mAddBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayoutManager LinerManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mCardsView = findViewById(R.id.trackView);

        mCardsView.setHasFixedSize(true);
        mCardsView.setLayoutManager(LinerManager);
        mAddBtn = findViewById(R.id.addListBtn);
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewCardActivity.class);
                startActivity(intent);
            }
        });
        mCardsList = new ArrayList<>();
        mDbOpenHelper = new CardsOpenHelper(this);
        addCard();
    }

    @Override
    protected void onResume() {
        super.onResume();
        addCard();
    }

    private void addCard() {
            String latestId ;
            String title;
            String answer;
            SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
            String[] cardColumns = {CardsDatabaseContract.CardsInfo.COLUMN_CARD_ID, CardsDatabaseContract.CardsInfo.COLUMN_CARD_TITLE, CardsDatabaseContract.CardsInfo.COLUMN_CARD_ANSWER};
            final Cursor cardsCursor = db.query(CardsDatabaseContract.CardsInfo.TABLE_NAME, cardColumns, null, null, null, null, null);
            mCardsList = new ArrayList<>();

            while(cardsCursor.moveToNext())
            {
                latestId = cardsCursor.getString(0);
                title = cardsCursor.getString(1);
                answer = cardsCursor.getString(2);
                mCardsList.add(new CardItem(latestId,title,answer));
            }
            Collections.reverse(mCardsList);
            mListAdapter = new ListAdapter(MainActivity.this,mCardsList);
            mCardsView.setAdapter(mListAdapter);
            mListAdapter.setOnItemClickListener(MainActivity.this);
            cardsCursor.close();
    }
    @Override
    protected void onDestroy() {
        mDbOpenHelper.close();
        super.onDestroy();
    }

    public void onItemClick(int position,ArrayList<CardItem> t ) {
        CardItem clickedItem = t.get(position);
        SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
        db.execSQL("DELETE FROM " + CardsDatabaseContract.CardsInfo.TABLE_NAME + " WHERE card_id = " + clickedItem.getmCardId());
        addCard();

    }
}
