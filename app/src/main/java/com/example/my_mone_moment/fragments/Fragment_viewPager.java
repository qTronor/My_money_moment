package com.example.my_mone_moment.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.my_mone_moment.MainActivity;
import com.example.my_mone_moment.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Fragment_viewPager extends Fragment {

    private static final int num_pages = 2;
    private ViewPager2 viewPager2;
    private FragmentStateAdapter fragmentStateAdapter;
    private int[] labels = new int[]{R.string.expense, R.string.income};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        // Inflate the layout for this fragment
        viewPager2 = view.findViewById(R.id.pager);
        fragmentStateAdapter = new ScreenSlideAdapter(this);
        viewPager2.setAdapter(fragmentStateAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText(labels[position])).attach();

        return view;
    }

    private class ScreenSlideAdapter extends FragmentStateAdapter {

        public ScreenSlideAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
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