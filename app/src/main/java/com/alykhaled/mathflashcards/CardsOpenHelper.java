package com.alykhaled.mathflashcards;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.alykhaled.mathflashcards.CardsDatabaseContract.CardsInfo;

public class CardsOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "cards.db";
    public static final int DATABASE_VERSION = 1;
    public CardsOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CardsInfo.SQL_CREATE_TABLE);
        ContentValues values = new ContentValues();
        values.put(CardsInfo.COLUMN_CARD_ID, "1");
        values.put(CardsInfo.COLUMN_CARD_TITLE, "math");
        values.put(CardsInfo.COLUMN_CARD_ANSWER, "x + y = z");

        long newRowId = db.insert(CardsInfo.TABLE_NAME, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
