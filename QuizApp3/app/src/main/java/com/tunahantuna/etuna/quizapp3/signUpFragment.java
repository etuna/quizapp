package com.tunahantuna.etuna.quizapp3;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.Semaphore;


/**
 * A simple {@link Fragment} subclass.
 */
public class signUpFragment extends Fragment {

    //Variables
    String username, email, passwordEntered, passwordConfirmed;
    EditText usernameET, emailET, passwordEnteredET, passwordConfirmedET;
    CheckBox checkBoxAggrement;
    boolean checkboxIsChecked;
    Button signUpButton;
    boolean unique;
    int waitForData;
    Semaphore s;
    boolean isReady=true;
    long numOfUser;
    DatabaseReference mRef;
    //----------------------------

    //usernameCherker Listener
    public interface usernameCheckListener{
        void onStart();
        void onSuccess(DataSnapshot data);
    }

    //SignUp Listener
    public interface signUpListener {
        void onSignUpButtonClicked();
    }
    //----------------------------

    //Firebase Auth
    private FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference ref;

    //ProgressDialog
    ProgressDialog loadingDialog;

    //User
    String name, surname;
    String emaiL;


    EditText nameET, surnameET;


    public signUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        nameET = (EditText) view.findViewById(R.id.name);
        surnameET = (EditText) view.findViewById(R.id.surname);
        usernameET = (EditText) view.findViewById(R.id.username);
        usernameET.setText(username);
        emailET = (EditText) view.findViewById(R.id.email);
        emailET.setText(email);
        passwordEnteredET = (EditText) view.findViewById(R.id.password);
        passwordEnteredET.setText(passwordEntered);
        passwordConfirmedET = (EditText) view.findViewById(R.id.repassword);
        passwordConfirmedET.setText(passwordConfirmed);
        checkBoxAggrement = (CheckBox) view.findViewById(R.id.checkBoxAggrement);
        checkBoxAggrement.setChecked(checkboxIsChecked);

        signUpButton = (Button) view.findViewById(R.id.signUpButtonSignUpFragment);
        if (savedInstanceState != null) {



            name = savedInstanceState.getString("name");
            surname = savedInstanceState.getString("surname");
            username = savedInstanceState.getString("username");
            usernameET.setText(username);
            email = savedInstanceState.getString("email");
            emailET.setText(email);
            passwordEntered = savedInstanceState.getString("passwordEntered");
            passwordEnteredET.setText(passwordEntered);
            passwordConfirmed = savedInstanceState.getString("passwordConfirmed");
            passwordConfirmedET.setText(passwordConfirmed);
            checkBoxAggrement.setChecked(savedInstanceState.getBoolean("isCBChecked"));

        }
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        View view = getView();

        unique = true;
        isReady = true;

        //Firebase Connections
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("Users");



        loadingDialog = new ProgressDialog(getActivity());

        name = nameET.getText().toString();
        surname = surnameET.getText().toString();
        username = usernameET.getText().toString().trim();
        email = emailET.getText().toString().trim();
        passwordEntered = passwordEnteredET.getText().toString().trim();
        passwordConfirmed = passwordConfirmedET.getText().toString().trim();


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadingDialog.setMessage("Please wait while registering...");
                loadingDialog.show();

                name = nameET.getText().toString();
                surname = surnameET.getText().toString();
                username = usernameET.getText().toString().trim();
                email = emailET.getText().toString().trim();
                passwordEntered = passwordEnteredET.getText().toString().trim();
                passwordConfirmed = passwordConfirmedET.getText().toString().trim();
                if(!username.equals("") && !email.equals("") && !passwordEntered.equals("") && !passwordConfirmed.equals("")) {
                    boolean registered = checkIsUniqueAndRegister(ref, username);
                } else {
                    loadingDialog.hide();
                    Toast.makeText(getActivity(), "Please check your information", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    //If checkbox is checked
    public boolean isCBChecked() {
        return checkBoxAggrement.isChecked();

    }
    //----------------------------


    //Checks whether new user is unique,it is taken before or not
    public boolean checkIsUniqueAndRegister(final DatabaseReference ref, final String username){
        //Search username in database


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    if (postSnapshot.child("username").getValue(String.class).equals(username)) {

                        Toast.makeText(getActivity(), username + " is already taken", Toast.LENGTH_SHORT).show();
                        loadingDialog.hide();
                        return;

                    }
                }
                registerUser();
                setReady(true);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        return isReady;


    }
    //----------------------------


    public void onSaveInstanceState(Bundle b) {
        b.putString("username", username);
        b.putString("email", email);
        b.putString("passwordEntered", passwordEntered);
        b.putString("passwordConfirmed", passwordConfirmed);
        // b.putBoolean("isCBChecked", isCBChecked());
    }

    public void setUsername(String text) {
        username = text;
    }

    public void setEmail(String text) {
        email = text;
    }

    public void setPasswordEntered(String text) {
        passwordEntered = text;
    }

    public void setPasswordConfirmed(String text) {
        passwordConfirmed = text;
    }

    public void setCheckBoxAggrement(boolean checked) {
        checkboxIsChecked = checked;
    }


    public void registerUser()  {

        if (isCBChecked()) {
            if (passwordEntered.equals(passwordConfirmed)) {

                auth.createUserWithEmailAndPassword(email, passwordEntered).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {


                            //Writing to database/users
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            mRef= database.getReference("Users");

                            mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    setNumOfUser(dataSnapshot.getChildrenCount());
                                    int userID = (int) numOfUser;
                                    mRef=mRef.child(Integer.toString(userID+1));
                                    mRef.child("id").setValue(userID+1);
                                    mRef.child("username").setValue(username);
                                    mRef.child("email").setValue(email);
                                    mRef.child("password").setValue(passwordEntered);
                                    mRef.child("name").setValue(name);
                                    mRef.child("surname").setValue(surname);
                                    loadingDialog.hide();

                                    final Intent intent = new Intent(getActivity(), activity_init.class);
                                    final User user = new User((int)numOfUser+1,username, email, passwordEntered,name,surname);
                                    intent.putExtra("user", user);


                                    Toast.makeText(getActivity(), "Successfully completed!", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                    loadingDialog.hide();
                                }



                            });






                        } else {
                            loadingDialog.hide();
                            Toast.makeText(getActivity(), "Sorry, system encountered to a problem", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            } else {
                loadingDialog.hide();
                Toast.makeText(getActivity(), "Please check your passwords", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            loadingDialog.hide();
            Toast.makeText(getActivity(), "Please accept the Terms of Service and Privacy Policy", Toast.LENGTH_SHORT).show();
        }
    }





    public boolean setReady(boolean r){
        isReady = r;
        return isReady;
    }

    public void setNumOfUser(long num){
        this.numOfUser = num;
    }


}
