package com.example.task7;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Lv2Adapter extends RecyclerView.Adapter<Lv2Adapter.InnerHolder> {
    private ArrayList<Lv2Data> data;

    public Lv2Adapter(ArrayList<Lv2Data> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public Lv2Adapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.lv2_item_rv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Lv2Adapter.InnerHolder holder, int position) {

        holder.mTvName.setText(data.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class InnerHolder extends RecyclerView.ViewHolder {

        TextView mTvName;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.lv2_tv_name);
        }
    }
}
