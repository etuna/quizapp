package com.tunahantuna.etuna.quizapp3;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by etuna on 8/16/17.
 */

public class QuestionPopulator {

    public ArrayList<Question> questions ;
    String question, posAnswer1, posAnswer2, posAnswer3, posAnswer4, answer;
    String cat ;
    Question[] questionBank;


    public QuestionPopulator(){
        questions = new ArrayList<Question>();
        questionBank = new Question[12];
    }


    public void getQuestions(int category){

        if(category == 0){
            cat = "Android";
        } else if(category == 1) {
            cat = "Math";
        }else {
            cat ="Food";
        }




        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref  = database.getReference("Questions/"+cat);


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){

                    question = postSnapshot.child("question").getValue(String.class);
                    answer = postSnapshot.child("answer").getValue(String.class);
                    posAnswer1 = postSnapshot.child("option1").getValue(String.class);
                    posAnswer2 = postSnapshot.child("option2").getValue(String.class);
                    posAnswer3 = postSnapshot.child("option3").getValue(String.class);
                    posAnswer4 = postSnapshot.child("option4").getValue(String.class);

                    ArrayList<String> options = new ArrayList<String>();
                    options.add(posAnswer1);
                    options.add(posAnswer2);
                    options.add(posAnswer3);
                    options.add(posAnswer4);

                    Question q = new Question(question,answer,options);

                    questions.add(q);
                }

                for(int i = 0; i<12 ; i++){

                    if(questions.size()>i) {
                        Question q = questions.get(i);
                        questionBank[i] = q;
                    }
                }





            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }





}
