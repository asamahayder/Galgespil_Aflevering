package com.example.galgespil_aflevering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity implements View.OnClickListener {

    TextView status;
    TextView score;
    TextView word;
    Button wiki;
    Button playAgain;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        status = findViewById(R.id.resultText);
        score = findViewById(R.id.scoreText);
        word = findViewById(R.id.wordText2);
        wiki = findViewById(R.id.wikipediaButton);
        playAgain = findViewById(R.id.playAgainButton);
        imageView = findViewById(R.id.stickmanImage);

        String statusString = getIntent().getStringExtra("status");
        int scoreInt = getIntent().getIntExtra("score",0);
        String scoreString = Integer.toString(scoreInt);
        String wordString = getIntent().getStringExtra("word");
        if (statusString.equals("won")){
            status.setText(R.string.statusWon);
            imageView.setImageResource(R.drawable.win);

        }else if (statusString.equals("lost")){
            status.setText(R.string.statusLost);
            imageView.setImageResource(R.drawable.lose);

        }else {
            status.setText(R.string.ErrorAtStatus); //for error checking
        }
        score.setText("score: " + scoreString);
        word.setText(wordString);

        playAgain.setOnClickListener(this);
        wiki.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == playAgain){
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }else if (v == wiki){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/" + word.getText().toString()));
            startActivity(intent);
        }
    }
}
