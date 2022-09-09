package com.example.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuestionsDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "quizdb";
    private static final String DATABASE_TABLE = "quiztable";

    private static final String _id = "id";
    private static final String COLUMN_QUESTIONS = "question";
    private static final String COLUMN_OPA = "optA";
    private static final String COLUMN_OPB = "optB";
    private static final String COLUMN_OPC = "optC";
    private static final String COLUMN_OPD = "optD";
    private static final String COLUMN_ANS = "answer";
    private static final String COLUMN_EXP = "exp";

    private SQLiteDatabase sqLiteDatabase;
    private int rowCount = 0;


    public QuestionsDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
      this.sqLiteDatabase = sqLiteDatabase;
        String  SQL_CREATE_QUIZ_TABLE=  "CREATE TABLE " +DATABASE_TABLE + "(" + _id + " INTEGER PRIMARY KEY," + COLUMN_QUESTIONS + " TEXT," + COLUMN_ANS + " TEXT," + COLUMN_OPA + " TEXT," + COLUMN_OPB + " TEXT," + COLUMN_OPC + " TEXT," + COLUMN_OPD + " TEXT," +COLUMN_EXP + " TEXT" + ")";
        Log.d("TaskDBHelper", "Query to form table" + SQL_CREATE_QUIZ_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_QUIZ_TABLE);
        addQuestins();
    }

    private void addQuestins() {
        Question q1 = new Question("Who is the founder of Apple Inc. ?", "Jose Thomas", "Bill Gates", "Steve Jobs", "Watson","Steve Jobs","a");
        this.addQuestionToDB(q1);
        Question q2 = new Question("Who is the first cricketer to score an international double century in 50-over match ?", "Ricky Ponting", "Sachin Tendulkar", "Brian Lara", "Rohit Sharma","Sachin Tendulkar","a");
        this.addQuestionToDB(q2);
        Question q3 = new Question("Which is the biggest largest city in the world ?", "Vienna",  "Delhi", "Reno","Mumbai","Reno","a");
        this.addQuestionToDB(q3);
        Question q4 = new Question("Which country is known as the country of copper ?", "Somalia", "Cameroon", "Zambia","india", "Zambia","a");
        this.addQuestionToDB(q4);
        Question q5 = new Question("Which is the world's oldest known city ?", "Rome", "Damascus","Kolkata", "Jerusalem", "Damascus","a");
        this.addQuestionToDB(q5);
        Question q6 = new Question("Who is the first Prime minister of India ?", "Jawaharlal Nehru","Narendra Modi", "Dr.Rajendra Prasad", "Lal Bahadur Shasthri", "Jawaharlal Nehru","a");
        this.addQuestionToDB(q6);
        Question q7 = new Question("Australia was discovered by ?", "James Cook", "Columbus","Warner", "Magallan", "James Cook","a");
        this.addQuestionToDB(q7);
        Question q8 = new Question("The national flower of Britain is ?", "Lily", "Rose","Sun Flower", "Lotus", "Rose","a");
        this.addQuestionToDB(q8);
        Question q9 = new Question("Which place is known as the roof of the world ?", "Alphs", "Tibet","Japan", "Nepal", "Tibet","a");
        this.addQuestionToDB(q9);
        Question q10 = new Question("Who invented washing machine ?", "James King", "Alfred Vincor","Roser Belly", "Christopher Marcossi", "James King","a");
        this.addQuestionToDB(q10);
        Question q11 = new Question("Who won the Football world Cup in 2014 ?","Manchester City", "Italy", "Argentina", "Germany", "Germany","a");
        this.addQuestionToDB(q11);
        Question q12 = new Question("Who won the Cricket World cup in 2011 ?", "Australia","Shree Lanka", "India", "England", "India","a");
        this.addQuestionToDB(q12);
        Question q13 = new Question("The number regarded as the lucky number in Italy is ?", "13","53", "7", "9", "13","a");
        this.addQuestionToDB(q13);
    }
    public void addQuestionToDB(Question q){
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTIONS, q.getQuestion());
        values.put(COLUMN_ANS,q.getAnswer());
        values.put(COLUMN_OPA,q.getOptA());
        values.put(COLUMN_OPB,q.getOptB());
        values.put(COLUMN_OPC,q.getOptC());
        values.put(COLUMN_OPD,q.getOptD());
        values.put(COLUMN_EXP,q.getExpalanation());
        //insert row
        sqLiteDatabase.insert(DATABASE_TABLE, null, values);
    }
    //get all question in listview
    public List<Question> getAllQuestions(){
        List <Question> questionList = new ArrayList<Question>();

        sqLiteDatabase = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+DATABASE_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery,null);
        rowCount = cursor.getCount();

        if(cursor.moveToFirst()){
            do{
                Question q = new Question();
                q.setId(cursor.getInt(0));
                q.setQuestion(cursor.getString(1));
                q.setAnswer(cursor.getString(2));
                q.setOptA(cursor.getString(3));
                q.setOptB(cursor.getString(4));
                q.setOptC(cursor.getString(5));
                q.setOptD(cursor.getString(6));
                q.setExpalanation(cursor.getString(7));

                //add question in list
                questionList.add(q);

                //loop all rows
            }while (cursor.moveToNext());
        }
        return questionList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
        onCreate(sqLiteDatabase);
    }
    public int getRowCount(){
        return rowCount;
    }
}
