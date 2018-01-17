package com.tunahantuna.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class result extends AppCompatActivity {

    private int score = 10;
    private String nickname="test" ;
    private Button startNewButton;
    private Button exitButton;
    private TextView tvNickname;
    private TextView tvScore;
    private TextView totalTime;
    private int seconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        User user =(User)intent.getSerializableExtra("user");

        nickname = user.getUsername();
        tvNickname = (TextView) findViewById(R.id.tvNickname);
        score = user.getPoints();
        tvScore  = (TextView) findViewById(R.id.tvScore);
        totalTime = (TextView) findViewById(R.id.TotalTimePassed);
        seconds = intent.getIntExtra("seconds",seconds);
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        String time = String.format("%d:%02d:%02d", hours, minutes, secs);
        totalTime.setText(time);
        tvNickname.setText(nickname);
        tvScore.setText(Integer.toString(score));
        startNewButton = (Button) findViewById(R.id.startNewButton);
        startNewButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent startNewIntent = new Intent(result.this , activity_init.class);
                startActivity(startNewIntent);
            }
        });
    }
}
