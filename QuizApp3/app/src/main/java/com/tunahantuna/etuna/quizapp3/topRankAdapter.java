package com.tunahantuna.etuna.quizapp3;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tunahantuna.etuna.quizapp3.TopRankItemFragment.OnListFragmentInteractionListener;
import com.tunahantuna.etuna.quizapp3.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class topRankAdapter extends RecyclerView.Adapter<topRankAdapter.ViewHolder> {

    private final List<TopRankFeed.TopRankItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public topRankAdapter(List<TopRankFeed.TopRankItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_toprankitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.pointsView.setText(Integer.toString(mValues.get(position).points));
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
        public final TextView pointsView;
        public final TextView mUsernameView;
        public TopRankFeed.TopRankItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            pointsView = (TextView) view.findViewById(R.id.id);
            mUsernameView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mUsernameView.getText() + "'";
        }
    }
}
