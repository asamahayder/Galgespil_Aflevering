package com.example.galgespil_aflevering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;


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
    String statusString;
    String scoreString;
    String wordString;


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

        //StatusString fås fra gameActivity og den fortæller os om man har vundet eller tabt
        statusString = getIntent().getStringExtra("status");
        scoreInt = getIntent().getIntExtra("score",0);
        numberOfTries = getIntent().getIntExtra("numberOfTries",0);
        scoreString = Integer.toString(scoreInt);
        wordString = getIntent().getStringExtra("word");

        score.setText("score: " + scoreString);
        handleScoreAnimation();
        word.setText(wordString);
        numberOfTriesText.setText(numberOfTriesText.getText() + Integer.toString(numberOfTries));

        playAgain.setOnClickListener(this);
        wiki.setOnClickListener(this);

        handleGameOutcome();
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
        //we don't consider 0 as a valid score
        if (scoreInt == 0){
            return;
        }

        //creating result object
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String time = formatter.format(date);
        ResultObject resultObject = new ResultObject(wordString, time, scoreString);

        String preferenceFileKey = getString(R.string.scoreReferenceFileKey);
        String scoreListKey = getString(R.string.scoreListKey);
        SharedPreferences preferences = getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE);
        String listInJSON = preferences.getString(scoreListKey,null);
        if (listInJSON == null){
            //creating a list and saving it in preferences
            JSONArray jsonList = new JSONArray();

            //converting object to json
            Gson gson = new Gson();
            String objectInJSON = gson.toJson(resultObject);

            jsonList.put(objectInJSON);
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
        Gson gson = new Gson();
        String objectInJSON = gson.toJson(resultObject);
        jsonArray.put(objectInJSON);
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

    public void handleGameOutcome(){
        if (statusString.equals("won")){
            status.setText(R.string.statusWon);
            imageView.setImageResource(R.drawable.win);
            //afspiller vinder lyd
            MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.victory_soundeffect);
            mediaPlayer.start();

        }else if (statusString.equals("lost")){
            status.setText(R.string.statusLost);
            imageView.setImageResource(R.drawable.lose);
            numberOfTriesText.setVisibility(View.INVISIBLE);
            //afspiller taber lyd
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.defeat_sound_effect);
            mediaPlayer.start();

        }else {
            status.setText(R.string.ErrorAtStatus); //for error checking
        }
    }

    public void handleScoreAnimation(){
        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(score,
                PropertyValuesHolder.ofFloat("scaleX", 1.5f),
                PropertyValuesHolder.ofFloat("scaleY", 1.5f));
        scaleDown.setDuration(1000);
        scaleDown.setInterpolator(new DecelerateInterpolator());
        scaleDown.setRepeatCount(Animation.INFINITE);
        scaleDown.setRepeatMode(ValueAnimator.REVERSE);
        scaleDown.start();
    }
}
