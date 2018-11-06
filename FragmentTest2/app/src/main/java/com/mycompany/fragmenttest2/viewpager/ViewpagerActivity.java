package com.mycompany.fragmenttest2.viewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.mycompany.fragmenttest2.R;

public class ViewpagerActivity extends AppCompatActivity {
    DemoPagerAdapter mDemoPagerAdapter;
    ViewPager mViewPager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_demo);

        // ViewPager y los adapters usan la support library
        // usar getSupportFragmentManager
        mDemoPagerAdapter = new DemoPagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoPagerAdapter);
    }
}

