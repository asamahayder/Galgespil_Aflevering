package com.example.galgespil_aflevering;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//this was made using the recyclerView guide from codepath.com and https://antonioleiva.com/recyclerview-listener/

public class ChooseWordAdapter extends RecyclerView.Adapter<ChooseWordAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(String word);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView wordView;

        public ViewHolder(View itemView) {
            super(itemView);
            wordView = itemView.findViewById(R.id.wordView2);
        }

        public void bind(final String word, final OnItemClickListener listener){
            wordView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(word);
                }
            });
        }
    }

    private List<String> wordList;
    private OnItemClickListener listener;


    public ChooseWordAdapter(List<String> wordList, OnItemClickListener listener){
        this.wordList = wordList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View wordView = inflater.inflate(R.layout.word_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(wordView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String word = wordList.get(position);
        holder.bind(wordList.get(position), listener);
        TextView wordView = holder.wordView;
        wordView.setText(word);
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }
}
