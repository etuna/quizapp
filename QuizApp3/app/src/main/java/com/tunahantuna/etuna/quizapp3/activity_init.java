package com.tunahantuna.etuna.quizapp3;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class activity_init extends AppCompatActivity implements OnlineUserFragment.OnListFragmentInteractionListener{


    User user;
    DatabaseReference myRef;
    DatabaseHelper mydb ;

    DrawerLayout drawerLayout;
    ListView listView;

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    public void selectItem(int position){
        if(position == 0 ){
            Intent intent = new Intent(activity_init.this, ProfileActivity.class);
            Toast.makeText(this, "asdaswd", Toast.LENGTH_SHORT).show();
            intent.putExtra("user",user);
            startActivity(intent);

        }else if(position==1){
            Intent intent = new Intent(activity_init.this, FriendsActivity.class);
            intent.putExtra("user",user);
            startActivity(intent);

        }else if(position ==2 ){
            Intent intent = new Intent(activity_init.this, FQuizActivity.class);
            intent.putExtra("user",user);
            startActivity(intent);

        }else {
            Intent intent = new Intent(activity_init.this, TopRankingActivity.class);
            intent.putExtra("user",user);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        listView = (ListView) findViewById(R.id.drawerList);
        String[] dn;
        dn = getResources().getStringArray(R.array.drawerNav);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dn));
        listView.setOnItemClickListener(new DrawerItemClickListener());





        mydb = new DatabaseHelper(this);
        new getAllQuestionsToSQLite(0).execute();
        new getAllQuestionsToSQLite(1).execute();
        new getAllQuestionsToSQLite(2).execute();

        Intent intent = getIntent();
        user =(User) intent.getSerializableExtra("user");


        //------

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        myRef = db.getReference("Users/"+user.id+"/status");
        final DatabaseReference onlineConnections = db.getReference("OnlineUsers");

        final DatabaseReference connectedRef = db.getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {

                    DatabaseReference con = onlineConnections.push();
                    con.setValue(Boolean.TRUE);
                    myRef.setValue("online");
                    myRef.onDisconnect().setValue("offline");
                    con.onDisconnect().removeValue();
                    fragmentHandling();

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.err.println("Error occured!");
            }
        });


        //------




        //Button Handling - StartButton
        Button startButton = (Button) findViewById(R.id.StartButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        Intent intentToStart = new Intent(activity_init.this, CategoryList.class);
        intentToStart.putExtra("user",user);
        startActivity(intentToStart);
            }
        });

    }
    @Override
    public void onStart(){
        super.onStart();
        //fragmentHandling();
    }

    public void fragmentHandling(){
        //Fragment transaction
        OnlineUserFragment onlineUserFragment = new OnlineUserFragment();
        onlineUserFragment.setUser(user);
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.onlineUserContainerActivityInit, onlineUserFragment);
        ft.commit();
    }


    @Override
    public void onListFragmentInteraction(OnlineUserFeed.OnlineUser item) {

    }




    public class getAllQuestionsToSQLite extends AsyncTask<Void, Void, Question[]> {

        int id;
        public getAllQuestionsToSQLite(int id) {
            this.id = id;
        }


        @Override
        protected Question[] doInBackground(Void... params) {
            try {

                Question[] questionBank;
                QuestionPopulator populator = new QuestionPopulator();
                populator.questions.clear();
                populator.getQuestions(id);
                questionBank = populator.questionBank;


                if(id == 0) {
                    Question.questionsAndroid = null;
                    Question.questionsAndroid = questionBank;


                    for(int i = 0; i<questionBank.length;i++){
                        mydb.insertItem("Android",questionBank[i].question,questionBank[i].answer,questionBank[i].options.get(0),questionBank[i].options.get(1),questionBank[i].options.get(2),questionBank[i].options.get(3));
                    }


                } else if (id ==1){

                    Question.questionsMath = null;
                    Question.questionsMath = questionBank;


                    for(int i = 0; i<questionBank.length;i++){
                        mydb.insertItem("Math",questionBank[i].question,questionBank[i].answer,questionBank[i].options.get(0),questionBank[i].options.get(1),questionBank[i].options.get(2),questionBank[i].options.get(3));
                    }


                }else {
                    Question.questionsFood = null;
                    Question.questionsFood = questionBank;


                    for(int i = 0; i<questionBank.length;i++){
                        mydb.insertItem("Food",questionBank[i].question,questionBank[i].answer,questionBank[i].options.get(0),questionBank[i].options.get(1),questionBank[i].options.get(2),questionBank[i].options.get(3));
                    }

                }
                return questionBank;
            } catch (Exception e) {

            }
            return null;
        }


        @Override
        protected void onPostExecute(Question[] result) {
            super.onPostExecute(result);

        }
    }
}
