package com.mahendra.shopperstock.adapter;

/**
 * page adapter for intro page
 * Created by GSS-Vishnu Gupta
 */

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


/**
 *  Adapter for intro page viewPager
 */
public class MyPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    Activity _act;


    public MyPageAdapter(FragmentManager fm, List<Fragment> fragments, Activity aaaa) {
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
