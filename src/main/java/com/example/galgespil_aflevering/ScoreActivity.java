package com.example.galgespil_aflevering;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;


public class ScoreActivity extends AppCompatActivity implements View.OnClickListener {

    TextView status;
    TextView score;
    TextView word;
    TextView numberOfTriesText;
    Button wiki;
    Button playAgain;
    ImageView imageView;
    ConstraintLayout scoreConstraintLayout;

    int scoreInt;
    int numberOfTries;


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
        numberOfTriesText = findViewById(R.id.numberOfTriesText);
        scoreConstraintLayout = findViewById(R.id.scoreConstrainLayout);

        String statusString = getIntent().getStringExtra("status");
        scoreInt = getIntent().getIntExtra("score",0);
        numberOfTries = getIntent().getIntExtra("numberOfTries",0);
        String scoreString = Integer.toString(scoreInt);
        String wordString = getIntent().getStringExtra("word");
        if (statusString.equals("won")){
            status.setText(R.string.statusWon);
            imageView.setImageResource(R.drawable.win);
            MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.victory_soundeffect);
            mediaPlayer.start();

        }else if (statusString.equals("lost")){
            status.setText(R.string.statusLost);
            imageView.setImageResource(R.drawable.lose);
            numberOfTriesText.setVisibility(View.INVISIBLE);
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.defeat_sound_effect);
            mediaPlayer.start();

        }else {
            status.setText(R.string.ErrorAtStatus); //for error checking
        }
        score.setText("score: " + scoreString);
        word.setText(wordString);
        numberOfTriesText.setText(numberOfTriesText.getText() + Integer.toString(numberOfTries));

        playAgain.setOnClickListener(this);
        wiki.setOnClickListener(this);

        addScoreToList();
        readingScoreFromList();
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

    public void addScoreToList(){
        if (scoreInt == 0){
            return;
        }
        String preferenceFileKey = getString(R.string.scoreReferenceFileKey);
        String scoreListKey = getString(R.string.scoreListKey);
        SharedPreferences preferences = getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE);
        String listInJSON = preferences.getString(scoreListKey,null);
        if (listInJSON == null){
            //creating list and saving it in preferences
            JSONArray jsonList = new JSONArray();
            jsonList.put(scoreInt);
            String stringList = jsonList.toString();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(scoreListKey,stringList);
            editor.apply();
            return;
        }
        //else just get list, put data inside and save
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(listInJSON);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsonArray.put(scoreInt);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(scoreListKey,jsonArray.toString());
        editor.apply();
    }

    public void readingScoreFromList(){
        //only for testing purposes
        String preferenceFileKey = getString(R.string.scoreReferenceFileKey);
        String scoreListKey = getString(R.string.scoreListKey);
        SharedPreferences preferences = getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE);
        String listInJSON = preferences.getString(scoreListKey,null);
        System.out.println("current list: " + listInJSON);
    }
}
