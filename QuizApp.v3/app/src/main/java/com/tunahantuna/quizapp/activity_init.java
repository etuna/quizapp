package com.tunahantuna.quizapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class activity_init extends AppCompatActivity {

    private String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        //Nick enterance
        final EditText nick = (EditText) findViewById(R.id.NicknameTextView);

        //Button Handling - StartButton
        Button startButton = (Button) findViewById(R.id.StartButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        Intent intentToStart = new Intent(activity_init.this, CategoryList.class);
        nickname = nick.getText().toString();
        intentToStart.putExtra("nickname",nickname);
        startActivity(intentToStart);
            }
        });

    }



}
