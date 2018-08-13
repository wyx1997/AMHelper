package com.am.example.amtest.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.am.example.amtest.fragment.LuntanDongtaiFragment;

public class LuntanViewPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public LuntanViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            default: return LuntanDongtaiFragment.newInstance(context);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
