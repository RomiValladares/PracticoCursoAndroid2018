package com.mycompany.fragmenttest2.tabs;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.mycompany.fragmenttest2.R;

public class TabsActivity extends AppCompatActivity {
    DemoCollectionPagerAdapter mDemoPagerAdapter;
    ViewPager mViewPager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabstrip);

        mDemoPagerAdapter = new DemoCollectionPagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoPagerAdapter);


        mViewPager.setPageMargin(16);
        mViewPager.setPageTransformer(true, new FadeAndScaleTransformer());
    }
}
