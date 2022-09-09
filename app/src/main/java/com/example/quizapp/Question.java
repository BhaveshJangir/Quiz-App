package com.example.quizapp;

public class Question {
    private  int id;
    private String question,optA,optB,optC,optD,answer,expalanation;

    public String getExpalanation() {
        return expalanation;
    }

    public void setExpalanation(String expalanation) {
        this.expalanation = expalanation;
    }

    public Question(){
        id=0;
        question = "";
        optA = "";
        optB = "";
        optC = "";
        optD = "";

        expalanation = "";
    }
    public Question( String question, String optA, String optB, String optC, String optD, String answer, String expalanation) {
        this.id = id;
        this.question = question;
        this.optA = optA;
        this.optB = optB;
        this.optC = optC;
        this.optD = optD;
        this.answer = answer;
        this.expalanation = expalanation;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptA() {
        return optA;
    }

    public void setOptA(String optA) {
        this.optA = optA;
    }

    public String getOptB() {
        return optB;
    }

    public void setOptB(String optB) {
        this.optB = optB;
    }

    public String getOptC() {
        return optC;
    }

    public void setOptC(String optC) {
        this.optC = optC;
    }

    public String getOptD() {
        return optD;
    }

    public void setOptD(String optD) {
        this.optD = optD;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
