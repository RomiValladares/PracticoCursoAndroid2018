package com.mycompany.fragmenttest2.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class DemoPagerAdapter extends FragmentStatePagerAdapter {
    public DemoPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new DemoFragment();
        Bundle args = new Bundle();
        // el objeto es solo un entero en este ejemplo
        args.putInt(DemoFragment.ARG_OBJECT, i + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return 40;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Object " + (position + 1);
    }
}

