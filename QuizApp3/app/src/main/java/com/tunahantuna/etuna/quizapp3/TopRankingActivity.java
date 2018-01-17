package com.tunahantuna.etuna.quizapp3;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TopRankingActivity extends AppCompatActivity implements TopRankItemFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_ranking);



        TopRankItemFragment frag = new TopRankItemFragment();
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.toprankContainer, frag);
        ft.commit();


    }

    @Override
    public void onListFragmentInteraction(TopRankFeed.TopRankItem item) {

    }
}
