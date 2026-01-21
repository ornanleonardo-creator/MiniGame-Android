package com.example.monapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView scoreText = findViewById(R.id.scoreText);
        Button clickButton = findViewById(R.id.clickButton);

        clickButton.setOnClickListener(v -> {
            score++;
            scoreText.setText("Score : " + score);
        });
    }
}
