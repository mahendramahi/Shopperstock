package com.mahendra.shopperstock.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mahendra.shopperstock.R;
import com.mahendra.shopperstock.adapter.KidsofferAdapter;
import com.mahendra.shopperstock.adapter.RecomndedAdapter;
import com.mahendra.shopperstock.adapter.SpecialoffAdapter;
import com.mahendra.shopperstock.adapter.WeeklytopAdapter;
import com.mahendra.shopperstock.adapter.ViewPagerAdapter;
import com.mahendra.shopperstock.custom.CustomGridView;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MK on 21-06-2016.
 */
public class HomeFragment extends Fragment {

    private int lastTopValue = 0;

    ListView myHomeList;
    ViewPager pager;
    ViewPagerAdapter mPagerAdapter;
    private CirclePageIndicator cindicator;
    private CustomGridView weeklytopgl,recomnded_gl,specialoffer_gl,kids_gl;
    private WeeklytopAdapter _weeklyAdapter;
    private RecomndedAdapter _recomAdapter;
    private SpecialoffAdapter _specialAdapter;
    private KidsofferAdapter _kidsofferAdapter;



    int[] images = {R.drawable.offer_one,R.drawable.offer_two,R.drawable.offer_tree};
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.home_fragment, container,
                false);



        pager = (ViewPager) rootView.findViewById(R.id.vpPager);
        cindicator = (CirclePageIndicator) rootView
                .findViewById(R.id.indicator);
        weeklytopgl = (CustomGridView) rootView.findViewById(R.id.weeklytopgl);
        recomnded_gl = (CustomGridView) rootView.findViewById(R.id.recomnded_gl);
        specialoffer_gl = (CustomGridView) rootView.findViewById(R.id.specialoffer_gl);
        kids_gl = (CustomGridView) rootView.findViewById(R.id.kids_gl);

        mPagerAdapter = new ViewPagerAdapter(getActivity(),
                images);

        _weeklyAdapter = new WeeklytopAdapter(getActivity());
        _recomAdapter = new RecomndedAdapter(getActivity());
        _specialAdapter = new SpecialoffAdapter(getActivity());
        _kidsofferAdapter = new KidsofferAdapter(getActivity());

        pager.setAdapter(mPagerAdapter);
        cindicator.setViewPager(pager);
        weeklytopgl.setAdapter(_weeklyAdapter);
        recomnded_gl.setAdapter(_recomAdapter);
        specialoffer_gl.setAdapter(_specialAdapter);
        kids_gl.setAdapter(_kidsofferAdapter);

        viewPagerAnimation();

        setBodyUI(rootView);
        
        return rootView;
    }

    private List<Fragment> getFragments() {
        List<Fragment> fList = new ArrayList<Fragment>();
        fList.add(MyFragmentPager.newInstance("Fragment 1", 1));
        fList.add(MyFragmentPager.newInstance("Fragment 2", 2));
        fList.add(MyFragmentPager.newInstance("Fragment 3", 3));
        fList.add(MyFragmentPager.newInstance("Fragment 4", 4));
        fList.add(MyFragmentPager.newInstance("Fragment 5", 5));
        return fList;

    } // end getFragments().............................


    private void setBodyUI(View rootView) {


    }

   private void viewPagerAnimation(){

       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {

               change_Page();
           }
       },3300);

   }

    private void change_Page(){

        int i = pager.getCurrentItem();
        if (i == (images.length-1)) {
            pager.setCurrentItem(0);
        }
        else {
            pager.setCurrentItem(pager.getCurrentItem()+1);
        }
        viewPagerAnimation();
    }
}
