package com.tunahantuna.etuna.quizapp3;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by etuna on 7/17/17.
 */

public class Question implements Parcelable{

    static Question[] questions;
    static Question[] questionsAndroid;
    static Question[] questionsMath;
    static Question[] questionsFood;

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


    protected Question(Parcel in) {
        mTextResId = in.readInt();
        answerResId = in.readInt();
        optionsResIds = in.createIntArray();
        question = in.readString();
        answer = in.readString();
        options = in.createStringArrayList();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mTextResId);
        parcel.writeInt(answerResId);
        parcel.writeIntArray(optionsResIds);
        parcel.writeString(question);
        parcel.writeString(answer);
        parcel.writeStringList(options);
    }
    //-----------------------------
}
