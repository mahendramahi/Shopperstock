package com.mahendra.shopperstock.Commons;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by girnar on 16/5/16.
 * Used to set custom fonts for TextView
 */
public class CusFntTextView extends TextView {

    public CusFntTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CusFntTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CusFntTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat_Light_0.otf");
            setTypeface(tf);
        }
    }
}
