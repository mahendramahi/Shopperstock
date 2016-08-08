/*
package com.mahendra.shopperstock.fragment;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import com.pamusa.Commons.ConnectionDetector;
import com.pamusa.Commons.SessionManager;
import com.pamusa.Commons.Utils;
import com.pamusa.R;
import com.pamusa.adapter.CirclePageIndicator;
import com.pamusa.adapter.ListCustomAdapter;
import com.pamusa.adapter.SlidingImage_Adapter;
import com.pamusa.async.SecretKeyResponseReceiver;
import com.pamusa.service.ServiceHandler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;

import com.pushbots.push.Pushbots;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private static final int RESULT_SETTINGS = 1;
    public static int[] IMAGES = {R.drawable.silder_one, R.drawable.slide_two, R.drawable.slide_three};
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    public ArrayList<String> imgSrcArr = new ArrayList<String>();
    public ArrayList<String> NewsIdArr = new ArrayList<String>();
    public ArrayList<String> ContentArr = new ArrayList<String>();
    public ArrayList<String> aDetailList = new ArrayList<String>();
    String[] imgSrcString;
    String[] NewsIdString;
    String[] ContentString;
    String temp;
    ArrayList prgmName;
    String devtoken;
    ConnectionDetector cd;
    Boolean isInternetPresent = false;
    String sInternetMess;
    SessionManager session;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Boolean isExit = false;
    String GCMKEY;
//    public static int[] IMAGES = {R.drawable.slide_truck, R.drawable.slide_truck, R.drawable.slide_truck, R.drawable.slide_truck, R.drawable.slide_truck};
    private Toolbar mToolbar;

    ///////////////Push Variables///////////
    private FragmentDrawer drawerFragment;
    private TextView mToolbarTitle;
    private TextView txt_signin;
    private TextView txt_signup;
    private ListView lis_news;
    private TextView t1;
    private ViewPager mViewPager;
    private ProgressDialog progress;
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private SecretKeyResponseReceiver secretreceiver;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new SessionManager(getApplicationContext());
        cd = new ConnectionDetector(LoginActivity.this);
        isInternetPresent = cd.isConnectingToInternet();
        sInternetMess = getResources().getString(R.string.internet_message);


        Intent i = getIntent();
        // isExit= i.getExtras().getBoolean("EXIT");
        isExit = getIntent().getBooleanExtra("EXIT", false);
        if (isExit) {

            finish();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
            getParent().finish();

        }
        Pushbots.sharedInstance().init(this);
//
        GCMKEY = Pushbots.sharedInstance().regID();
        // Toast.makeText(LoginActivity.this,Str,Toast.LENGTH_SHORT).show();
        intializeUi();
        viewPagerDesc();
        if (isInternetPresent) {

            new NewsAsyb().execute();
        } else {

            showAlertDialog();
        }


        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbarTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
        mToolbar.setTitle("");
        // mToolbar.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.transparent));
        setSupportActionBar(mToolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);

        AppBarLayout.LayoutParams p = (AppBarLayout.LayoutParams) collapsingToolbarLayout.getLayoutParams();
        p.setScrollFlags(0);
        collapsingToolbarLayout.setLayoutParams(p);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


        navigationView.getMenu().clear(); //clear old inflated items.
        navigationView.inflateMenu(R.menu.activity_main_one); //inflate new items.
        navigationView.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.black));
        navigationView.setItemTextColor(Utils.myList);
        navigationView.setNavigationItemSelectedListener(LoginActivity.this);

    }

    public void showAlertDialog() {

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);

        // Setting Dialog Title
        alertDialog.setTitle(R.string.internet_title);

        // Setting Dialog Message
        alertDialog.setMessage(sInternetMess);


        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                Intent resultIntent = new Intent(Intent.ACTION_MAIN, null);
                resultIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.wifi.WifiSettings");
                resultIntent.setComponent(cn);
                startActivityForResult(resultIntent, RESULT_SETTINGS);

            }
        });

        alertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event
                LoginActivity.this.finish();

            }
        });

        // Showing Alert Message
        alertDialog.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_SETTINGS:
                LoginActivity.this.finish();
                break;

        }

    }

    private void viewPagerDesc() {
        for (int i = 0; i < IMAGES.length; i++)

            ImagesArray.add(IMAGES[i]);


        mViewPager.setAdapter(new SlidingImage_Adapter(LoginActivity.this, ImagesArray));


        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mViewPager);
        final float density = getResources().getDisplayMetrics().density;

        indicator.setRadius(6 * density);


        NUM_PAGES = IMAGES.length;


        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mViewPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }

    private void intializeUi() {

        mViewPager = (ViewPager) findViewById(R.id.pager);
        txt_signin = (TextView) findViewById(R.id.btn_signin);
        txt_signup = (TextView) findViewById(R.id.btn_signup);
        lis_news = (ListView) findViewById(R.id.list_news);
        //t1=(TextView) findViewById(R.id.txt_news);

        //Defining the click event
        txt_signin.setOnClickListener(this);
        txt_signup.setOnClickListener(this);
        Utils.setListViewHeightBasedOnChildren(lis_news);
        Utils.setCustomFont(this, txt_signin, Utils.BOLD_FONT);
        Utils.setCustomFont(this, txt_signup, Utils.BOLD_FONT);
//        Utils.setCustomFont(this,t1,Utils.REGULAR);


    }

    private void dismissProgressDialog() {
        if (progress != null && progress.isShowing()) {
            progress.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        dismissProgressDialog();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_signin:

              Intent intent = new Intent(LoginActivity.this, SigninActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);


                break;
            case R.id.btn_signup:
                Intent in = new Intent(LoginActivity.this, RegistrationActivity.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
               // in.putExtra("KEY", GCMKEY);
                startActivity(in);
                break;

        }
    }

    @Override


    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            // Handle the camera action
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_new_load) {

            Intent intent = new Intent(LoginActivity.this, NewLoadRequestActivity.class);
            intent.putExtra("sTrackID", "00");
            intent.putExtra("sRedirectID", true);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_favorite) {
            Intent intent = new Intent(LoginActivity.this, FavouriteActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_track_load) {
            Intent intent = new Intent(LoginActivity.this, TrackLoadActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_load_history) {
            Intent intent = new Intent(LoginActivity.this, LoadHistoryActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_my_trucks) {
            Intent intent = new Intent(LoginActivity.this, MyTruckActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_my_driver) {
            Intent intent = new Intent(LoginActivity.this, MyDriverActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_find_load) {
            Intent intent = new Intent(LoginActivity.this, FindLoadActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_special_offer) {
            Intent intent20 = new Intent(LoginActivity.this, SpecialOffers.class);
            startActivity(intent20);
            //Toast.makeText(getApplicationContext(), "No Special Offers as of now", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_news) {
            Intent intent = new Intent(LoginActivity.this, NewsEvents.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_aboutus) {
            Intent intent = new Intent(LoginActivity.this, AboutUsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_contactus) {
            Intent intent = new Intent(LoginActivity.this, ContactUs.class);
            startActivity(intent);
        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(LoginActivity.this, SigninActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_login) {
            Intent intent = new Intent(LoginActivity.this, SigninActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }





    private class NewsAsyb extends AsyncTask<String, Void, Void> {
        String responseString = null;
        String url = Utils.BASE_URL + Utils.NEWS_LIST_URL;

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(LoginActivity.this);
            progress.setMessage("Please Wait...");
            progress.setCancelable(false);
            progress.setIndeterminate(true);
            progress.setIndeterminateDrawable(getApplicationContext().getResources().getDrawable(R.drawable.custom_progress_dialog_animation));
            progress.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... params) {
            ServiceHandler sh = new ServiceHandler();
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
            Log.d("Response: ", "> " + jsonStr);
            String sNewsId, sHeader, sContent, imgSrc, sDate, sStatus;

            if (jsonStr != null) {
                try {
                    JSONArray jsonArray = new JSONArray(jsonStr);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject c = jsonArray.getJSONObject(i);
                        sNewsId = c.getString("newsId");
                        sHeader = c.getString("heading");
                        sContent = c.getString("content");
                        imgSrc = c.getString("imageSrc");
                        sDate = c.getString("date");
                        sStatus = c.getString("status");
                        imgSrcArr.add(imgSrc);
                        NewsIdArr.add(sNewsId);
                        ContentArr.add(sHeader);

                    }

                    imgSrcString = new String[imgSrcArr.size()];
                    imgSrcString = imgSrcArr.toArray(imgSrcString);

                    NewsIdString = new String[NewsIdArr.size()];
                    NewsIdString = NewsIdArr.toArray(NewsIdString);

                    ContentString = new String[ContentArr.size()];
                    ContentString = ContentArr.toArray(ContentString);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (LoginActivity.this.isFinishing()) { // or call isFinishing() if min sdk version < 17
                return;
            }
            dismissProgressDialog();
            lis_news.setAdapter(new ListCustomAdapter(LoginActivity.this, ContentString, imgSrcString, NewsIdString));
            super.onPostExecute(aVoid);

            //progress.dismiss();
        }
    }
}
*/
