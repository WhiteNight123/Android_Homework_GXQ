package com.example.task6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import java.util.ArrayList;

public class Lv3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lv3_activity);
        ViewPager2 viewPager2 = findViewById(R.id.lv3_view_pager);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Lv3Fragment1());
        fragments.add(new Lv3Fragment2());
        FragmentStateAdapter adapter = new Lv3FragmentPaperAdapter(this, fragments);
        viewPager2.setAdapter(adapter);
    }
}