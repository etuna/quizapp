package com.tunahantuna.quizapp;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.app.Activity;
/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryListFragment extends ListFragment {

    public interface categoryListener {
        void itemClicked(long id);
    }

    public categoryListener catListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //Categories
        String[] categories = new String[3];
        categories[0] = "Android";
        categories[1] = "Math";
        categories[2] = "Food";
        //------------------------------------------

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(inflater.getContext(),android.R.layout.simple_list_item_1, categories);
        setListAdapter(categoryAdapter);

        return super.onCreateView(inflater,container,savedInstanceState);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        catListener = (categoryListener) activity;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (catListener != null) {
            catListener.itemClicked(id);
        }
    }

}
