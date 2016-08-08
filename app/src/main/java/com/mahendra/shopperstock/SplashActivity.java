package com.mahendra.shopperstock;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;


import com.mahendra.shopperstock.Commons.Utility;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MK on 18-06-2016.
 */
public class SplashActivity extends AppCompatActivity{

    Activity _activity = SplashActivity.this;
    public static String marketAssociatedEmailId = "";
    SharedPreferences sharedPreferences;
    private String TAG = "ShopperSTock";
    private boolean isIntroShow;

    JSONParser jsonParser = new JSONParser();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        initialiseView();
        getDeviceLoginEmailId();

        new UserEntry().execute();
         moveAfterAnim();
    }

    /**
     * it is used to initialise views
     */
    private void initialiseView() {
       /* TextView poweredBy = (TextView) findViewById(R.id.powerdBy);
        String text = "<font color=#ffffff>Powered by</font> <font color=#ffffff>Pamusa</font>";
        poweredBy.setText(Html.fromHtml(text));*/

    }//end initialiseView()-------------------------------


    // move next page after 3 sec
    private void moveAfterAnim() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                if (sharedPreferences.getBoolean("LoginStatus", false)) {

                   /* Intent intent = new Intent(SplashActivity.this, HomeActivtiy.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();*/

                } else {


                    if (Utility.getUtilityInstance().isNetworkAvailable(_activity)) {
                        //    if (Utility.getPreferencesIntValue(_activity, "isLoggedIn") == 0) {
                        //send to Intro Page

                        isIntroShow = sharedPreferences.getBoolean("isintroshow", false);

                        if (!isIntroShow) {
                        Intent intent = new Intent(SplashActivity.this, IntroActivity.class);
                        intent.putExtra("Where", "Splash");
                        startActivity(intent);
                        finish();
                        Utility.doAnim(_activity, "right");
                    }
                    else {
                        Intent newIntent = new Intent(getBaseContext(),MainActivity.class);
                        startActivity(newIntent);
                        finish();
                        Utility.doAnim(SplashActivity.this, "right");
                    }
                   /* } else {
                        Utility.doStartActivityWithFinish(SplashActivity.this, DashboardActivity.class, "right");
                    }*/
                    } else {
                       // Utility.getUtilityInstance().showMessage(getResources().getString(R.string.check_network), _activity);
                    }
                }


            }
        }, 3000);
    }// end moveAfterAnim()......................


    /*
    * User Email Entery Executeion Class Start
    * */
    class UserEntry extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... params) {


            try {
            // Building Parameters
            List<NameValuePair> l = new ArrayList<NameValuePair>();
            l.add(new BasicNameValuePair("email", marketAssociatedEmailId));

            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(URLFactory.userentry(),
                    "POST", l);

            // check log cat fro response
            //Log.d("Create Response", json.toString());

            // check for success tag


            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

/*
get Googlr Account Function
* */

    void getDeviceLoginEmailId() {
        Account[] arrayofAccount = AccountManager.get(SplashActivity.this)
                .getAccountsByType("com.google");
        if (arrayofAccount.length > 0) {
            this.marketAssociatedEmailId = arrayofAccount[0].name;
            Log.d(TAG, marketAssociatedEmailId);
            return;
        }
        Log.d(TAG, "Email Id is NUll");
    }


}//end main class-------------------------------