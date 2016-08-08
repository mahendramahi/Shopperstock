package com.mahendra.shopperstock.Commons;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;



public class Utils {
//public static final String BASE_URL = "http://54.169.69.143:8080";
//public static final String BASE_URL = "http://192.168.16.210:9000";
public static final String BASE_URL = "https://www.pamusa.com";



    public static final String SIGNIN_URL = "/api/v1/auth/login";
    public static final String SIGNUP_URL = "/api/v1/auth/register";
    public static final String SEND_OTP_URL = "/api/v1/auth/otp";
    public static final String VERIFY_OTP_URL = "/api/v1/auth/verify";
    public static final String FORGOT_PASSWORD_URL = "/api/v1/auth/forgot/";
    public static final String RESET_PASSWORD_URL = "/api/v1/auth/reset";
    public static final String SELF_CHANGE_URL = "/api/v1/auth/change/user";
    public static final String SIGNOUT_URL = "/api/v1/auth/signout";

    public static final String LOAD_FAVOURITES_DETAILS_URL = "/api/v1/lo/fav/";
    public static final String MENU_URL = "/api/v1/general/menu";
    public static final String NEWS_DETAILS_URL = "/api/v1/general/news/";
    public static final String NEWS_LIST_URL = "/api/v1/general/news/list/0";
    public static final String NEWS_LIST_URL1 = "/api/v1/general/news/list/1";
    public static final String ABOUTUS_URL = "/api/v1/general/about";
    public static final String SPECIAL_OFFERS_URL = "/api/v1/general/offers";
    public static final String CONTACTUS_URL = "/api/v1/general/contact";

    public static final String LOAD_DATA_REQUEST_URL = "/api/v1/lo/data";
    public static final String LOAD_NEW_REQUEST_URL = "/api/v1/lo/new";
    public static final String LOAD_FAVOURITES_URL = "/api/v1/lo/fav";
    public static final String LOAD_TRACK_LIST_URL = "/api/v1/lo/track";
    public static final String LOAD_TRACK_DETAILS_URL = "/api/v1/lo/track/";
    public static final String LOAD_HISTORY_LIST_URL = "/api/v1/lo/history";
    public static final String LOAD_HISTORY_DETAILS_URL = "/api/v1/lo/history/";
    public static final String LOAD_CHANGE_REQUEST_URL = "/api/v1/lo/change/load/";
    public static final String LOAD_FEEDBACK_URL = "/api/v1/lo/feedback/";


    public static final String TRUCK_CHANGE_REQUEST_URL = "/api/v1/to/truck/change/";
    public static final String TRUCK_LIST_URL = "/api/v1/to/truck";
    public static final String TRUCK_DETAILS_URL = "/api/v1/to/truck/";
    public static final String TRUCK_TRIPS_URL = "/api/v1/to/history/";
    public static final String DRIVER_LIST_URL = "/api/v1/to/driver";
    public static final String DRIVER_INFO_URL = "/api/v1/to/driver/";
    public static final String FIND_LOAD_LIST_URL = "/api/v1/to/load";
    public static final String FIND_LOAD_DETAILS_URL = "/api/v1/to/load/";
    public static final String FIND_REQUEST_LOAD_URL = "/api/v1/to/load/request/";
    public static final String DRIVER_CHANGE_REQUEST_URL = "/api/v1/to/driver/change/";

    public static final String DRIVER_FEEDBACK_URL = "/api/v1/lo/feedback/";


    // ---------------------------------------------------Fonts---------------------------------------------------
    public static final String BOLD_ITALIC_FONT = "fonts/Adobe-Garamond-Pro-Bold-Italic_2007.ttf";
    public static final String BOLD_FONT = "fonts/Adobe-Garamond-Pro-Bold_2008.ttf";
    public static final String ITALIC_2009_FONT = "fonts/Adobe-Garamond-Pro-Italic_2009.ttf";
    public static final String SEMIBOLD_ITALIC_2010_FONT = "fonts/Adobe-Garamond-Pro-Semibold-Italic_2010.ttf";
    public static final String SEMIBOLD_2011_FONT = "fonts/Adobe-Garamond-Pro-Semibold_2011.ttf";
    public static final String PRO_2012_FONT = "fonts/Adobe-Garamond-Pro_2012.ttf";
    public static final String REGULAR = "fonts/Adode Garamond Regular.ttf";
    static int[][] states = new int[][]{
            new int[]{android.R.attr.state_enabled}, // enabled
            new int[]{-android.R.attr.state_enabled}, // disabled
            new int[]{-android.R.attr.state_checked}, // unchecked
            new int[]{android.R.attr.state_pressed}  // pressed
    };
    static int[] colors = new int[]{
            Color.WHITE,
            Color.WHITE,
            Color.WHITE,
            Color.BLUE
    };
    public static final ColorStateList myList = new ColorStateList(states, colors);

    public static final void setCustomFont(Context context, TextView txtView, String fontType) {
//        Typeface custom_regular = Typeface.createFromAsset(context.getAssets(), REGULAR);
//        txtView.setTypeface(custom_regular);

        Typeface custom_font = Typeface.createFromAsset(context.getAssets(), BOLD_ITALIC_FONT);
        txtView.setTypeface(custom_font);

        Typeface custom_fontbold = Typeface.createFromAsset(context.getAssets(), BOLD_FONT);
        txtView.setTypeface(custom_fontbold);

        Typeface custom_font_italic2009 = Typeface.createFromAsset(context.getAssets(), ITALIC_2009_FONT);
        txtView.setTypeface(custom_font_italic2009);

        Typeface custom_font_italic_2010 = Typeface.createFromAsset(context.getAssets(), SEMIBOLD_ITALIC_2010_FONT);
        txtView.setTypeface(custom_font_italic_2010);

        Typeface custom_fontitalic_bold = Typeface.createFromAsset(context.getAssets(), BOLD_ITALIC_FONT);
        txtView.setTypeface(custom_fontitalic_bold);

        Typeface custom_font_semibold_2011 = Typeface.createFromAsset(context.getAssets(), SEMIBOLD_2011_FONT);
        txtView.setTypeface(custom_font_semibold_2011);

    }

    // ---------------------------------------------------ListView ScrolView---------------------------------------------------
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        Adapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, AbsoluteLayout.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    // ---------------------------------------------------internet  connection alert dialog---------------------------------------------------

    public static void showAlertDialog(final Context context, int title, String message, Boolean status) {


        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);


        final Context mContext = context;

        // Setting Dialog Title
        alertDialog.setTitle(title);
        alertDialog.setCancelable(false);

        // Setting Dialog Message
        alertDialog.setMessage(message);


        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                final Intent intent = new Intent(Intent.ACTION_MAIN, null);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                final ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.wifi.WifiSettings");
                intent.setComponent(cn);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                mContext.startActivity(intent);

            }
        });


        alertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event

                //dialog.cancel();
/*
                Intent intent = new Intent(context, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                context.startActivity(intent);*/

            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
}