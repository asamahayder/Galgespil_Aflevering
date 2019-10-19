package com.example.galgespil_aflevering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;

public class ScoreListActivity extends AppCompatActivity implements View.OnClickListener {
    Button exitbutton;
    LinearLayout scoreListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_list);

        exitbutton = findViewById(R.id.returnButton);
        scoreListView = findViewById(R.id.scoreListView);

        exitbutton.setOnClickListener(this);
        fillList();
    }

    public void fillList(){
        ArrayList<Integer> scoreArrayList = new ArrayList<>();
        String preferenceFileKey = getString(R.string.scoreReferenceFileKey);
        String scoreListKey = getString(R.string.scoreListKey);
        SharedPreferences preferences = getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE);
        String list = preferences.getString(scoreListKey,null);
        //hvis listen er null, altså at den ikke findes, så gør vi intet.
        if (list != null){
            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(list);
                for (int i = 0; i < jsonArray.length(); i++) {
                    int score = jsonArray.getInt(i);
                    scoreArrayList.add(score);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //sorting the list in descending order
            Collections.sort(scoreArrayList);
            Collections.reverse(scoreArrayList);

            for (int i = 0; i < scoreArrayList.size(); i++) {
                //we don't consider 0 as a valid score
                if (scoreArrayList.get(i) == 0){
                    continue;
                }
                TextView textView = new TextView(this);
                textView.setText(scoreArrayList.get(i).toString());
                textView.setTextColor(Color.parseColor("white"));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
                textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                scoreListView.addView(textView);
            }


        }

    }

    @Override
    public void onClick(View v) {
        if (v == exitbutton){
            finish();
        }
    }
}
