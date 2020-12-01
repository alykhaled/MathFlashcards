package com.alykhaled.mathflashcards;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;

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
        final ViewFlipper flipper = view.findViewById(R.id.cardViewFlipper);
        TextView mTextQuestion =  view.findViewById(R.id.questionTxt);
        TextView mTextAnswer = view.findViewById(R.id.answerTxt);
        mTextQuestion.setText(card.getCard_question());
        mTextAnswer.setText(card.getCard_answer());
        ImageView mathImage =  view.findViewById(R.id.mathImage);
        ImageView quesImage =  view.findViewById(R.id.quesImage);

        if (card.getQuesAddons().equals("math"))
        {
            String queslink = "https://chart.googleapis.com/chart?cht=tx&chl="+ card.getCard_question()+"&chs=200";
            queslink = queslink.replace("+","%2B");
            Picasso.get().load(queslink).fit().centerInside().into(quesImage);
        }
        else if (card.getQuesAddons().equals("image"))
        {

        }
        else
        {
            /*RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200,
                    200);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL,RelativeLayout.TRUE);
            params.addRule(RelativeLayout.CENTER_VERTICAL,RelativeLayout.TRUE);
            mTextQuestion.setLayoutParams(params);*/
        }

        if (card.getAnswerAddons().equals("math"))
        {
            String link = "https://chart.googleapis.com/chart?cht=tx&chl="+ card.getCard_answer()+"&chs=200";
            link = link.replace("+","%2B");
            Picasso.get().load(link).fit().centerInside().into(mathImage);
        }
        else if (card.getAnswerAddons().equals("image"))
        {

        }
        else
        {
            /*RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200,
                    200);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL,RelativeLayout.TRUE);
            params.addRule(RelativeLayout.CENTER_VERTICAL,RelativeLayout.TRUE);
            mTextAnswer.setLayoutParams(params);*/
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.setInAnimation(mContext,R.anim.front_animator);
                flipper.setOutAnimation(mContext,R.anim.back_animator);
                flipper.showNext();
            }
        });

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
