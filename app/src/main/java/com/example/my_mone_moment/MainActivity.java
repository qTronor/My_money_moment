package com.example.my_mone_moment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.my_mone_moment.fragments.Fragment_first;
import com.example.my_mone_moment.fragments.Fragment_second;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class MainActivity extends FragmentActivity {

    private static final int num_pages = 2;
    private ViewPager2 viewPager2;
    private FragmentStateAdapter fragmentStateAdapter;

    private int[] labels = new int[]{R.string.expense, R.string.income};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.pager);
        fragmentStateAdapter = new ScreenSlideAdapter(this);
        viewPager2.setAdapter(fragmentStateAdapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText(labels[position])).attach();
    }

    private class ScreenSlideAdapter extends FragmentStateAdapter{
        public ScreenSlideAdapter(MainActivity mainActivity){
            super(mainActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch(position) {
                case 0:
                    return new Fragment_first();
                case 1:
                    return new Fragment_second();
                default:
                    return null;
            }
        }

        @Override
        public int getItemCount() {
            return num_pages;
        }
    }

}