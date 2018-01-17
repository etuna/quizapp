package com.tunahantuna.quizapp;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.app.FragmentTransaction;
import android.app.Fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class PointsFragment extends Fragment {

    interface pointListener{
        void onClickItem(long id);
    }
    public pointListener pListener;
    private String nickname;
    public boolean colored=false;

    public Map<Integer, Integer> questionColors = new HashMap();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_points, container, false);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        pListener = (pointListener) context;
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        int color;

        // -1 wrong, 0 next, 1 correct, else no change
        //Button Handling------------------------
        Button b0 = (Button) view.findViewById(R.id.b0_100);
        color = questionColors.get(0);
        if(color==-1){
            b0.setBackgroundColor(Color.RED);
        } else if(color==0){
            b0.setBackgroundColor(Color.BLUE);
        } else if(color == 1){
            b0.setBackgroundColor(Color.GREEN);
        } else {
            //no change
        }

        b0.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                pListener.onClickItem(0);
            }
        });






        Button b1 = (Button) view.findViewById(R.id.b1_100);
        color = questionColors.get(1);
        if(color==-1){
            b1.setBackgroundColor(Color.RED);
        } else if(color==0){
            b1.setBackgroundColor(Color.BLUE);
        } else if(color == 1){
            b1.setBackgroundColor(Color.GREEN);
        } else {
            //no change
        }

        b1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                pListener.onClickItem(1);
            }
        });







        Button b2 = (Button) view.findViewById(R.id.b2_100);
        color = questionColors.get(2);
        if(color==-1){
            b2.setBackgroundColor(Color.RED);
        } else if(color==0){
            b2.setBackgroundColor(Color.BLUE);
        } else if(color == 1){
            b2.setBackgroundColor(Color.GREEN);
        } else {
            //no change
        }

        b2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                pListener.onClickItem(2);
            }
        });








        Button b3 = (Button) view.findViewById(R.id.b3_200);
        color = questionColors.get(3);
        if(color==-1){
            b3.setBackgroundColor(Color.RED);
        } else if(color==0){
            b3.setBackgroundColor(Color.BLUE);
        } else if(color == 1){
            b3.setBackgroundColor(Color.GREEN);
        } else {
            //no change
        }

        b3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                pListener.onClickItem(3);
            }
        });









        Button b4 = (Button) view.findViewById(R.id.b4_200);
        color = questionColors.get(4);
        if(color==-1){
            b4.setBackgroundColor(Color.RED);
        } else if(color==0){
            b4.setBackgroundColor(Color.BLUE);
        } else if(color == 1){
            b4.setBackgroundColor(Color.GREEN);
        } else {
            //no change
        }

        b4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                pListener.onClickItem(4);
            }
        });








        Button b5 = (Button) view.findViewById(R.id.b5_200);
        color = questionColors.get(5);
        if(color==-1){
            b5.setBackgroundColor(Color.RED);
        } else if(color==0){
            b5.setBackgroundColor(Color.BLUE);
        } else if(color == 1){
            b5.setBackgroundColor(Color.GREEN);
        } else {
            //no change
        }

        b5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                pListener.onClickItem(5);
            }
        });








        Button b6 = (Button) view.findViewById(R.id.b6_300);
        color = questionColors.get(6);
        if(color==-1){
            b6.setBackgroundColor(Color.RED);
        } else if(color==0){
            b6.setBackgroundColor(Color.BLUE);
        } else if(color == 1){
            b6.setBackgroundColor(Color.GREEN);
        } else {
            //no change
        }

        b6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                pListener.onClickItem(6);
            }
        });









        Button b7 = (Button) view.findViewById(R.id.b7_300);
        color = questionColors.get(7);
        if(color==-1){
            b7.setBackgroundColor(Color.RED);
        } else if(color==0){
            b7.setBackgroundColor(Color.BLUE);
        } else if(color == 1){
            b7.setBackgroundColor(Color.GREEN);
        } else {
            //no change
        }

        b7.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                pListener.onClickItem(7);
            }
        });










        Button b8 = (Button) view.findViewById(R.id.b8_300);
        color = questionColors.get(8);
        if(color==-1){
            b8.setBackgroundColor(Color.RED);
        } else if(color==0){
            b8.setBackgroundColor(Color.BLUE);
        } else if(color == 1){
            b8.setBackgroundColor(Color.GREEN);
        } else {
            //no change
        }

        b8.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                pListener.onClickItem(8);
            }
        });









        Button b9 = (Button) view.findViewById(R.id.b9_400);
        color = questionColors.get(9);
        if(color==-1){
            b9.setBackgroundColor(Color.RED);
        } else if(color==0){
            b9.setBackgroundColor(Color.BLUE);
        } else if(color == 1){
            b9.setBackgroundColor(Color.GREEN);
        } else {
            //no change
        }

        b9.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                pListener.onClickItem(9);
            }
        });









        Button b10 = (Button) view.findViewById(R.id.b10_400);
        color = questionColors.get(10);
        if(color==-1){
            b10.setBackgroundColor(Color.RED);
        } else if(color==0){
            b10.setBackgroundColor(Color.BLUE);
        } else if(color == 1){
            b10.setBackgroundColor(Color.GREEN);
        } else {
            //no change
        }

        b10.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                pListener.onClickItem(10);
            }
        });









        Button b11 = (Button) view.findViewById(R.id.b11_400);
        color = questionColors.get(11);
        if(color==-1){
            b11.setBackgroundColor(Color.RED);
        } else if(color==0){
            b11.setBackgroundColor(Color.BLUE);
        } else if(color == 1){
            b11.setBackgroundColor(Color.GREEN);
        } else {
            //no change
        }

        b11.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                pListener.onClickItem(11);
            }
        });







        Button endQuiz = (Button) view.findViewById(R.id.endQuiz);
        endQuiz.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                pListener.onClickItem(-1);
            }
        });
        //---------------------------------------------------------




    }
    public void setColorOfQuestion(HashMap<Integer,Integer> hm){
        questionColors = hm;
    }

}
