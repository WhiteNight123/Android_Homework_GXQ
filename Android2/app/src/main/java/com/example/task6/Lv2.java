package com.example.task6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class Lv2 extends AppCompatActivity {
    private final String[] tabs = {"页面1", "页面2", "页面3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lv2_activity);
        ViewPager2 viewPager2 = findViewById(R.id.lv2_view_pager_fragment);
        TabLayout tabLayout = findViewById(R.id.lv2_tabLayout);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Lv2Fragment1());
        fragments.add(new Lv2Fragment2());
        fragments.add(new Lv2Fragment3());
        Lv2FragmentPagerAdapter adapter = new Lv2FragmentPagerAdapter(this, fragments);
        viewPager2.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabs[position]);
            }
        }).attach();
    }
}