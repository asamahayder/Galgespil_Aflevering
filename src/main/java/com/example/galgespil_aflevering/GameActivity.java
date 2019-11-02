package com.example.galgespil_aflevering;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    TextView gameMode;
    TextView synligtOrd;
    ImageView image;
    LinearLayout linearLayout1;
    LinearLayout linearLayout2;
    LinearLayout linearLayout3;
    ArrayList<Button> buttonArrayList;
    Galgelogik spil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        String mode = intent.getStringExtra("mode");
        spil = new Galgelogik();
        synligtOrd = findViewById(R.id.synligtOrd);
        image = findViewById(R.id.grafik);
        linearLayout1 = findViewById(R.id.linearLayout1);
        linearLayout2 = findViewById(R.id.linearLayout2);
        linearLayout3 = findViewById(R.id.linearLayout3);
        buttonArrayList = new ArrayList<>();
        synligtOrd.setText(R.string.ventVenligst);

        createCustomKeyboard();
        handleGetWord(mode);

    }

    @Override
    public void onClick(View v) {
        //Da knapper bliver lavet programmatisk, så kan deres clicklistener ikke blive implementeret her.
    }

    public void createCustomKeyboard(){
        for (int i = 0; i < 26; i++) {
            final Button button = new Button(this);
            button.setBackgroundResource(R.drawable.rounded_buttons);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleButtonClick(button);
                }
            });
            button.setTextSize(TypedValue.COMPLEX_UNIT_SP,27);
            button.setTextColor(Color.parseColor("yellow"));
            button.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            params.setMargins(6,6,6,6);
            button.setPadding(10,10,10,10);
            button.setLayoutParams(params);
            button.setEnabled(false);
            buttonArrayList.add(button);

            switch (i){
                case 0: button.setText("Q"); break;
                case 1: button.setText("W"); break;
                case 2: button.setText("E"); break;
                case 3: button.setText("R"); break;
                case 4: button.setText("T"); break;
                case 5: button.setText("Y"); break;
                case 6: button.setText("U"); break;
                case 7: button.setText("I"); break;
                case 8: button.setText("O"); break;
                case 9: button.setText("P"); break;
                case 10: button.setText("A"); break;
                case 11: button.setText("S"); break;
                case 12: button.setText("D"); break;
                case 13: button.setText("F"); break;
                case 14: button.setText("G"); break;
                case 15: button.setText("H"); break;
                case 16: button.setText("J"); break;
                case 17: button.setText("K"); break;
                case 18: button.setText("L"); break;
                case 19: button.setText("Z"); break;
                case 20: button.setText("X"); break;
                case 21: button.setText("C"); break;
                case 22: button.setText("V"); break;
                case 23: button.setText("B"); break;
                case 24: button.setText("N"); break;
                case 25: button.setText("M"); break;

            }
            if (i < 10){
                linearLayout1.addView(button);
            }else if (i < 19){
                linearLayout2.addView(button);
            }else{
                linearLayout3.addView(button);
            }
        }
    }

    public void handleGetWord(String mode){
        gameMode = findViewById(R.id.chosenMode);

        if (mode.equals("animals")){
            gameMode.setText(R.string.animals);
            new HentOrdFraArkTask(this, spil).execute("animals");
        }else if (mode.equals("cars")){
            gameMode.setText(R.string.cars);
            new HentOrdFraArkTask(this, spil).execute("cars");
        }else if(mode.equals("countries")){
            gameMode.setText(R.string.countries);
            new HentOrdFraArkTask(this, spil).execute("countries");
        }
        else if(mode.equals("movies")){
            gameMode.setText(R.string.movies);
            new HentOrdFraArkTask(this, spil).execute("movies");
        }
        else if(mode.equals("games")){
            gameMode.setText(R.string.games);
            new HentOrdFraArkTask(this, spil).execute("games");
        }else if(mode.equals("foods")){
            gameMode.setText(R.string.food);
            new HentOrdFraArkTask(this, spil).execute("foods");
        }
    }

    public void handleButtonClick(TextView button){
        String guess = button.getText().toString().toLowerCase();
        handleGuessV2(guess);
        if (spil.erSidsteBogstavKorrekt()){
            button.setBackgroundResource(0);
            button.setTextColor(Color.parseColor("green"));
        }else{
            button.setBackgroundResource(0);
            button.setTextColor(Color.parseColor("red"));
        }
        handleImageChange();
        handleGameFinish();
    }

    public void handleGuessV2(String guess){
        spil.gætBogstav(guess);
        synligtOrd.setText(spil.getSynligtOrd());
    }

    public void handleImageChange(){
        if (!spil.erSidsteBogstavKorrekt()){
            switch (spil.getAntalForkerteBogstaver()){
                case 1: image.setImageResource(R.drawable.forkert1);
                    break;
                case 2: image.setImageResource(R.drawable.forkert2);
                    break;
                case 3: image.setImageResource(R.drawable.forkert3);
                    break;
                case 4: image.setImageResource(R.drawable.forkert4);
                    break;
                case 5: image.setImageResource(R.drawable.forkert5);
                    break;
                case 6: image.setImageResource(R.drawable.forkert6);
                    break;
            }
        }
    }

    public void handleGameFinish(){
        if (spil.erSpilletSlut()){
            int score = calculateScore();
            //setButtonsEnabled(false);

            Intent intent = new Intent(this, ScoreActivity.class);
            if (spil.erSpilletVundet()){
                intent.putExtra("status","won");
                intent.putExtra("score",score);
            }else{
                intent.putExtra("status", "lost");
                intent.putExtra("score",0);
            }

            intent.putExtra("word",spil.getOrdet());
            finish();
            startActivity(intent);
        }
    }

    //Disse to metoder bliver brugt i forbindelse med HentOrdFraArkTask klassen
    public void setSynligtOrd(){
        synligtOrd.setText(spil.getSynligtOrd());
    }

    public void showErrorMessage(){
        synligtOrd.setText(R.string.couldNotFetchWord);
    }

    public int calculateScore(){
        int antalBogstaver = spil.getOrdet().length();
        int antalLivTilbage = 6-spil.getAntalForkerteBogstaver();
        return 1000*(antalBogstaver+antalLivTilbage);
    }

    public void setButtonsEnabled(boolean state){
        for (int i = 0; i < buttonArrayList.size(); i++) {
            buttonArrayList.get(i).setEnabled(state);
        }
    }
}
