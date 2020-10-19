package com.example.roomwords.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwords.R;
import com.example.roomwords.model.Task;

import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.WordViewHolder> {
    private final LayoutInflater mInflater;
    private List<Task> mTasks;
    private static ClickListener checkBoxOnClickListener;

    public TaskListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void setCheckBoxOnClickListener(ClickListener clickListener) {
        TaskListAdapter.checkBoxOnClickListener = clickListener;
    }

    @NonNull
    @Override
    public TaskListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskListAdapter.WordViewHolder holder, int position) {
        if (mTasks != null) {
            Task current = mTasks.get(position);
            holder.wordItemView.setText(current.getWord());
            holder.isDone.setChecked(current.isDone);
        } else {
            holder.wordItemView.setText("No word");
        }
    }

    public void setTasks(List<Task> mTasks) {
        this.mTasks = mTasks;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mTasks != null) {
            return mTasks.size();
        }
        return 0;
    }

    public Task getWordAtPosition(int position) {
        return mTasks.get(position);
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        private TextView wordItemView;
        private CheckBox isDone;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
            isDone = itemView.findViewById(R.id.checkBox);
        }


    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}
