package com.tunahantuna.etuna.quizapp3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileEditActivity extends AppCompatActivity {

    EditText usernameET, nameET,surnameET;
    Button saveButton;

    String oldUsername, oldName, oldSurname;
    String newUsername, newName, newSurname;

    ProgressDialog pd ;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        saveButton = (Button) findViewById(R.id.saveButtonProfileEdit);
        usernameET = (EditText) findViewById(R.id.usernameProfileEditAct);
        nameET = (EditText) findViewById(R.id.nameProfileEditAct);
        surnameET = (EditText) findViewById(R.id.surnameProfileEditAct);


        if(savedInstanceState != null) {
            user =(User) savedInstanceState.getSerializable("user");
            newUsername = savedInstanceState.getString("newUsername");
            newName = savedInstanceState.getString("newName");
            newSurname = savedInstanceState.getString("newSurname");
            usernameET.setText(newUsername);
            nameET.setText(newName);
            surnameET.setText(newSurname);
        } else {


           // Intent intent = getIntent();
            //user = (User) intent.getSerializableExtra("user");
            user = new User(1,"etuna","etuna@ku.edu.tr","","Esat Tunahan","Tuna");
        }
        oldUsername = user.username;
        oldName = user.name;
        oldSurname = user.surname;



        pd = new ProgressDialog(this);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.setMessage("Please wait...");
                pd.show();
                newUsername = usernameET.getText().toString();
                newName = nameET.getText().toString();
                newSurname = surnameET.getText().toString();

                saveUserAndGoBack();
            }
        });



    }


    public void saveUserAndGoBack() {


        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("Users/"+user.id);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DatabaseReference mRef = dataSnapshot.getRef();
                mRef.child("name").setValue(newName);
                mRef.child("surname").setValue(newSurname);
                mRef.child("username").setValue(newUsername);
                user.username = newUsername;
                user.name = newName;
                user.surname = newSurname;
                pd.hide();
                Intent intent = new Intent(ProfileEditActivity.this, ProfileActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }


    public void onSaveInstanceState(Bundle b) {


        saveButton = (Button) findViewById(R.id.saveButtonProfileEdit);
        usernameET = (EditText) findViewById(R.id.usernameProfileEditAct);
        nameET = (EditText) findViewById(R.id.nameProfileEditAct);
        surnameET = (EditText) findViewById(R.id.surnameProfileEditAct);

        newUsername = usernameET.getText().toString();
        newName = nameET.getText().toString();
        newSurname = surnameET.getText().toString();

        b.putSerializable("user",user);
        b.putString("newUsername",newUsername);
        b.putString("newName",newName);
        b.putString("newSurname",newSurname);

    }
}
