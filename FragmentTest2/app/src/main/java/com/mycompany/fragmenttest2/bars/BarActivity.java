package com.mycompany.fragmenttest2.bars;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mycompany.fragmenttest2.R;


public class BarActivity extends AppCompatActivity {
    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);

        //paso 2
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //paso 3
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        TabLayout.Tab tab = tabLayout.newTab().setText("Tab 1");
        tabLayout.addTab(tab);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //paso 4
        mViewPager = findViewById(R.id.pager);
        mDemoCollectionPagerAdapter = new DemoCollectionPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);

        //paso 5
        TabLayout.TabLayoutOnPageChangeListener listener =
                new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
        mViewPager.addOnPageChangeListener(listener);

        //paso 6

        TabLayout.OnTabSelectedListener tabListener =
                new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        };

        tabLayout.addOnTabSelectedListener(tabListener);


    }

}
