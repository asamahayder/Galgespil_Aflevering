package com.example.galgespil_aflevering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView easyMode;
    TextView standardMode;
    TextView hardMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        easyMode = findViewById(R.id.easyMode);
        standardMode = findViewById(R.id.standardMode);
        hardMode = findViewById(R.id.hardMode);

        easyMode.setOnClickListener(this);
        standardMode.setOnClickListener(this);
        hardMode.setOnClickListener(this);



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
        }

    }
}
