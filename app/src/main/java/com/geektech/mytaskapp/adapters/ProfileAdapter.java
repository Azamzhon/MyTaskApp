package com.geektech.mytaskapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.mytaskapp.R;
import com.geektech.mytaskapp.models.Box;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private List<Box> boxList;

    public ProfileAdapter(List<Box> boxList) {
        this.boxList = boxList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.list_profile_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.img_book_thumbnail.setImageResource(boxList.get(position).getThumbnail());
    }

    @Override
    public int getItemCount() {
        return boxList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_book_thumbnail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_book_thumbnail = (ImageView) itemView.findViewById(R.id.pli_image_view);
        }
    }
}