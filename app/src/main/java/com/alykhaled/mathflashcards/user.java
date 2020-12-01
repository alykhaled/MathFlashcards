package com.alykhaled.mathflashcards;

import java.io.Serializable;
import java.util.ArrayList;

public class user implements Serializable {
    private String name;
    private String profile_image;
    private String user_id;
    private ArrayList<ListItem> lists;
    private ArrayList<String> interests;

    public user(String name, String profile_image, String user_id, ArrayList<ListItem> lists, ArrayList<String> intersets) {
        this.name = name;
        this.profile_image = profile_image;
        this.user_id = user_id;
        this.lists = lists;
        this.interests = intersets;
    }

    public user() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public ArrayList<ListItem> getLists() {
        return lists;
    }

    public void setLists(ArrayList<ListItem> lists) {
        this.lists = lists;
    }

    public ArrayList<String> getInterests() {
        return interests;
    }

    public void setInterests(ArrayList<String> interests) {
        this.interests = interests;
    }

    @Override
    public String toString() {
        return "user{" +
                "name='" + name + '\'' +
                ", profile_image='" + profile_image + '\'' +
                ", user_id='" + user_id + '\'' +
                ", lists=" + lists +
                '}';
    }
}
