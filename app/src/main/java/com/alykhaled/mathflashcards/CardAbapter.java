package com.alykhaled.mathflashcards;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class CardAbapter extends PagerAdapter {

    private final Context mContext;
    private final List<CardItem> mCards;
    private LayoutInflater layoutInflater;

    public CardAbapter(Context mContext, List<CardItem> mCards) {
        this.mContext = mContext;
        this.mCards = mCards;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        CardItem card = mCards.get(position);

        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.card_layout, null);
        TextView mTextQuestion =  view.findViewById(R.id.questionTxt);
        TextView mTextAnswer = view.findViewById(R.id.answerTxt);
        mTextQuestion.setText(card.getmCardQuestion());
        mTextAnswer.setText(card.getmCardAnswer());
        int mCurrentPosition = position;
        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public int getCount() {
        return mCards.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}
