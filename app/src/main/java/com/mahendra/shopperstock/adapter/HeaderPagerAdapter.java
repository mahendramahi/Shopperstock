package com.mahendra.shopperstock.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

/**
 * Created by MK on 22-06-2016.
 */
public class HeaderPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    Activity _act;


    public HeaderPagerAdapter(FragmentManager fm, List<Fragment> fragments, Activity aaaa) {
        super(fm);
        this.fragments = fragments;
        this._act = aaaa;
    }

    @Override
    public Fragment getItem(int position) {

        return this.fragments.get(position);
    }


    @Override
    public int getCount() {
        return this.fragments.size();
    }

}//end MyPageAdapter()-----------------
