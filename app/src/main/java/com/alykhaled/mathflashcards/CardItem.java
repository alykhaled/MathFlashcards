package com.alykhaled.mathflashcards;

public class CardItem {
    private String mCardId;
    private String mCardQuestion;
    private String mCardAnswer;

    public CardItem() {}
    public CardItem(String mCardQuestion, String mCardAnswer) {
        this.mCardQuestion = mCardQuestion;
        this.mCardAnswer = mCardAnswer;
    }

    public String getmCardId() {
        return mCardId;
    }

    public String getmCardQuestion() {
        return mCardQuestion;
    }

    public String getmCardAnswer() {
        return mCardAnswer;
    }
}
