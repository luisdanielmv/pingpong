package com.example.pingpong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pingpong.activities.PongActivity;

public class DifficultiesActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.difficulties);
    }

    public void setEasyDifficulty(View view) {
        this.setDifficultySettings(12, 0.6f);
    }

    public void  setNormalDifficulty(View view) {
        this.setDifficultySettings(16, 0.8f);
    }

    public void setHardDifficulty(View view){
        this.setDifficultySettings(20, 0.9f);
    }

    private  void setDifficultySettings(int ballSpeed, float computerProbability) {
        Intent intent = new Intent(this, PongActivity.class);
        intent.putExtra("ballSpeed", ballSpeed);
        intent.putExtra("computerProbability", computerProbability);
        startActivity(intent);

    }
}