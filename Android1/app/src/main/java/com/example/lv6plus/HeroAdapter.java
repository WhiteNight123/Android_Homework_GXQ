package com.example.lv6plus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.ViewHolder>{
    private List<Hero> mHeroList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View heroView;
        ImageView heroImage;
        TextView heroName;

        public ViewHolder(View view) {
            super(view);
            heroView = view;
            heroImage = (ImageView) view.findViewById(R.id.image_hero);
            heroName = (TextView) view.findViewById(R.id.tv_hero_name);
        }
    }


    public HeroAdapter(List<Hero> heroList) {
        mHeroList = heroList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lol, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        Hero hero = mHeroList.get(position);
        holder.heroImage.setImageResource(hero.getImageId());
        holder.heroName.setText(hero.getName());
    }

    @Override
    public int getItemCount() {
        return mHeroList.size();
    }
}
