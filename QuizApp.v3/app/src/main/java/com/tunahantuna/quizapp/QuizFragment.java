package com.tunahantuna.quizapp;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Fragment;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class QuizFragment extends Fragment {


    public QuizFragment() {
        // Required empty public constructor
    }

    interface quizListener{
        void onClickQL(long id,boolean isAnswerTrue);
        void onClickQL(int i);
    }

    public quizListener qListener;
    private Button firstOptionButton;
    private Button secondOptionButton;
    private Button thirdOptionButton;
    private Button fourthOptionButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    public int seconds;
    private boolean running;
    private boolean wasRunning = true;
    public int questionTime = 10;
    private Handler questionTimerHandler = new Handler();
    private  Runnable qtRunnable;
    private User user;
    private boolean justNextButton= true;
    private boolean isAnswerTrue;

    //Questions - Answers Map/Answer Key--------------
    private Map question_answers =  new HashMap<Integer, Integer>();
    //------------------------------------------------


    //OPTIONS OF QUESTIONS---------------------------
    private int options_q1[] = new int[]{
            R.string.option1_question_australia,
            R.string.option2_question_australia,
            R.string.option3_question_australia,
            R.string.option4_question_australia
    };

    private int options_q2[] = new int[]{
            R.string.option1_question_oceans,
            R.string.option2_question_oceans,
            R.string.option3_question_oceans,
            R.string.option4_question_oceans
    };

    private int options_q3[] = new int[]{
            R.string.option1_question_math,
            R.string.option2_question_math,
            R.string.option3_question_math,
            R.string.option4_question_math
    };

    private int options_q4[] = new int[]{
            R.string.option1_question_cinema,
            R.string.option2_question_cinema,
            R.string.option3_question_cinema,
            R.string.option4_question_cinema
    };

    private int options_q5[] = new int[]{
            R.string.option1_question_chem,
            R.string.option2_question_chem,
            R.string.option3_question_chem,
            R.string.option4_question_chem
    };

    private int options_q6[] = new int[]{
            R.string.option1_question_tv,
            R.string.option2_question_tv,
            R.string.option3_question_tv,
            R.string.option4_question_tv
    };

    private int options_q7[] = new int[]{
            R.string.option1_question_phone,
            R.string.option2_question_phone,
            R.string.option3_question_phone,
            R.string.option4_question_phone
    };

    private int options_q8[] = new int[]{
            R.string.option1_question_music,
            R.string.option2_question_music,
            R.string.option3_question_music,
            R.string.option4_question_music
    };

    private int options_q9[] = new int[]{
            R.string.option1_question_time,
            R.string.option2_question_time,
            R.string.option3_question_time,
            R.string.option4_question_time
    };

    private int options_q10[] = new int[]{
            R.string.option1_question_quiz,
            R.string.option2_question_quiz,
            R.string.option3_question_quiz,
            R.string.option4_question_quiz
    };
    //---------------------------------------------


    //OPTIONS OF QUESTIONS---------------------------
    private String options_q01[] = new String[4];

    private String options_q02[] = new String[4];

    private String options_q03[] = new String[4];

    private String options_q04[] = new String[4];

    private String options_q05[] = new String[4];

    private String options_q06[] = new String[4];

    private String options_q07[] = new String[4];

    private String options_q08[] = new String[4];

    private String options_q09[] = new String[4];

    private String options_q010[] = new String[4];

    private String options_q011[] = new String[4];

    private String options_q012[] = new String[4];
    //---------------------------------------------











    //Questions------------------------------------
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, R.string.answer_question_australia,options_q1),
            new Question(R.string.question_oceans, R.string.answer_question_oceans,options_q2),
            new Question(R.string.question_math, R.string.answer_question_math,options_q3),
            new Question(R.string.question_cinema,R.string.answer_question_cinema,options_q4),
            new Question(R.string.question_chem,R.string.answer_question_chem,options_q5),
            new Question(R.string.question_tv,R.string.answer_question_tv , options_q6),
            new Question(R.string.question_phone,R.string.answer_question_phone,options_q7),
            new Question(R.string.question_music,R.string.answer_question_music,options_q8),
            new Question(R.string.question_time,R.string.answer_question_time,options_q9),
            new Question(R.string.question_quiz,R.string.answer_question_quiz,options_q10),
    };
    //---------------------------------------------

    private int mCurrentIndex = 0;
    public View view;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Run the timer,questionTimer,total time-----
        view=getView();
        wasRunning=true;
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            questionTime = savedInstanceState.getInt("questionTime");
            mCurrentIndex = savedInstanceState.getInt("mCurrentIndex");
            user.setUsername(savedInstanceState.getString("nickname"));
            user.setPoints(savedInstanceState.getInt("score"));
        } else {
        }
        if (wasRunning) {
            running = true;
        }
        return inflater.inflate(R.layout.fragment_quiz, container, false);

    }







    //RunTimer--------------------
    private void runTimer() {
        //lecture4RunTimer
        view = getView();
        final TextView timeView = (TextView) view.findViewById(R.id.totalTimer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
    //----------------------------

    //updateQuestion--------------
    private void updateQuestion()
    {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }
    //----------------------------

    //updateButtons---------------
    private void updateButtons()
    {
        Question question = mQuestionBank[mCurrentIndex];
        int[] opts  = question.getOptionResIds();
        firstOptionButton.setText(opts[0]);
        secondOptionButton.setText(opts[1]);
        thirdOptionButton.setText(opts[2]);
        fourthOptionButton.setText(opts[3]);
    }
    //----------------------------

    //checkAnswer-----------------
    private void checkAnswer(int optionSelected) {
        Question cq = mQuestionBank[mCurrentIndex];
        int messageResId = 0;
        int ans = (int)question_answers.get(mCurrentIndex);
        if(optionSelected == ans)
        {
            isAnswerTrue=true;
            user.setPoints(user.getPoints()+100);
            messageResId = R.string.correct_toast;
        }
        else
        {
            isAnswerTrue=false;
            user.setPoints(user.getPoints()-20);
            messageResId = R.string.incorrect_toast;
        }
    }
    //----------------------------

    //onSaveInstanceState---------
    public void onSaveInstanceState(Bundle b)
    {
        b.putInt("questionTime",questionTime);
        b.putInt("seconds",seconds);
        b.putInt("mCurrentIndex",mCurrentIndex);
        b.putInt("score",user.getPoints());
        b.putString("nickname",user.getUsername());
    }
    //----------------------------

    //toNextQuestion--------------
    private void toNextQuestion()
    {
        mNextButton.performClick();
    }
    //----------------------------

    //QuestionTimer---------------
    private void questionTimer(TextView v)
    {

        final TextView tvQT = v;

        qtRunnable = new Runnable() {
            @Override
            public void run() {
                if(questionTime < 0)
                {
                    toNextQuestion();
                }
                else
                {
                    String updatedTimerText = String.format("%d", questionTime);
                    tvQT.setText(updatedTimerText);
                    questionTime--;
                    questionTimerHandler.postDelayed(this, 1000);
                }
            }
        };


        questionTimerHandler.post(qtRunnable);


    }

    //----------------------------

    @Override
    public void onStop() {
        super.onStop();
        wasRunning = running;
        running = false;
    }

    @Override
    public void onStart() {
        super.onStart();
        view = getView();



        //Answer Key--------------
        question_answers.put(0,1);
        question_answers.put(1,3);
        question_answers.put(2,3);
        question_answers.put(3,3);
        question_answers.put(4,1);
        question_answers.put(5,3);
        question_answers.put(6,0);
        question_answers.put(7,1);
        question_answers.put(8,2);
        question_answers.put(9,3);
        //------------------------

        //Welcome,nickname--------
        TextView welcomeNickname = (TextView) view.findViewById(R.id.welcomeNickname) ;
        welcomeNickname.setText("Welcome, "+user.getUsername()+".");
        //------------------------


        //QuestionTimer View------
        final TextView questionTimerView = (TextView) view.findViewById(R.id.questionTimer);
        //------------------------

        //Question---------------
        mQuestionTextView = (TextView) view.findViewById(R.id.question_text_view);
        //-----------------------

        //Buttons----------------
        firstOptionButton = (Button) view.findViewById(R.id.firstOptionButton);
        firstOptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question q = mQuestionBank[mCurrentIndex];
                checkAnswer(0);
                questionTimerHandler.removeCallbacks(qtRunnable);
                qListener.onClickQL(mCurrentIndex,isAnswerTrue);
            }
        });

        secondOptionButton = (Button) view.findViewById(R.id.secondOptionButton);
        secondOptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question q = mQuestionBank[mCurrentIndex];
                checkAnswer(1);
                questionTimerHandler.removeCallbacks(qtRunnable);
                qListener.onClickQL(mCurrentIndex,isAnswerTrue);

            }
        });


        thirdOptionButton = (Button) view.findViewById(R.id.thirdOptionButton);
        thirdOptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question q = mQuestionBank[mCurrentIndex];
                checkAnswer(2);
                questionTimerHandler.removeCallbacks(qtRunnable);
                qListener.onClickQL(mCurrentIndex,isAnswerTrue);
            }
        });

        fourthOptionButton = (Button) view.findViewById(R.id.fourthOptionButton);
        fourthOptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question q = mQuestionBank[mCurrentIndex];
                checkAnswer(3);
                questionTimerHandler.removeCallbacks(qtRunnable);
                qListener.onClickQL(mCurrentIndex,isAnswerTrue);
            }
        });

        mNextButton = (Button) view.findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                if(justNextButton)
                {
                    user.setPoints(user.getPoints()-20);
                }
                if(!justNextButton) {
                    justNextButton = true;
                }
                questionTimerHandler.removeCallbacks(qtRunnable);
                qListener.onClickQL(mCurrentIndex);
            }

        });


        //------------------------



        runTimer();
        questionTimer((TextView)view.findViewById(R.id.questionTimer));
        updateQuestion();
        updateButtons();
        //-----------------------------













    }

    public void onDestroy(){
        super.onDestroy();
        questionTimerHandler.removeCallbacks(qtRunnable);
    }

    public void createUser(String nickname) {
        user = new User(nickname,0);
    }

    public void onAttach(Context context){
        super.onAttach(context);
        qListener = (quizListener) context;
    }

    public void setQuestion(int index){
        mCurrentIndex = index;
    }
    public void setSeconds(int seconds){
        this.seconds= seconds;
    }
    public void setQuestionSeconds(int questionSeconds){
        questionTime=questionSeconds;
    }
    public void setUser(User user){
        this.user=user;
    }
    public void setQuestions(ArrayList<String> questions,ArrayList<String> answers, ArrayList<String> options ){
        Question [] Qs = new Question[questions.size()];
        ArrayList<String> optionsTR = new ArrayList<String>();
        for(int i = 0 ; i<questions.size(); i++){
            optionsTR.clear();
            optionsTR.add(options.get(3*i));
            optionsTR.add(options.get(3*i+1));
            optionsTR.add(options.get(3*i+2));
            Question q = new Question(questions.get(i),answers.get(i),optionsTR);
            Qs[i]=q;
        }
        mQuestionBank = Qs;

        int c = 0;
        for(int k = 0; k<3; k++){
            options_q01[k] = options.get(3*c+k);
        }
        options_q01[3]=answers.get(c);
        c++;


        for(int k = 0; k<3; k++){
            options_q02[k] = options.get(3*c+k);
        }
        options_q02[3]=answers.get(c);
        c++;

        for(int k = 0; k<3; k++){
            options_q03[k] = options.get(3*c+k);
        }
        options_q03[3]=answers.get(c);
        c++;

        for(int k = 0; k<3; k++){
            options_q04[k] = options.get(3*c+k);
        }
        options_q04[3]=answers.get(c);
        c++;

        for(int k = 0; k<3; k++){
            options_q05[k] = options.get(3*c+k);
        }
        options_q05[3]=answers.get(c);
        c++;

        for(int k = 0; k<3; k++){
            options_q06[k] = options.get(3*c+k);
        }
        options_q06[3]=answers.get(c);
        c++;

        for(int k = 0; k<3; k++){
            options_q07[k] = options.get(3*c+k);
        }
        options_q07[3]=answers.get(c);
        c++;

        for(int k = 0; k<3; k++){
            options_q08[k] = options.get(3*c+k);
        }
        options_q08[3]=answers.get(c);
        c++;

        for(int k = 0; k<3; k++){
            options_q09[k] = options.get(3*c+k);
        }
        options_q09[3]=answers.get(c);
        c++;

        for(int k = 0; k<3; k++){
            options_q010[k] = options.get(3*c+k);
        }
        options_q010[3]=answers.get(c);
        c++;

        for(int k = 0; k<3; k++){
            options_q011[k] = options.get(3*c+k);
        }
        options_q011[3]=answers.get(c);
        c++;

        for(int k = 0; k<3; k++){
            options_q012[k] = options.get(3*c+k);
        }
        options_q012[3]=answers.get(c);
        c++;





}
}


