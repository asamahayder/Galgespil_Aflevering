package com.example.galgespil_aflevering;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

//this was made using the recyclerView guide from codepath.com

public class ResultListAdapter extends RecyclerView.Adapter<ResultListAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView scoreView;
        public TextView wordView;
        public TextView timeView;

        public ViewHolder(View itemView) {
            super(itemView);

            scoreView = itemView.findViewById(R.id.scoreView);
            wordView = itemView.findViewById(R.id.wordView);
            timeView = itemView.findViewById(R.id.timeView);
        }
    }

    private List<ResultObject> resultObjectList;

    public ResultListAdapter(List<ResultObject> resultObjectList){
        this.resultObjectList = resultObjectList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View resultObjectView = inflater.inflate(R.layout.result_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(resultObjectView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResultObject resultObject = resultObjectList.get(position);

        TextView wordView = holder.wordView;
        TextView scoreView = holder.scoreView;
        TextView timeView = holder.timeView;

        wordView.setText(resultObject.getWord());
        scoreView.setText(resultObject.getScore());
        timeView.setText(resultObject.getTime());
    }

    @Override
    public int getItemCount() {
        return resultObjectList.size();
    }
}
