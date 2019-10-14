package com.example.galgespil_aflevering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView gitHubImage;
    ImageView questionImage;
    ImageView animalImage;
    ImageView carImage;
    ImageView countryImage;
    ImageView movieImage;
    ImageView gameImage;
    ImageView foodImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gitHubImage = findViewById(R.id.gitHubImage);
        questionImage = findViewById(R.id.questionImage);
        animalImage = findViewById(R.id.animalImage);
        carImage = findViewById(R.id.carImage);
        countryImage = findViewById(R.id.countryImage);
        movieImage = findViewById(R.id.movieImage);
        gameImage = findViewById(R.id.gameImage);
        foodImage = findViewById(R.id.foodImage);

        gitHubImage.setOnClickListener(this);
        questionImage.setOnClickListener(this);
        animalImage.setOnClickListener(this);
        carImage.setOnClickListener(this);
        countryImage.setOnClickListener(this);
        movieImage.setOnClickListener(this);
        gameImage.setOnClickListener(this);
        foodImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == animalImage){
            Intent intent = new Intent(this, Gameactivity.class);
            intent.putExtra("mode", "animals");
            startActivity(intent);
        }else if (v == carImage){
            Intent intent = new Intent(this, Gameactivity.class);
            intent.putExtra("mode", "cars");
            startActivity(intent);
        }else if (v == countryImage){
            Intent intent = new Intent(this, Gameactivity.class);
            intent.putExtra("mode", "countries");
            startActivity(intent);
        }else if(v == movieImage){
            Intent intent = new Intent(this, Gameactivity.class);
            intent.putExtra("mode", "movies");
            startActivity(intent);
        }else if(v == gameImage){
            Intent intent = new Intent(this, Gameactivity.class);
            intent.putExtra("mode", "games");
            startActivity(intent);
        }else if(v == foodImage){
            Intent intent = new Intent(this, Gameactivity.class);
            intent.putExtra("mode", "foods");
            startActivity(intent);
        }else if (v == gitHubImage){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/asamahayder/Galgespil_Aflevering"));
            startActivity(intent);
        }else if (v == questionImage){
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }

    }
}
