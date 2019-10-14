package com.example.galgespil_aflevering;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == returnButton){
            finish();
        }
    }
}
