package com.alykhaled.mathflashcards;

import java.io.Serializable;
import java.util.ArrayList;

class ListItem implements Serializable {
    private String list_id;
    private String list_name;
    private String list_pic;
    private ArrayList<CardItem> cards;

    public ListItem() {}

    public ListItem(String list_id, String list_name, ArrayList<CardItem> cards,String list_pic) {
        this.list_id = list_id;
        this.list_name = list_name;
        this.cards = cards;
        this.list_pic = list_pic;
    }

    public String getList_id() {
        return list_id;
    }

    public void setList_id(String list_id) {
        this.list_id = list_id;
    }

    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

    public ArrayList<CardItem> getCards() {
        return cards;
    }

    public void setCards(ArrayList<CardItem> cards) {
        this.cards = cards;
    }

    public String getList_pic() {
        return list_pic;
    }

    public void setList_pic(String list_pic) {
        this.list_pic = list_pic;
    }

    @Override
    public String toString() {
        return "ListItem{" +
                "list_id='" + list_id + '\'' +
                ", cards=" + cards +
                '}';
    }
}
