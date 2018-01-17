package com.tunahantuna.etuna.quizapp3;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class FriendsActivity extends AppCompatActivity implements FriendItemFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        FriendFeed.ITEMS.clear();
        Log.e("oncreate first",Integer.toString(FriendFeed.ITEMS.size()));

        FriendItemFragment friendItemFragment = new FriendItemFragment();
        android.app.FragmentTransaction ft  = getFragmentManager().beginTransaction();
        ft.replace(R.id.friendFragmentContainer, friendItemFragment);
        ft.commit();


    }

    @Override
    public void onListFragmentInteraction(FriendFeed.FriendItem item) {

    }
}
