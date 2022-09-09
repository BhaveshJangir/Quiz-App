package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ScoreCard extends AppCompatActivity {

    TextView mResult,comp;
    Button retake;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_card);

        retake = (Button) findViewById(R.id.retake_button);
        mResult = (TextView) findViewById(R.id.total_score_text);
        mResult.setText(""+QuizPage.getScore());
        comp = (TextView) findViewById(R.id.result_text);
        if(QuizPage.getScore() == 5){
            comp.setText("Excellent work");
        }else if(QuizPage.getScore() == 4){
            comp.setText("Good Work");
        }else if(QuizPage.getScore() == 3){
            comp.setText("Well try");
        }else if(QuizPage.getScore() <= 2){
            comp.setText("failed");
        }

        retake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),QuizHomePage.class);
                startActivity(i);
                finish();
            }
        });

    }
}