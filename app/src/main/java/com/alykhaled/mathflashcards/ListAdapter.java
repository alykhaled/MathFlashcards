package com.alykhaled.mathflashcards;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private final Context mContext;
    private final ArrayList<CardItem> mLists;
    private LayoutInflater layoutInflater;
    public OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick (int position, ArrayList<CardItem> t);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    public ListAdapter(Context mContext, ArrayList<CardItem> mLists) {
        this.mContext = mContext;
        this.mLists = mLists;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        CardItem card = mLists.get(position);

        holder.mTextQuestion.setText(card.getmCardQuestion());
        holder.mTextAnswer.setText(card.getmCardAnswer());

        int mCurrentPosition = position;
    }

    @Override
    public int getItemCount() {
        return mLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextQuestion;
        public TextView mTextAnswer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextQuestion = itemView.findViewById(R.id.questionTxt);
            mTextAnswer = itemView.findViewById(R.id.answerTxt);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position,mLists);
                        }
                    }
                }
            });
        }
    }
}
