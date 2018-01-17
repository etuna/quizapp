package com.tunahantuna.etuna.quizapp3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by etuna on 8/16/17.
 */

public class TopRankFeed {

    public static final List<TopRankItem> ITEMS = new ArrayList<TopRankItem>();
    public static final Map<String, TopRankItem> ITEM_MAP = new HashMap<String, TopRankItem>();

    private TopRankItemFragment.OnListFragmentInteractionListener mListener;

    public void addItem(TopRankItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(Integer.toString(item.id), item);
    }


    public TopRankItem createHomeFeedItem(int id, String username,int points) {
        return new TopRankItem(id, username,points);
    }



    public static class TopRankItem {
        //Variables
        String username;
        int id,points;

        //Constructor
        public TopRankItem(int id, String username, int points) {
            this.points = points;
            this.id = id;
            this.username = username;
        }

    }



}
