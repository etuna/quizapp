package com.tunahantuna.quizapp;

import java.util.ArrayList;

/**
 * Created by etuna on 7/17/17.
 */

public class Question {

    //Variables
    private int mTextResId;
    private int answerResId;
    private int []optionsResIds;
    public String question;
    public String answer;
    public ArrayList<String> options;
    //-----------------------------

    //Constructor
    public Question(int textResId,int answerResId,int optionsResIds[]) {
        mTextResId = textResId;
        this.answerResId = answerResId;
        this.optionsResIds = optionsResIds;
    }
    //-----------------------------

    public Question(String q,String ans, ArrayList<String> options) {
        question = q;
        answer = ans;
        this.options=options;
    }
    //-----------------------------


    //Getters-Setters
    public int getTextResId() {
        return mTextResId;
    }
    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }
    public int getAnswerResId()
    {
        return answerResId;
    }
    public int []getOptionResIds()
    {
        return optionsResIds;
    }
    //-----------------------------
}
