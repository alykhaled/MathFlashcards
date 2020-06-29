package com.alykhaled.mathflashcards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.AnimatorSet;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NewCardActivity extends AppCompatActivity {
    private CardsOpenHelper mDbOpenHelper;
    private Button mSubBtn;
    private Button mDeleteBtn;
    private EditText mQuestionEdit;
    private EditText mAnswerEdit;
    private ArrayList<CardItem> mCardsList;
    private RecyclerView mCardsView;
    private ListAdapter mListAdapter;
    private AnimatorSet frontAnim;
    private AnimatorSet backAnim;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_card);

        mSubBtn = findViewById(R.id.submitBtn);
        mDeleteBtn = findViewById(R.id.deleteBtn);
        mQuestionEdit = findViewById(R.id.questionEdit);
        mAnswerEdit = findViewById(R.id.answerEdit);

        com.alykhaled.mathflashcards.FirebaseUtil.openFbReference("cards");
        mFirebaseDatabase = com.alykhaled.mathflashcards.FirebaseUtil.mFirebaseDatabase;
        mDatabaseReference = com.alykhaled.mathflashcards.FirebaseUtil.mDatabaseReference;
        mCardsList = new ArrayList<>();
        mDbOpenHelper = new CardsOpenHelper(this);
        mSubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCard();
            }
        });
        mDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();

                db.execSQL("DELETE from " + CardsDatabaseContract.CardsInfo.TABLE_NAME);
            }
        });
    }
    private void addCard() {
        /*String latestId = "0";
        String title;
        String answer;
        SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
        String[] cardColumns = {CardsDatabaseContract.CardsInfo.COLUMN_CARD_ID, CardsDatabaseContract.CardsInfo.COLUMN_CARD_TITLE, CardsDatabaseContract.CardsInfo.COLUMN_CARD_ANSWER};
        final Cursor cardsCursor = db.query(CardsDatabaseContract.CardsInfo.TABLE_NAME, cardColumns, null, null, null, null, null);
        mCardsList = new ArrayList<>();
        ContentValues values = new ContentValues();

        while(cardsCursor.moveToNext())
        {
            latestId = cardsCursor.getString(0);
        }
        if (!mQuestionEdit.getText().toString().equals("")&& !mAnswerEdit.getText().toString().equals(""))
        {
            values.put(CardsDatabaseContract.CardsInfo.COLUMN_CARD_ID, Integer.parseInt(latestId)+1);
            values.put(CardsDatabaseContract.CardsInfo.COLUMN_CARD_TITLE, mQuestionEdit.getText().toString());
            values.put(CardsDatabaseContract.CardsInfo.COLUMN_CARD_ANSWER, mAnswerEdit.getText().toString());
            long newRowId = db.insert(CardsDatabaseContract.CardsInfo.TABLE_NAME, null, values);
        }
        cardsCursor.close();*/
        CardItem card = new CardItem(mQuestionEdit.getText().toString(),mAnswerEdit.getText().toString());
        mDatabaseReference.push().setValue(card);
    }
}
