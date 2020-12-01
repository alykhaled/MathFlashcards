package com.alykhaled.mathflashcards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class InterestsActivity extends AppCompatActivity implements InterestsAdapter.OnItemClickListener  {
    private RecyclerView mInterestsView;
    private ArrayList<InterestItem> mInterestsList;
    private ArrayList<String> mSelectedItems;
    private InterestsAdapter mInterestsAdapter;
    private Button mSaveBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);
        mInterestsView = findViewById(R.id.interestsView);
        GridLayoutManager layoutmanger = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        mInterestsView.setLayoutManager(layoutmanger);
        mInterestsList = new ArrayList<>();
        mSelectedItems = new ArrayList<>();
        mInterestsList.add(new InterestItem("Math","https://placekitten.com/300/300",false));
        mInterestsList.add(new InterestItem("Physics","https://placekitten.com/300/300" ,false));
        mInterestsList.add(new InterestItem("Chemistry","https://placekitten.com/300/300",false));
        mInterestsList.add(new InterestItem("Languages","https://i.picsum.photos/id/524/300/300.jpg?hmac=MpxUkv6gcBsbfdS64thmQBCSgpyfNIXq2Y8_Gx3SupA",false));
        mInterestsList.add(new InterestItem("Biology","https://placekitten.com/300/300",false));
        mInterestsAdapter = new InterestsAdapter(InterestsActivity.this, mInterestsList);
        mInterestsAdapter.setOnItemClickListener(InterestsActivity.this);
        mInterestsView.setAdapter(mInterestsAdapter);
        Intent intent = getIntent();
        final user user = (user) intent.getSerializableExtra("user");
        mSaveBtn = findViewById(R.id.saveBtn);

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setInterests(mSelectedItems);
                FirebaseDatabase.getInstance().getReference().child("users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .setValue(user);
                Intent intent = new Intent(InterestsActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClick(int position, ArrayList<InterestItem> t) {
        mSelectedItems.add(t.get(position).getInterest_name());
    }
}