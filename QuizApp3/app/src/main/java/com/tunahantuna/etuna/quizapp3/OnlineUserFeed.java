package com.tunahantuna.etuna.quizapp3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by etuna on 8/16/17.
 */

public class OnlineUserFeed {
    public static final List<OnlineUser> ITEMS = new ArrayList<OnlineUser>();
    public static final Map<String, OnlineUser> ITEM_MAP = new HashMap<String, OnlineUser>();

    private OnlineUserFragment.OnListFragmentInteractionListener mListener;

    public void addItem(OnlineUser item) {
        ITEMS.add(item);
        ITEM_MAP.put(Integer.toString(item.id), item);
    }


    public OnlineUser createHomeFeedItem(int id, String username) {
        return new OnlineUser(id, username);
    }



    public static class OnlineUser {
        //Variables
        String username;
        int id;

        //Constructor
        public OnlineUser(int id, String username) {
            this.id = id;
            this.username = username;
        }

    }
}
