package com.example.galgespil_aflevering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;

public class ScoreListActivity extends AppCompatActivity implements View.OnClickListener {
    Button exitButton;
    ArrayList<Integer> scoreArrayList;
    ArrayList<ResultObject> resultList;

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_list);

        exitButton = findViewById(R.id.returnButton);
        recyclerView = findViewById(R.id.recyclerView);
        scoreArrayList = new ArrayList<>();
        resultList = new ArrayList<>();

        exitButton.setOnClickListener(this);
        fetchListFromStorage();
        sortList();
        displayList();
    }

    public void fetchListFromStorage(){
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
                    //converting json to java object
                    Gson gson = new Gson();
                    String objectInJSON = jsonArray.getString(i);
                    ResultObject resultObject = gson.fromJson(objectInJSON, ResultObject.class);
                    resultList.add(resultObject);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void sortList(){
        Collections.sort(resultList);
    }

    public void displayList(){
        ResultListAdapter adapter = new ResultListAdapter(resultList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {
        if (v == exitButton){
            finish();
        }
    }
}
