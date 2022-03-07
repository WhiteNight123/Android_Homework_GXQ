package com.example.task6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Lv3RecycleAdapter extends RecyclerView.Adapter<Lv3RecycleAdapter.InnerHolder> {
    private ArrayList<Lv3Person> data;

    public Lv3RecycleAdapter(ArrayList<Lv3Person> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public Lv3RecycleAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.lv3_activity_item_rv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.tv1.setText(data.get(position).getPerson1());
        holder.tv2.setText(data.get(position).getPerson2());
        holder.tv3.setText(data.get(position).getPerson3());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class InnerHolder extends RecyclerView.ViewHolder {
        TextView tv1;
        TextView tv2;
        TextView tv3;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.lv3_tv_fragment1_1);
            tv2 = (TextView) itemView.findViewById(R.id.lv3_tv_fragment1_2);
            tv3 = (TextView) itemView.findViewById(R.id.lv3_tv_fragment1_3);
        }
    }
}
