package com.ashish.emotionoptimizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent i = getIntent();
        String prediction = i.getStringExtra("prediction");
        TextView tv = findViewById(R.id.res);
        String fi = "Predictio: " + prediction;
        tv.setText(fi);

    }
}
