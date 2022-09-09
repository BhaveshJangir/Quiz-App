package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class QuizPage extends AppCompatActivity {

    List<Question> questionList;
    int que_id;
    Question currentQuestion;
    TextView mQuestion, textViewCountDown;
    TextView mScore;
    RadioButton rdA, rdB, rdC, rdD;
    Button nextButton;
    RadioGroup rgp;
    static int totalScore;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);

        mQuestion = (TextView) findViewById(R.id.Question);
        rdA = (RadioButton) findViewById(R.id.rd_op_a);
        rdB = (RadioButton) findViewById(R.id.rd_op_b);
        rdC = (RadioButton) findViewById(R.id.rd_op_c);
        rdD = (RadioButton) findViewById(R.id.rd_op_d);
        nextButton = (Button) findViewById(R.id.next_button);
        textViewCountDown = findViewById(R.id.text_view_countdown);
        mScore = (TextView) findViewById(R.id.current_score);

        new CountDownTimer(180000, 1000) {
            @SuppressLint("ResourceType")
            public void onTick(long millisUntilFinished) {
                int minute = (int) (millisUntilFinished / 1000) / 60;
                int second = (int) (millisUntilFinished / 1000) % 60;

                if (minute == 0) {
                    textViewCountDown.setTextColor(R.color.red);
                    textViewCountDown.setText(minute + " : " + second);
                } else {
                    textViewCountDown.setText(minute + " : " + second);
                }
            }

            public void onFinish() {
                totalScore = score;
                Intent intent = new Intent(QuizPage.this, ScoreCard.class);
                startActivity(intent);
                finish();
            }

        }.start();


        //get all question from db
        QuestionsDbHelper dbHelper = new QuestionsDbHelper(this);
        questionList = dbHelper.getAllQuestions();
        //random question
        Collections.shuffle(questionList);
        currentQuestion = questionList.get(que_id);

        setQuestionView();
//        rgp = (RadioGroup) findViewById(R.id.rediogroup_opt);
//        RadioButton answer = (RadioButton) findViewById(rgp.getCheckedRadioButtonId());
//
//      rdA.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View view) {
//
//              if (rdA.isChecked()) {
//                  if (currentQuestion.getAnswer().equals(answer.getText())) {
//                      answer.setTextColor(Color.parseColor("#00ff00"));
//                  }else {// Changing radio button 1 color on checked.
//                      rdA.setTextColor(Color.parseColor("#00ff00"));
//
//                      //Changing other remaining radio button color to black.
//                      rdC.setTextColor(Color.RED);
//                      rdD.setTextColor(Color.RED);
//                      rdB.setTextColor(Color.RED);
//                  }
//              }
//          }
//      });
//
//        if (rdB.isChecked()) {
//            // Changing radio button 1 color on checked.
//            rdB.setTextColor(Color.parseColor("#00ff00"));
//
//            //Changing other remaining radio button color to black.
//            rdD.setTextColor(Color.RED);
//            rdC.setTextColor(Color.RED);
//            rdA.setTextColor(Color.RED);
//        }
//        if (rdC.isChecked()) {
//            // Changing radio button 1 color on checked.
//            rdB.setTextColor(Color.parseColor("#00ff00"));
//
//            //Changing other remaining radio button color to black.
//            rdD.setTextColor(Color.RED);
//            rdB.setTextColor(Color.RED);
//            rdA.setTextColor(Color.RED);
//        }
//        if (rdD.isChecked()) {
//            // Changing radio button 1 color on checked.
//            rdB.setTextColor(Color.parseColor("#00ff00"));
//
//            //Changing other remaining radio button color to black.
//            rdB.setTextColor(Color.RED);
//            rdC.setTextColor(Color.RED);
//            rdA.setTextColor(Color.RED);
//        }

        nextButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
               rgp = (RadioGroup) findViewById(R.id.rediogroup_opt);
                RadioButton answer = (RadioButton) findViewById(rgp.getCheckedRadioButtonId());
                if (currentQuestion.getAnswer().equals(answer.getText())) {
                    score++;
                    Log.d("Score", "Your score" + score);
                    mScore.setText("Right answere : " + score);
                }
                if (que_id < 5) {
                    currentQuestion = questionList.get(que_id);
                    //  answer.setChecked(false);
                    setQuestionView();
                } else {
                    totalScore = score;
                    Intent intent = new Intent(QuizPage.this, ScoreCard.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


    }

    private void setQuestionView() {
        mQuestion.setText(currentQuestion.getQuestion());
        rdA.setText(currentQuestion.getOptA());
        rdB.setText(currentQuestion.getOptB());
        rdC.setText(currentQuestion.getOptC());
        rdD.setText(currentQuestion.getOptD());
        que_id++;
    }

    public static int getScore() {
        return totalScore;
    }

//    public void optionD(View view) {
//        RadioButton answer = (RadioButton) findViewById(rgp.getCheckedRadioButtonId());
//        if (currentQuestion.getAnswer().equals(answer.getText())) {
//            // Changing radio button 1 color on checked.
//            rdD.setTextColor(Color.parseColor("#00ff00"));
//
//            //Changing other remaining radio button color to black.
//            rdB.setTextColor(Color.BLACK);
//            rdC.setTextColor(Color.BLACK);
//            rdA.setTextColor(Color.BLACK);
//        }
//    }
//
//    public void optionC(View view) {
//        RadioButton answer = (RadioButton) findViewById(rgp.getCheckedRadioButtonId());
//        if (currentQuestion.getAnswer().equals(answer.getText())) {
//            // Changing radio button 1 color on checked.
//            rdC.setTextColor(Color.parseColor("#00ff00"));
//
//            //Changing other remaining radio button color to black.
//            rdB.setTextColor(Color.RED);
//            rdD.setTextColor(Color.RED);
//            rdA.setTextColor(Color.RED);
//        }
//    }
//
//    public void optionB(View view) {
//        RadioButton answer = (RadioButton) findViewById(rgp.getCheckedRadioButtonId());
//        if (currentQuestion.getAnswer().equals(answer.getText())) {
//            // Changing radio button 1 color on checked.
//            rdB.setTextColor(Color.parseColor("#00ff00"));
//
//            //Changing other remaining radio button color to black.
//            rdD.setTextColor(Color.RED);
//            rdC.setTextColor(Color.RED);
//            rdA.setTextColor(Color.RED);
//        }
//    }
//
//    public void optionA(View view) {
//        RadioButton answer = (RadioButton) findViewById(rgp.getCheckedRadioButtonId());
//        if (currentQuestion.getAnswer().equals(answer.getText())) {
//            // Changing radio button 1 color on checked.
//            rdA.setTextColor(Color.parseColor("#00ff00"));
//
//            //Changing other remaining radio button color to black.
//            rdB.setTextColor(Color.RED);
//            rdC.setTextColor(Color.RED);
//            rdD.setTextColor(Color.RED);
//        }
//    }
}