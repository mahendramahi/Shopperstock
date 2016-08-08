package com.mahendra.shopperstock.Commons;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by girnar on 16/5/16.
 * Used to set custom fonts for EditText
 */
public class CusFntEditText extends EditText {

    public CusFntEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CusFntEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CusFntEditText(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat_Regular_0.otf");
            setTypeface(tf);
        }
    }
}
