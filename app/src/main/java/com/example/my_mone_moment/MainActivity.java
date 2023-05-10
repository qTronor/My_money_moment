package com.example.my_mone_moment;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.my_mone_moment.data.ViewModel;
import com.example.my_mone_moment.fragments.Fragment_first;
import com.example.my_mone_moment.fragments.Fragment_second;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;

    private static final int num_pages = 2;
    private ViewPager2 viewPager2;
    private FragmentStateAdapter fragmentStateAdapter;

    private TextView sum_textView;
    private ViewModel viewModel;
    //private int total_sum;

    private int[] labels = new int[]{R.string.expense, R.string.income};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        LiveData<Integer> totalSum = viewModel.getExpenseSum();

        viewPager2 = findViewById(R.id.pager);
        fragmentStateAdapter = new ScreenSlideAdapter(this);
        viewPager2.setAdapter(fragmentStateAdapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText(labels[position])).attach();

        sum_textView = findViewById(R.id.sum_textView);

        totalSum.observe(this, integer -> {
            if(integer == null) {
                //Toast.makeText(MainActivity.this, "0", Toast.LENGTH_SHORT).show();
                sum_textView.setText("0");
            }
            else
                sum_textView.setText(String.valueOf(integer));
        });

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

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}