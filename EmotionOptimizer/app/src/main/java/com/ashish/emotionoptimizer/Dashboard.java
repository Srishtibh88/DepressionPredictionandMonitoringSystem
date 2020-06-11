package com.ashish.emotionoptimizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void predictionFunc(View view) {
        Intent i = new Intent(Dashboard.this,Prediction.class);
        startActivity(i);
    }
}
