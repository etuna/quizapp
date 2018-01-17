package com.tunahantuna.quizapp;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    String username, email, passwordEntered, passwordConfirmed;

    CheckBox CBAgreement;

    boolean inOptions = true, CBisChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        if (savedInstanceState != null) {

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            signUpFragment sf = new signUpFragment();
            ft.replace(R.id.signUpContainer, sf);
            ft.addToBackStack(null);
            ft.commit();

            username = savedInstanceState.getString("username");
            email = savedInstanceState.getString("email");
            passwordEntered = savedInstanceState.getString("passwordEntered");
            passwordConfirmed = savedInstanceState.getString("passwordConfirmed");
            CBisChecked = savedInstanceState.getBoolean("CBChecked");

            sf.setUsername(username);
            sf.setEmail(email);
            sf.setPasswordEntered(passwordEntered);
            sf.setPasswordConfirmed(passwordConfirmed);
            sf.setCheckBoxAggrement(CBisChecked);


        } else {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            signUpFragment sof = new signUpFragment();
            ft.replace(R.id.signUpContainer, sof);
            ft.addToBackStack(null);
            ft.commit();
        }
    }




    public void onBackPressed() {
        finish();
    }

    public void onSaveInstanceState(Bundle b) {
        if (getFragmentManager().findFragmentById(R.id.signUpContainer) instanceof signUpFragment) {
            username = ((EditText) findViewById(R.id.username)).getText().toString();
            email = ((EditText) findViewById(R.id.email)).getText().toString();
            passwordEntered = ((EditText) findViewById(R.id.password)).getText().toString();
            passwordConfirmed = ((EditText) findViewById(R.id.repassword)).getText().toString();
            CBAgreement = (CheckBox) findViewById(R.id.checkBoxAggrement);
            b.putString("username", username);
            b.putString("email", email);
            b.putString("passwordEntered", passwordEntered);
            b.putString("passwordConfirmed", passwordConfirmed);
            b.putBoolean("CBChecked",CBAgreement.isChecked());
            b.putBoolean("inOptions", false);
        } else {
            b.putBoolean("inOptions", true);
        }
    }
}
