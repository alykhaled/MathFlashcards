package com.alykhaled.mathflashcards;

import java.io.Serializable;

public class CardItem implements Serializable {
    private String card_id;
    private String card_question;
    private String card_answer;
    private String card_image;
    private String quesAddons;
    private String answerAddons;

    public CardItem() {}

    public CardItem(String card_id, String card_question, String card_answer,String card_image,String quesAddons,String answerAddons) {
        this.card_id = card_id;
        this.card_question = card_question;
        this.card_answer = card_answer;
        this.card_image = card_image;
        this.quesAddons = quesAddons;
        this.answerAddons = answerAddons;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getCard_question() {
        return card_question;
    }

    public void setCard_question(String card_question) {
        this.card_question = card_question;
    }

    public String getCard_answer() {
        return card_answer;
    }

    public void setCard_answer(String card_answer) {
        this.card_answer = card_answer;
    }

    public String getCard_image() {
        return card_image;
    }

    public void setCard_image(String card_image) {
        this.card_image = card_image;
    }

    public String getQuesAddons() {
        return quesAddons;
    }

    public void setQuesAddons(String quesAddons) {
        this.quesAddons = quesAddons;
    }

    public String getAnswerAddons() {
        return answerAddons;
    }

    public void setAnswerAddons(String answerAddons) {
        this.answerAddons = answerAddons;
    }

    @Override
    public String toString() {
        return "CardItem{" +
                "card_id='" + card_id + '\'' +
                ", card_question='" + card_question + '\'' +
                ", card_answer='" + card_answer + '\'' +
                ", card_image='" + card_image + '\'' +
                ", quesAddons='" + quesAddons + '\'' +
                ", answerAddons='" + answerAddons + '\'' +
                '}';
    }
}
