package com.alykhaled.mathflashcards;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.AnimatorSet;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

public class NewCardActivity extends AppCompatActivity {
    private Button mSubBtn, mSaveBtn, mAnswerAddBtn, mQuestionAddBtn, mAddImageBtn;
    private EditText mQuestionEdit, mAnswerEdit, mNameEdit;
    private ImageView mListImage;
    private ViewPager mCardsView;
    private CardAbapter mCardAdapter;
    private AnimatorSet frontAnim;
    private AnimatorSet backAnim;
    private DatabaseReference mDatabaseReference;
    private ArrayList<CardItem> cards;
    private ListItem list;
    private String quesAddons = "";
    private String answerAddons = "";
    private Uri listImageLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_card);
        cards = new ArrayList<>();
        list = new ListItem();

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        mSubBtn = findViewById(R.id.submitBtn);
        mSaveBtn = findViewById(R.id.saveBtn);
        mQuestionEdit = findViewById(R.id.questionEdit);
        mAnswerEdit = findViewById(R.id.answerEdit);
        mNameEdit = findViewById(R.id.listName);
        mCardsView = findViewById(R.id.cardsView);
        mAnswerAddBtn = findViewById(R.id.addonsBtn);
        mQuestionAddBtn = findViewById(R.id.quesAddon);
        mAddImageBtn = findViewById(R.id.addImageBtn);
        mListImage = findViewById(R.id.listImage);

        com.alykhaled.mathflashcards.FirebaseUtil.openFbReference("lists");
        mDatabaseReference = com.alykhaled.mathflashcards.FirebaseUtil.mDatabaseReference;

        mSubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCard();
            }
        });
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.setCards(cards);
                list.setList_name(mNameEdit.getText().toString());
                String id = mDatabaseReference.push().getKey();
                list.setList_id(id);
                mDatabaseReference.child(id).setValue(list);
            }
        });
        mQuestionAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quesAddons = "math";
            }
        });
        mAnswerAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerAddons = "math";
            }
        });
        mAddImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(intent.createChooser(intent,"Insert background"),42);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 42 && resultCode == RESULT_OK)
        {
            Uri imageUri = data.getData();
            final StorageReference ref = FirebaseUtil.mStorageRef.child(imageUri.getLastPathSegment());
            ref.putFile(imageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return ref.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        String url = String.valueOf(downloadUri);
                        list.setList_pic(url);
                        Picasso.get().load(url).fit().into(mListImage);
                        listImageLink = Uri.parse(url);
                    } else {
                        Toast.makeText(NewCardActivity.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

            /*ref.getDownloadUrl().addOnSuccessListener(this, new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri taskSnapshot) {
                    String url = String.valueOf(taskSnapshot);
                    list.setList_pic(url);
                    Picasso.get().load(url).fit().into(mListImage);
                    listImageLink = Uri.parse(url);
                }
            });*/

        }
    }

    private void addCard() {
        /*String latestId = "0";
        String title;
        String answer;
        SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
        String[] cardColumns = {CardsDatabaseContract.CardsInfo.COLUMN_CARD_ID, CardsDatabaseContract.CardsInfo.COLUMN_CARD_TITLE, CardsDatabaseContract.CardsInfo.COLUMN_CARD_ANSWER};
        final Cursor cardsCursor = db.query(CardsDatabaseContract.CardsInfo.TABLE_NAME, cardColumns, null, null, null, null, null);
        mCardsList = new ArrayList<>();
        ContentValues values = new ContentValues();

        while(cardsCursor.moveToNext())
        {
            latestId = cardsCursor.getString(0);
        }
        if (!mQuestionEdit.getText().toString().equals("")&& !mAnswerEdit.getText().toString().equals(""))
        {
            values.put(CardsDatabaseContract.CardsInfo.COLUMN_CARD_ID, Integer.parseInt(latestId)+1);
            values.put(CardsDatabaseContract.CardsInfo.COLUMN_CARD_TITLE, mQuestionEdit.getText().toString());
            values.put(CardsDatabaseContract.CardsInfo.COLUMN_CARD_ANSWER, mAnswerEdit.getText().toString());
            long newRowId = db.insert(CardsDatabaseContract.CardsInfo.TABLE_NAME, null, values);
        }
        cardsCursor.close();*/

        CardItem card = new CardItem(mDatabaseReference.getKey(),mQuestionEdit.getText().toString(),mAnswerEdit.getText().toString(),"",quesAddons,answerAddons);
        cards.add(card);
        Collections.reverse(cards);
        mCardAdapter = new CardAbapter(this,cards);
        mCardsView.setAdapter(mCardAdapter);

    }
}
