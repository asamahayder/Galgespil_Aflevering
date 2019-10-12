package com.example.galgespil_aflevering;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity implements View.OnClickListener {

    TextView status;
    TextView score;
    TextView word;
    Button wiki;
    Button playAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        status = findViewById(R.id.resultText);
        score = findViewById(R.id.scoreText);
        word = findViewById(R.id.wordText2);
        wiki = findViewById(R.id.wikipediaButton);
        playAgain = findViewById(R.id.playAgainButton);

        String statusString = getIntent().getStringExtra("status");
        int scoreInt = getIntent().getIntExtra("score",0);
        String scoreString = Integer.toString(scoreInt);
        String wordString = getIntent().getStringExtra("word");
        if (statusString.equals("won")){
            status.setText(R.string.statusWon);

        }else if (statusString.equals("lost")){
            status.setText(R.string.statusLost);

        }else {
            status.setText(R.string.ErrorAtStatus);
        }
        score.setText(scoreString);
        word.setText(wordString);

        playAgain.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == playAgain){
            finish();
        }
    }
}
