package com.tunahantuna.quizapp;

        import android.os.Bundle;
        import android.os.Handler;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;
        import android.content.Intent;
        import java.util.Map;
        import java.util.HashMap;

public class QuizActivity extends AppCompatActivity {

    private Button firstOptionButton;
    private Button secondOptionButton;
    private Button thirdOptionButton;
    private Button fourthOptionButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private int seconds;
    private boolean running;
    private boolean wasRunning;
    private int questionTime = 10;
    private Handler questionTimerHandler = new Handler();
    private  Runnable qtRunnable;
    private User user;
    private boolean justNextButton= true;
    public PointsFragment pf;
    public QuizFragment qf;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        wasRunning = true;

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
        final String nickname;
        Intent intent = getIntent();
        nickname = intent.getStringExtra("nickname");
        user = new User(nickname, 0);
        TextView welcomeNickname = (TextView) findViewById(R.id.welcomeNickname) ;
        welcomeNickname.setText("Welcome, "+nickname+".");
        //------------------------

        //QuestionTimer View------
        final TextView questionTimerView = (TextView) findViewById(R.id.questionTimer);
        //------------------------

        //Question---------------
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        //-----------------------

        //Buttons----------------
        firstOptionButton = (Button) findViewById(R.id.firstOptionButton);
        firstOptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question q = mQuestionBank[mCurrentIndex];
                checkAnswer(0);
                justNextButton=false;
                toNextQuestion();
            }
        });

        secondOptionButton = (Button) findViewById(R.id.secondOptionButton);
        secondOptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question q = mQuestionBank[mCurrentIndex];
                checkAnswer(1);
                justNextButton=false;
                toNextQuestion();
            }
        });


        thirdOptionButton = (Button) findViewById(R.id.thirdOptionButton);
        thirdOptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question q = mQuestionBank[mCurrentIndex];
                checkAnswer(2);
                justNextButton=false;
                toNextQuestion();
            }
        });

        fourthOptionButton = (Button) findViewById(R.id.fourthOptionButton);
        fourthOptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question q = mQuestionBank[mCurrentIndex];
                checkAnswer(3);
                justNextButton=false;
                toNextQuestion();
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
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
                    if (mCurrentIndex < 9) {
                        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                        questionTime = 10;
                        questionTimerHandler.removeCallbacks(qtRunnable);
                        questionTimerHandler.post(qtRunnable);
                        updateQuestion();
                        updateButtons();
                    } else {
                        questionTimerHandler.removeCallbacks(qtRunnable);

                        Intent resultIntent = new Intent(QuizActivity.this, result.class);
                        resultIntent.putExtra("nickname", user.getUsername());
                        resultIntent.putExtra("score", user.getPoints());
                        resultIntent.putExtra("seconds", seconds);
                        startActivity(resultIntent);
                    }

            }
        });


        //------------------------


        //Run the timer,questionTimer,total time-----
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            questionTime = savedInstanceState.getInt("questionTime");
            mCurrentIndex = savedInstanceState.getInt("mCurrentIndex");
            user.setUsername(savedInstanceState.getString("nickname"));
            user.setPoints(savedInstanceState.getInt("score"));
        }
        if (wasRunning) {
            running = true;
        }
        runTimer();
        questionTimer((TextView)findViewById(R.id.questionTimer));
        updateQuestion();
        updateButtons();
        //-----------------------------


    }





    //RunTimer--------------------
    private void runTimer() {
        //lecture4RunTimer
        final TextView timeView = (TextView) findViewById(R.id.totalTimer);
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
                user.setPoints(user.getPoints()+100);
                messageResId = R.string.correct_toast;
            }
        else
            {
                user.setPoints(user.getPoints()-20);
                messageResId = R.string.incorrect_toast;
            }

        Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show();
    }
    //----------------------------

    //onSaveInstanceState---------
    protected void onSaveInstanceState(Bundle b)
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
    protected void onStop() {
        super.onStop();
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (wasRunning) {
            running = true;
        }
    }

    protected void onDestroy(){
        super.onDestroy();
        questionTimerHandler.removeCallbacks(qtRunnable);
    }
}

