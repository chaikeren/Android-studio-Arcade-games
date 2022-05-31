package com.example.arcade_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    TextView greetUser;
    RelativeLayout sevenBoomBtn, wordsGameBtn, colorsGameBtn,
                 fingerMeBtn, numberMemoryBtn, crackTheEggBtn;
    Button logoutBtn, leaderboardBtn;
    DatabaseReference myRef;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String userid = user.getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        initView();

        initButtons();

        greetUser();
    }

    private void initButtons() {

        sevenBoomBtn.setOnClickListener(this);
        wordsGameBtn.setOnClickListener(this);
        colorsGameBtn.setOnClickListener(this);
        fingerMeBtn.setOnClickListener(this);
        numberMemoryBtn.setOnClickListener(this);
        crackTheEggBtn.setOnClickListener(this);
        leaderboardBtn.setOnClickListener(this);
        logoutBtn.setOnClickListener(this);
    }

    private void initView() {

        greetUser = findViewById(R.id.greetUser);
        sevenBoomBtn = findViewById(R.id.seven_boom_btn);
        wordsGameBtn = findViewById(R.id.word_game_btn);
        colorsGameBtn = findViewById(R.id.colors_game_btn);
        fingerMeBtn = findViewById(R.id.finger_me_btn);
        numberMemoryBtn = findViewById(R.id.number_memory_btn);
        crackTheEggBtn = findViewById(R.id.crack_the_egg_game_btn);
        leaderboardBtn = findViewById(R.id.leaderboard_btn);
        logoutBtn = findViewById(R.id.logout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.seven_boom_btn:
                startActivity(new Intent(HomePage.this, SevenBoom.class));
                break;
            case R.id.word_game_btn:
                startActivity(new Intent(HomePage.this, WordsGame.class));
                break;
            case R.id.colors_game_btn:
                startActivity(new Intent(HomePage.this, ColorsGame.class));
                break;
            case R.id.finger_me_btn:
                startActivity(new Intent(HomePage.this, FingerMe.class));
                break;
            case R.id.number_memory_btn:
                startActivity(new Intent(HomePage.this, NumberMemory.class));
                break;
            case R.id.crack_the_egg_game_btn:
                startActivity(new Intent(HomePage.this, CrackTheEgg.class));
                break;
            case R.id.leaderboard_btn:
                startActivity(new Intent(HomePage.this, LeaderBoard.class));
                break;
            case R.id.logout:
                logout();
                break;
        }
    }

    private void greetUser(){

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users").child(Objects.requireNonNull(mAuth.getUid()));

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("username").getValue().toString();
                greetUser.setText("Hello: " + name);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    private void logout() {

        startActivity(new Intent(HomePage.this, MainActivity.class));
    }


}