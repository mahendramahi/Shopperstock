package com.mahendra.shopperstock;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.mahendra.shopperstock.Commons.CustomViewPager;
import com.mahendra.shopperstock.Commons.Utility;
import com.mahendra.shopperstock.adapter.MyPageAdapter;
import com.mahendra.shopperstock.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends FragmentActivity {

    public static CustomViewPager pager;
    public static TextView txtIntroSkip, txtIntroNext;
    private PagerAdapter mPagerAdapter;
    private Activity _activity = this;
    private SharedPreferences sharedPreferences;
    private boolean isIntroShow;

    /**
     * It is used to change UI when click on skip button on intro page
     */
    public static void chnageUIonSkip() {
        pager.setCurrentItem(2);
    }// end chnageUIonSkip()...........................

    /**
     * It is used to change UI when click on next button on intro page
     */
    public static void chnageUIonNext(int pos) {
        if (pos == 1) {
            pager.setCurrentItem(1);
        } else {
            pager.setCurrentItem(2);
        }
    }// end chnageUIonNext()...........................


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        initialiseView();

        setViewPagerAdapter();
    }// end onCreate().....................

    @Override
    protected void onStart() {
        super.onStart();

        isIntroShow = sharedPreferences.getBoolean("isintroshow", false);
        if (isIntroShow) {
            pager.setCurrentItem(2);
        }

    }

    /**
     * it is used to initialise views
     */
    public void initialiseView() {
        pager = (CustomViewPager) findViewById(R.id.pager);

        //  pager.setPageTransformer(false, new NoPageTransformer());


    }// end initialiseView().....................

    private static class NoPageTransformer implements ViewPager.PageTransformer {
        public void transformPage(View view, float position) {
            if (position < 0) {
                view.setScrollX((int) ((float) (view.getWidth()) * position));
            } else if (position > 0) {
                view.setScrollX(-(int) ((float) (view.getWidth()) * -position));
            } else {
                view.setScrollX(0);
            }
        }
    }

    /*
     * It is used to set adapter for viewPager
     */
    public void setViewPagerAdapter() {
        List<Fragment> fragments = getFragments();

        MyPageAdapter pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments, _activity);

        pager.setAdapter(pageAdapter);

        com.viewpagerindicator.CirclePageIndicator mIndicator = (com.viewpagerindicator.CirclePageIndicator) findViewById(R.id.indicator_new);
        mIndicator.setViewPager(pager);
        try {
            if (getIntent().getStringExtra("Where").equalsIgnoreCase("Terms")) {
                chnageUIonSkip();
            } else {

            }

        } catch (Exception e) {

        }


    } //end setViewPagerAdapter().........................

    /*
     * It returns list of fragments
     */
    private List<Fragment> getFragments() {
        List<Fragment> fList = new ArrayList<Fragment>();
        fList.add(MyFragment.newInstance("Fragment 1", 1));
        fList.add(MyFragment.newInstance("Fragment 2", 2));
        fList.add(MyFragment.newInstance("Fragment 3", 3));
        return fList;

    } // end getFragments().............................

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // Used to finish activity
        Utility.getUtilityInstance().backEvent(_activity);
    }


}//end main class............
