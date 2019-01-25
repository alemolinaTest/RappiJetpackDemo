package com.example.jetpacktest.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jetpacktest.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    
    Button addScoreButton, resetScoreButton;
    TextView scoreTextView;

//    Integer score = 0;
    ScoreViewModel scoreViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        scoreTextView = findViewById(R.id.scoreTextView);
        addScoreButton = findViewById(R.id.addScoreButton);
        resetScoreButton = findViewById(R.id.resetScoreButton);

        scoreViewModel = ViewModelProviders.of(this).get(ScoreViewModel.class);

        scoreViewModel.getScore().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                Log.i(TAG, "onChanged: ");
                scoreTextView.setText(String.valueOf(integer));
            }
        });

        //addScore button listener
        addScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addScore();
            }
        });
        //resetScore button listener
        resetScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetScore();
            }
        });
    }

    public void addScore() {
        scoreViewModel.addScore();
    }
    
    public void resetScore() {
        scoreViewModel.resetScore();
    }
    
    
    
    
    
}
