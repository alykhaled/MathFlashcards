package com.alykhaled.mathflashcards;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.AnimatorSet;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements ListAdapter.OnItemClickListener {
    private CardsOpenHelper mDbOpenHelper;
    private ArrayList<ListItem> mListsList;
    private RecyclerView mCardsView;
    private ListAdapter mListAdapter;
    private AnimatorSet frontAnim;
    private AnimatorSet backAnim;
    private TextView mAddBtn;
    private TextView mWelcomeText;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildListener;

    @Override
    protected void onResume() {
        super.onResume();
        View decorView = getWindow().getDecorView();

        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View decorView = getWindow().getDecorView();

        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        LinearLayoutManager LinerManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mCardsView = findViewById(R.id.trackView);
        mWelcomeText = findViewById(R.id.welcomeText);

        mCardsView.setHasFixedSize(true);
        mCardsView.setLayoutManager(LinerManager);
        mAddBtn = findViewById(R.id.addListBtn);
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewCardActivity.class);
                startActivity(intent);
            }
        });
        mListsList = new ArrayList<>();
        mDbOpenHelper = new CardsOpenHelper(this);
        com.alykhaled.mathflashcards.FirebaseUtil.openFbReference("lists");
        mFirebaseDatabase = com.alykhaled.mathflashcards.FirebaseUtil.mFirebaseDatabase;
        mDatabaseReference = com.alykhaled.mathflashcards.FirebaseUtil.mDatabaseReference;
        mChildListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ListItem card = snapshot.getValue(ListItem.class);
                mListsList.add(card);
                Collections.reverse(mListsList);
                mListAdapter = new ListAdapter(MainActivity.this,mListsList);
                mCardsView.setAdapter(mListAdapter);
                mListAdapter.setOnItemClickListener(MainActivity.this);
                mListAdapter.notifyItemInserted(mListsList.size()-1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mDatabaseReference.addChildEventListener(mChildListener);
        getUserDetails();
    }

    public void getUserDetails() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        Query query = ref.child("users")
                .orderByKey()
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot singleSnapShot : snapshot.getChildren())
                {
                    user user = singleSnapShot.getValue(com.alykhaled.mathflashcards.user.class);
                    mWelcomeText.setText("Welcome, " + user.getName());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        mDbOpenHelper.close();
        super.onDestroy();
    }

    public void onItemClick(int position,ArrayList<ListItem> t ) {
        ListItem list = t.get(position);
        /*SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
        db.execSQL("DELETE FROM " + CardsDatabaseContract.CardsInfo.TABLE_NAME + " WHERE card_id = " + clickedItem.getmCardId());*/
        Intent intent = new Intent(MainActivity.this,ViewCardActivity.class);
        intent.putExtra("list", list);
        startActivity(intent);

    }
}
