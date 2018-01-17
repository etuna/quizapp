package com.tunahantuna.etuna.quizapp3;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tunahantuna.etuna.quizapp3.OnlineUserFragment.OnListFragmentInteractionListener;
import com.tunahantuna.etuna.quizapp3.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class onlineUserAdapter extends RecyclerView.Adapter<onlineUserAdapter.ViewHolder> {

    private final List<OnlineUserFeed.OnlineUser> mValues;
    private final OnListFragmentInteractionListener mListener;

    public onlineUserAdapter(List<OnlineUserFeed.OnlineUser> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_onlineuser, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        Log.e(mValues.get(position).username,Integer.toString(mValues.get(position).id));
        holder.mIdView.setText(Integer.toString(mValues.get(position).id));
        holder.mUsernameView.setText(mValues.get(position).username);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mUsernameView;
        public OnlineUserFeed.OnlineUser mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mUsernameView = (TextView) view.findViewById(R.id.username);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mUsernameView.getText() + "'";
        }
    }
}
