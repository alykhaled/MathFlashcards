package com.alykhaled.mathflashcards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import static com.alykhaled.mathflashcards.FirebaseUtil.mAuth;

public class RegisterActivity extends AppCompatActivity {

    public EditText nameEdit;
    public EditText emailEdit;
    public EditText passwordEdit;
    public Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        nameEdit = findViewById(R.id.fullNameEdit);
        emailEdit = findViewById(R.id.emailEdit);
        passwordEdit = findViewById(R.id.passwordEdit);
        registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (!TextUtils.isEmpty(emailEdit.getText()) && !TextUtils.isEmpty(passwordEdit.getText()))
                {
                    mAuth.createUserWithEmailAndPassword(emailEdit.getText().toString(), passwordEdit.getText().toString()).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                user user = new user();
                                user.setName(nameEdit.getText().toString());
                                user.setProfile_image("");
                                user.setUser_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
                                /*FirebaseDatabase.getInstance().getReference().child("users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user);*/
                                Toast.makeText(RegisterActivity.this, "Authentication completed.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this,InterestsActivity.class);
                                intent.putExtra("user",user);
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "Please make sure to add both Email and Password", Toast.LENGTH_SHORT).show();
                }
                /*Intent intent = new Intent(RegisterActivity.this,InterestsActivity.class);
                intent.putExtra("userName",nameEdit.getText().toString());
                intent.putExtra("userName",nameEdit.getText().toString());
                startActivity(intent);*/
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

}