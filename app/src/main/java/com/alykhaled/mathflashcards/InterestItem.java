package com.alykhaled.mathflashcards;

import java.io.Serializable;

class InterestItem implements Serializable {
    private String interest_name;
    private String interest_pic;
    private boolean selected;

    public InterestItem() {}

    public InterestItem(String interest_name, String interest_pic, boolean selected) {
        this.interest_name = interest_name;
        this.interest_pic = interest_pic;
        this.selected = selected;
    }

    public String getInterest_name() {
        return interest_name;
    }

    public void setInterest_name(String interest_name) {
        this.interest_name = interest_name;
    }

    public void setList_name(String list_name) {
        this.interest_name = list_name;
    }

    public String getInterest_pic() {
        return interest_pic;
    }

    public void setInterest_pic(String interest_pic) {
        this.interest_pic = interest_pic;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
