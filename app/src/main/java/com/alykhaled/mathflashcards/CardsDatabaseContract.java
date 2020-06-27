package com.alykhaled.mathflashcards;

import android.provider.BaseColumns;

public final class CardsDatabaseContract {
    private CardsDatabaseContract() {}

    public static final class CardsInfo implements BaseColumns
    {
        public static final String TABLE_NAME = "card_info";
        public static final String COLUMN_CARD_ID = "card_id";
        public static final String COLUMN_CARD_TITLE= "card_title";
        public static final String COLUMN_CARD_ANSWER= "card_answer";
        public static final String COLUMN_CARD_LIST = "card_list";

        public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY, " + COLUMN_CARD_ID+" TEXT UNIQUE NOT NULL, " + COLUMN_CARD_TITLE + " TEXT , " + COLUMN_CARD_ANSWER+ " TEXT)";
    }
    public static final class ListInfo implements BaseColumns
    {
        public static final String TABLE_NAME = "list_info";
        public static final String COLUMN_LIST_ID = "list_id";
        public static final String COLUMN_LIST_TITLE= "list_title";
        public static final String COLUMN_LIST_SIZE= "list_size";

        public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY, " + COLUMN_LIST_ID +" TEXT UNIQUE NOT NULL, " + COLUMN_LIST_TITLE + " TEXT , " + COLUMN_LIST_SIZE + " TEXT)";
    }
}
