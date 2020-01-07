package com.example.galgespil_aflevering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ChooseOwnWordActivity extends AppCompatActivity {

    TextView pageTitle;
    Galgelogik game;
    ArrayList<String> wordList;
    RecyclerView recyclerView;
    String chosenWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_own_word);

        game = new Galgelogik();
        wordList = new ArrayList<>();
        pageTitle = findViewById(R.id.chooseWordTitle);
        recyclerView = findViewById(R.id.chooseWordRecyclerView);

        new GetAllWordsFromWebTask(this, game).execute("placeholder");


    }

    public void showErrorMessage(){
        pageTitle.setTextSize(20);
        pageTitle.setTextColor(getColor(R.color.red));
        pageTitle.setText(getString(R.string.somethingWentWrong));
    }


    public void displayWordList(ArrayList<String> wordList) {
        this.wordList = wordList;
        ChooseWordAdapter adapter = new ChooseWordAdapter(wordList, new ChooseWordAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String word) {
                chosenWord = word;
                openIntent();
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void openIntent(){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("mode", "custom");
        intent.putExtra("chosenWord", chosenWord);
        startActivity(intent);
    }
}
