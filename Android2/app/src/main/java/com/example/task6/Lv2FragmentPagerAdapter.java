package com.example.task6;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class Lv2FragmentPagerAdapter extends FragmentStateAdapter {
    private final ArrayList<Fragment>fragments;
    public Lv2FragmentPagerAdapter(FragmentActivity fragmentActivity,ArrayList<Fragment>fragments){
        super(fragmentActivity);
        this.fragments=fragments;
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
