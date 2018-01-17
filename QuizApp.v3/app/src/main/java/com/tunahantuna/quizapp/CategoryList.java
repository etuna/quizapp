package com.tunahantuna.quizapp;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CategoryList extends AppCompatActivity implements CategoryListFragment.categoryListener,
        Serializable, PointsFragment.pointListener,
        QuizFragment.quizListener {
    public int seconds;
    public int questionSeconds;
    public PointsFragment pf;
    public QuizFragment qf;
    public Map<Integer, Integer> colorQuestions = new HashMap<Integer, Integer>();
    public User user;
    public int questionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        Intent intent = getIntent();
        String nickname = intent.getStringExtra("nickname");
        user = new User(nickname, 0);
        colorQuestions.put(0, 100);
        colorQuestions.put(1, 100);
        colorQuestions.put(2, 100);
        colorQuestions.put(3, 100);
        colorQuestions.put(4, 100);
        colorQuestions.put(5, 100);
        colorQuestions.put(6, 100);
        colorQuestions.put(7, 100);
        colorQuestions.put(8, 100);
        colorQuestions.put(9, 100);
        colorQuestions.put(10, 100);
        colorQuestions.put(11, 100);
        colorQuestions.put(12, 100);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            questionSeconds = savedInstanceState.getInt("questionSeconds");
            colorQuestions = (HashMap)savedInstanceState.getSerializable("colors");
            user=(User) savedInstanceState.getSerializable("user");
            questionIndex=savedInstanceState.getInt("questionIndex");
            int inQuiz = savedInstanceState.getInt("inQuiz");
            if (inQuiz == 1) {

                qf = new QuizFragment();
                pf = new PointsFragment();
                qf.setQuestion(questionIndex);
                qf.setSeconds(seconds);
                qf.setUser(user);
                qf.setQuestionSeconds(questionSeconds);
                FragmentTransaction mainFt = getFragmentManager().beginTransaction();
                pf.setColorOfQuestion((HashMap) colorQuestions);
                mainFt.replace(R.id.fragment_container, qf);
                mainFt.addToBackStack(null);
                mainFt.commit();
            } else {

                qf = new QuizFragment();
                pf = new PointsFragment();
                qf.setUser(user);
                FragmentTransaction mainFt = getFragmentManager().beginTransaction();
                pf.setColorOfQuestion((HashMap) colorQuestions);
                mainFt.replace(R.id.fragment_container, pf);
                mainFt.addToBackStack(null);
                mainFt.commit();
            }
        }


    }


    @Override
    public void itemClicked(long id) {

        View v = findViewById(R.id.fragment_container);
        FirebaseCon fc = new FirebaseCon();
        ArrayList<String> questions = fc.getQuestions("Android");
        ArrayList<String> answers = fc.getAnswers("Android");
        ArrayList<String> options = fc.getOptions("Android");

        if (v == null) {
            Intent intent = new Intent(CategoryList.this, FQuizActivity.class);
            intent.putExtra("user", (Serializable) user);
            startActivity(intent);
        } else {
            seconds = 0;
            qf = new QuizFragment();
            pf = new PointsFragment();
            qf.setUser(user);
            FragmentTransaction mainFt = getFragmentManager().beginTransaction();
            pf.setColorOfQuestion((HashMap) colorQuestions);
            mainFt.replace(R.id.fragment_container, pf);
            mainFt.addToBackStack(null);
            mainFt.commit();
        }
    }

    @Override
    public void onClickItem(long id) {
        questionIndex = (int) id;
        if (id == 0) {
            QuizFragment qf = new QuizFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            FirebaseCon fc = new FirebaseCon();
            ArrayList<String> questions = fc.getQuestions("Android");
            ArrayList<String> answers = fc.getAnswers("Android");
            ArrayList<String> options = fc.getOptions("Android");
            //ArrayList<String> answers = new ArrayList<String>();
           // ArrayList<String> options = new ArrayList<String>();
            //qf.setQuestions(questions,answers,options);
            qf.setUser(user);
            qf.setQuestion(0);
            qf.setSeconds(seconds);
            ft.replace(R.id.fragment_container, qf);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        if (id == 1) {
            QuizFragment qf = new QuizFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            qf.setUser(user);
            qf.setQuestion(1);
            qf.setSeconds(seconds);
            ft.replace(R.id.fragment_container, qf);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        if (id == 2) {
            QuizFragment qf = new QuizFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            qf.setUser(user);
            qf.setQuestion(2);
            qf.setSeconds(seconds);
            ft.replace(R.id.fragment_container, qf);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        if (id == 3) {
            QuizFragment qf = new QuizFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            qf.setUser(user);
            qf.setQuestion(3);
            qf.setSeconds(seconds);
            ft.replace(R.id.fragment_container, qf);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        if (id == 4) {
            QuizFragment qf = new QuizFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            qf.setUser(user);
            qf.setQuestion(4);
            qf.setSeconds(seconds);
            ft.replace(R.id.fragment_container, qf);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        if (id == 5) {
            QuizFragment qf = new QuizFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            qf.setUser(user);
            qf.setQuestion(5);
            qf.setSeconds(seconds);
            ft.replace(R.id.fragment_container, qf);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        if (id == 6) {
            QuizFragment qf = new QuizFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            qf.setUser(user);
            qf.setQuestion(6);
            qf.setSeconds(seconds);
            ft.replace(R.id.fragment_container, qf);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        if (id == 7) {
            QuizFragment qf = new QuizFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            qf.setUser(user);
            qf.setQuestion(7);
            qf.setSeconds(seconds);
            ft.replace(R.id.fragment_container, qf);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        if (id == 8) {
            QuizFragment qf = new QuizFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            qf.setUser(user);
            qf.setQuestion(8);
            qf.setSeconds(seconds);
            ft.replace(R.id.fragment_container, qf);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        if (id == 9) {
            QuizFragment qf = new QuizFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            qf.setUser(user);
            qf.setQuestion(9);
            qf.setSeconds(seconds);
            ft.replace(R.id.fragment_container, qf);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        if (id == 10) {
            QuizFragment qf = new QuizFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            qf.setUser(user);
            qf.setQuestion(10);
            qf.setSeconds(seconds);
            ft.replace(R.id.fragment_container, qf);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        if (id == 11) {
            QuizFragment qf = new QuizFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            qf.setUser(user);
            qf.setQuestion(11);
            qf.setSeconds(seconds);
            ft.replace(R.id.fragment_container, qf);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        if (id == -1) {
            Intent intent = new Intent(CategoryList.this, result.class);
            intent.putExtra("user", (Serializable) user);
            intent.putExtra("seconds", seconds);
            startActivity(intent);

        }
    }

    @Override
    public void onClickQL(long id, boolean isAnswerTrue) {
        int ind = (int) id;
        if (isAnswerTrue) {
            colorQuestions.put(ind, 1);
        } else {
            colorQuestions.put(ind, -1);
        }
        QuizFragment qfr = (QuizFragment) getFragmentManager().findFragmentById(R.id.fragment_container);
        seconds = qfr.seconds;
        PointsFragment pff = new PointsFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        pff.setColorOfQuestion((HashMap) colorQuestions);
        ft.replace(R.id.fragment_container, pff);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onClickQL(int i) {
        int ind = i;

        QuizFragment qfr = (QuizFragment) getFragmentManager().findFragmentById(R.id.fragment_container);
        seconds = qfr.seconds;
        PointsFragment pff = new PointsFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        colorQuestions.put(ind, 0);
        pff.setColorOfQuestion((HashMap) colorQuestions);
        ft.replace(R.id.fragment_container, pff);
        ft.addToBackStack(null);
        ft.commit();
    }
    @Override
    public void onSaveInstanceState(Bundle b){
        int inQuiz;
        if (findViewById(R.id.next_button) == null) {
            inQuiz = 0;
        } else {
            QuizFragment qfr = (QuizFragment) getFragmentManager().findFragmentById(R.id.fragment_container);
            questionSeconds= qfr.questionTime;
            seconds = qfr.seconds;

            b.putInt("questionSeconds",questionSeconds);
            inQuiz = 1;
        }
        b.putSerializable("colors",(Serializable)colorQuestions);
        b.putInt("seconds", seconds);
        b.putInt("inQuiz", inQuiz);
        b.putSerializable("user",(Serializable) user);
        b.putInt("questionIndex",questionIndex);
    }
}
