package com.mahendra.shopperstock.Commons;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by sez1 on 3/6/16.
 */
public class CustomViewPagerWitoutSwip extends ViewPager {

    public CustomViewPagerWitoutSwip(Context context) {
        super(context);
    }

    public CustomViewPagerWitoutSwip(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
        return false;
    }
}