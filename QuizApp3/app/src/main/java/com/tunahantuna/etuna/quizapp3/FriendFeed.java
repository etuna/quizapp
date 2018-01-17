package com.tunahantuna.etuna.quizapp3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by etuna on 8/16/17.
 */

public class FriendFeed {
    public static final List<FriendItem> ITEMS = new ArrayList<FriendItem>();
    public static final Map<String, FriendItem> ITEM_MAP = new HashMap<String, FriendItem>();

    private FriendItemFragment.OnListFragmentInteractionListener mListener;

    public void addItem(FriendItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(Integer.toString(item.id), item);
    }


    public FriendItem createHomeFeedItem(int id, String username) {
        return new FriendItem(id, username);
    }



    public static class FriendItem {
        //Variables
        String username;
        int id;

        //Constructor
        public FriendItem(int id, String username) {
            this.id = id;
            this.username = username;
        }

    }
}
