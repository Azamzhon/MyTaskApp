package com.geektech.mytaskapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.mytaskapp.R;
import com.geektech.mytaskapp.models.Task;
import com.geektech.mytaskapp.ui.interfaces.OnItemClickListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private ArrayList<Task> tasks;
    private OnItemClickListener onItemClickListener;

    public TaskAdapter(ArrayList<Task> list) {
        tasks = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_task_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(tasks.get(position), position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private TextView createdTime;
        private TextView updatedTime;
        private View itemLine;
        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.rv_text);
            createdTime = itemView.findViewById(R.id.text_createdTime);
            updatedTime = itemView.findViewById(R.id.text_updatedTime);
            itemLine = itemView.findViewById(R.id.item_line);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItem(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemClickListener.onItemLongClick(getAdapterPosition());
                    return true;
                }
            });
        }

        public void bind(Task task, int position) {
            this.position = position;
            if (task.getTitle().length() > 4) {
                itemLine.setVisibility(View.VISIBLE);
            }
            textView.setText(task.getTitle());
            createdTime.setText(getDate(task.getCreatedAt()));
            updatedTime.setText(getDate(task.getUpdateAt()));
        }

        private String getDate(long time) {
            DateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            return dateFormat.format(time);
        }
    }
}