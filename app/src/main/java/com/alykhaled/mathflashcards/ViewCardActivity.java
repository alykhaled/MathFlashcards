package com.alykhaled.mathflashcards;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;

public class ViewCardActivity extends AppCompatActivity {
    private ViewPager mCardsView;
    private CardAbapter mCardAdapter;
    private Button mRightBtn;
    private Button mWrongBtn;
    private TextView mScoreBtn;
    private ProgressBar mProgress;
    int score = 0;
    Animation animation ;
    Animation animationlast ;
    ArrayList<Integer> choosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_card);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        Intent intent = getIntent();
        ArrayList<CardItem> cards = new ArrayList<>();
        ListItem list = (ListItem) intent.getSerializableExtra("list");
        cards = list.getCards();
        Collections.reverse(cards);

        mCardsView = findViewById(R.id.viewCardsView);
        mRightBtn = findViewById(R.id.rightBtn);
        mWrongBtn = findViewById(R.id.wrongBtn);
        mScoreBtn = findViewById(R.id.scoreTxt);
        mProgress = findViewById(R.id.progressBar);

        choosen = new ArrayList<>();
        for(int i = 0; i < cards.size(); i++)
        {
            choosen.add(0);
        }
        mProgress.setMax(cards.size());
        mProgress.setProgress(0);
        mRightBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                RelativeLayout linearLayout = findViewById(R.id.viewLayout);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200,
                        200);
                params.addRule(RelativeLayout.CENTER_HORIZONTAL,RelativeLayout.TRUE);
                params.addRule(RelativeLayout.CENTER_VERTICAL,RelativeLayout.TRUE);
                ImageView image = new ImageView(ViewCardActivity.this);
                image.setImageResource(R.drawable.right);
                image.setLayoutParams(params);
                linearLayout.addView(image);

                setContentView(linearLayout);
                animation = AnimationUtils.loadAnimation(ViewCardActivity.this, R.anim.fade_in);
                image.setAnimation(animation);
                animationlast = AnimationUtils.loadAnimation(ViewCardActivity.this, R.anim.fade_out);
                image.setAnimation(animationlast);
                image.setVisibility(View.INVISIBLE);
                animation = AnimationUtils.loadAnimation(ViewCardActivity.this, R.anim.fade_out);
                image.setAnimation(animation);

                score++;
                mScoreBtn.setText(String.valueOf(score));
                mRightBtn.setEnabled(false);
                mWrongBtn.setEnabled(true);
                mProgress.setProgress(mProgress.getProgress()+1,true);
            }
        });

        mWrongBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                RelativeLayout linearLayout = findViewById(R.id.viewLayout);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200,
                        200);
                params.addRule(RelativeLayout.CENTER_HORIZONTAL,RelativeLayout.TRUE);
                params.addRule(RelativeLayout.CENTER_VERTICAL,RelativeLayout.TRUE);
                ImageView image = new ImageView(ViewCardActivity.this);
                image.setImageResource(R.drawable.wrong);
                image.setLayoutParams(params);
                linearLayout.addView(image);

                setContentView(linearLayout);
                animation = AnimationUtils.loadAnimation(ViewCardActivity.this, R.anim.fade_in);
                image.setAnimation(animation);
                image.setVisibility(View.INVISIBLE);
                choosen.set(mCardsView.getCurrentItem(),2);

                mRightBtn.setEnabled(true);
                mWrongBtn.setEnabled(false);
                score--;
                mScoreBtn.setText(String.valueOf(score));

                if (choosen.get(mCardsView.getCurrentItem()) != 1)
                {
                    mProgress.setProgress(mProgress.getProgress()+1,true);

                }
                mCardsView.arrowScroll(View.FOCUS_RIGHT);
            }
        });

        mCardsView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (choosen.get(position) == 1)
                {
                    mRightBtn.setEnabled(false);

                }
                else if(choosen.get(position) == 2)
                {
                    mWrongBtn.setEnabled(false);
                }
                else
                {
                    mRightBtn.setEnabled(true);
                    mWrongBtn.setEnabled(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mCardAdapter = new CardAbapter(this,cards);
        mCardsView.setAdapter(mCardAdapter);
    }
}