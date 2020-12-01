package com.alykhaled.mathflashcards;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class InterestsAdapter extends RecyclerView.Adapter<InterestsAdapter.ViewHolder> {

    private final Context mContext;
    private final ArrayList<InterestItem> mLists;
    private LayoutInflater layoutInflater;
    public OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position, ArrayList<InterestItem> t);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    public InterestsAdapter(Context mContext, ArrayList<InterestItem> mLists) {
        this.mContext = mContext;
        this.mLists = mLists;
    }

    @NonNull
    @Override
    public InterestsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final InterestsAdapter.ViewHolder holder, final int position) {
        final InterestItem list = mLists.get(position);
        holder.mListName.setText(list.getInterest_name());
        Picasso.get().load(list.getInterest_pic()).fit().into(holder.mListImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    if (position != RecyclerView.NO_POSITION) {
                        mListener.onItemClick(position,mLists);
                    }
                }
                if (list.isSelected())
                {
                    holder.mListName.setBackgroundColor(0xFF00FF00);
                    list.setSelected(false);
                }
                else
                {
                    holder.mListName.setBackgroundColor(0xFF00FFFF);
                    list.setSelected(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mListName;
        public ImageView mListImage;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            mListName = itemView.findViewById(R.id.listText);
            mListImage = itemView.findViewById(R.id.list_back);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(itemView.getContext(), "upload failed: ", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }
    }
}
