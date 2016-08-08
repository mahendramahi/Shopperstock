package com.mahendra.shopperstock;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.mahendra.shopperstock.adapter.Homelistadapter;

/**
 * Created by MK on 23-06-2016.
 */
public class Dummy extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.myhome);

       ListView myHomeList = (ListView) findViewById(R.id.myhomelist);


        View header = LayoutInflater.from(this).inflate(
                R.layout.myhomeheader, null);

        // ViewGroup header = (ViewGroup)
        // inflater.inflate(R.layout.over_view_header,
        // List_view_Diffrent_compney_rate_tag,false);

     /*   ViewPager pager = (ViewPager) header.findViewById(R.id.pager);

        List<Fragment> fragments = getFragments();

        HeaderPagerAdapter pageAdapter = new HeaderPagerAdapter(getActivity().getSupportFragmentManager(), fragments, getActivity());

        pager.setAdapter(pageAdapter);*/

       /* com.mahendra.shopperstock.Commons.CirclePageIndicator mIndicator = (com.mahendra.shopperstock.Commons.CirclePageIndicator) header.findViewById(R.id.indicator_new);
        mIndicator.setViewPager(pager);*/

        final RelativeLayout myheaderRl = (RelativeLayout) header.findViewById(R.id.myheaderRL);


        myHomeList.addHeaderView(header, null, false);
        Homelistadapter homelistAdapter = new Homelistadapter(this);

        myHomeList.setAdapter(homelistAdapter);
    }
}
