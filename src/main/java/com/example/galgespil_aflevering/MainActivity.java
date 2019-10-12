package com.example.galgespil_aflevering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView easyMode;
    TextView standardMode;
    TextView hardMode;
    ImageView gitHubImage;
    ImageView questionImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        easyMode = findViewById(R.id.easyMode);
        standardMode = findViewById(R.id.standardMode);
        hardMode = findViewById(R.id.hardMode);
        gitHubImage = findViewById(R.id.gitHubImage);
        questionImage = findViewById(R.id.questionImage);

        easyMode.setOnClickListener(this);
        standardMode.setOnClickListener(this);
        hardMode.setOnClickListener(this);
        gitHubImage.setOnClickListener(this);
        questionImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == easyMode){
            Intent intent = new Intent(this, Gameactivity.class);
            intent.putExtra("mode", "1");
            startActivity(intent);
        }else if (v == standardMode){
            Intent intent = new Intent(this, Gameactivity.class);
            intent.putExtra("mode", "2");
            startActivity(intent);
        }else if (v == hardMode){
            Intent intent = new Intent(this, Gameactivity.class);
            intent.putExtra("mode", "3");
            startActivity(intent);
        }else if (v == gitHubImage){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("github.com"));
            startActivity(intent);
        }

    }
}
