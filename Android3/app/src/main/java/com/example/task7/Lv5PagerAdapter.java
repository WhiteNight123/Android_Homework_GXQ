package com.example.task7;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;


public class Lv5PagerAdapter extends RecyclerView.Adapter<Lv5PagerAdapter.InnerHolder> {
    private ArrayList<Lv5Data> data;
    public Context context;

    public Lv5PagerAdapter(Context context, ArrayList<Lv5Data> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.lv5_item_vp, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.textView.setText(data.get(position).getTitle());

        Glide.with(context).load(data.get(position).getImageUrl()).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                holder.imageView.setImageDrawable(resource);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public InnerHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.lv5_iv_vp2);
            textView = itemView.findViewById(R.id.lv5_tv_vp2);
        }
    }
}
