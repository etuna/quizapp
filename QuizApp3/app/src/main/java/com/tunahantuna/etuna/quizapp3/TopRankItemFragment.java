package com.tunahantuna.etuna.quizapp3;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tunahantuna.etuna.quizapp3.dummy.DummyContent;
import com.tunahantuna.etuna.quizapp3.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class TopRankItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TopRankItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static TopRankItemFragment newInstance(int columnCount) {
        TopRankItemFragment fragment = new TopRankItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_toprankitem_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            getTopRanks();
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(TopRankFeed.TopRankItem item);
    }


    public void getTopRanks() {


        TopRankFeed.ITEMS.clear();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Users");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<TopRankFeed.TopRankItem> array = new ArrayList<TopRankFeed.TopRankItem>();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    int id  = postSnapshot.child("id").getValue(Integer.class);
                    int points = postSnapshot.child("points").getValue(Integer.class);
                    String username = postSnapshot.child("username").getValue(String.class);

                    TopRankFeed.TopRankItem item = new TopRankFeed.TopRankItem(id,username,points);
                    array.add(item);

                }


                Collections.sort(array, new Comparator<TopRankFeed.TopRankItem>() {
                    @Override
                    public int compare(TopRankFeed.TopRankItem topRankItem, TopRankFeed.TopRankItem t1) {
                        return topRankItem.points > t1.points ? -1 : 1;
                    }
                });

                for(int i = 0; i<5;i++) {
                    if(array.size()>i){
                        TopRankFeed.ITEMS.add(array.get(i));
                    }
                }

                recyclerView.setAdapter(new topRankAdapter(TopRankFeed.ITEMS, mListener));





            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }




}
