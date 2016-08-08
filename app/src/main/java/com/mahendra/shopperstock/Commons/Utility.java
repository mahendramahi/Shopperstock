package com.mahendra.shopperstock.Commons;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.mahendra.shopperstock.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * Contain all unique members
 * Created by GSS-Vishnu Kant
 */
public class Utility {


    public static Utility sUtility = null;
    public static Intent sIntent = new Intent();


    public static boolean isTruckOwner =  false;
    public static boolean isTruckBooking =  false;
    public static String isCompanyName =  "";
    public static String isPhoneNo =  "";
    public static String istockenvalue =  "";
    public static String isOwner =  "";


    private Utility() {

    }

    public static Utility getUtilityInstance() {

        if (sUtility == null) {
            sUtility = new Utility();
        }
        return sUtility;
    }


    /**
     * Back event for entire app
     * finish activity and left animation
     */
    public void backEvent(Activity _act) {
        _act.finish();
        Utility.doAnim(_act, "left");

    }//end backEvent()-----------------


    /**
     * first app user
     */
    public static final int AID_APP = 10000;

    /**
     * offset for uid ranges for each user
     */
    public static final int AID_USER = 100000;

    public static String getForegroundApp() {
        File[] files = new File("/proc").listFiles();
        int lowestOomScore = Integer.MAX_VALUE;
        String foregroundProcess = null;

        for (File file : files) {
            if (!file.isDirectory()) {
                continue;
            }

            int pid;
            try {
                pid = Integer.parseInt(file.getName());
            } catch (NumberFormatException e) {
                continue;
            }

            try {
                String cgroup = read(String.format("/proc/%d/cgroup", pid));

                String[] lines = cgroup.split("\n");

                if (lines.length != 2) {
                    continue;
                }

                String cpuSubsystem = lines[0];
                String cpuaccctSubsystem = lines[1];

                if (!cpuaccctSubsystem.endsWith(Integer.toString(pid))) {
                    // not an application process
                    continue;
                }

                if (cpuSubsystem.endsWith("bg_non_interactive")) {
                    // background policy
                    continue;
                }

                String cmdline = read(String.format("/proc/%d/cmdline", pid));

                if (cmdline.contains("com.android.systemui")) {
                    continue;
                }

                int uid = Integer.parseInt(
                        cpuaccctSubsystem.split(":")[2].split("/")[1].replace("uid_", ""));
                if (uid >= 1000 && uid <= 1038) {
                    // system process
                    continue;
                }

                int appId = uid - AID_APP;
                int userId = 0;
                // loop until we get the correct user id.
                // 100000 is the offset for each user.
                while (appId > AID_USER) {
                    appId -= AID_USER;
                    userId++;
                }

                if (appId < 0) {
                    continue;
                }

                // u{user_id}_a{app_id} is used on API 17+ for multiple user account support.
                // String uidName = String.format("u%d_a%d", userId, appId);

                File oomScoreAdj = new File(String.format("/proc/%d/oom_score_adj", pid));
                if (oomScoreAdj.canRead()) {
                    int oomAdj = Integer.parseInt(read(oomScoreAdj.getAbsolutePath()));
                    if (oomAdj != 0) {
                        continue;
                    }
                }

                int oomscore = Integer.parseInt(read(String.format("/proc/%d/oom_score", pid)));
                if (oomscore < lowestOomScore) {
                    lowestOomScore = oomscore;
                    foregroundProcess = cmdline;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return foregroundProcess;
    }

    private static String read(String path) throws IOException {
        StringBuilder output = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        output.append(reader.readLine());
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            output.append('\n').append(line);
        }
        reader.close();


        return output.toString().trim();
    }


    //show message
    public static void showMessage(String msg, Context con) {
        Toast.makeText(con, "" + msg, Toast.LENGTH_SHORT).show();
    }


    /*********************
     * set shared preferences
     **************************/
    public static void SetPreferences(Context con, String key, String value) {
        // save the data
        SharedPreferences preferences = con.getSharedPreferences("prefs_login", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
/*
*
* Hide Key Board
*
*/
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    /*********************
     * set shared preferences int value
     **************************/
    public static void SetPreferencesIntValue(Context con, String key, int value) {
        // save the data
        SharedPreferences preferences = con.getSharedPreferences("prefs_login", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /******************
     * get shared preferences
     *******************/
    public static String getPreferences(Context con, String key) {
        SharedPreferences sharedPreferences = con.getSharedPreferences(
                "prefs_login", 0);
        String value = sharedPreferences.getString(key, "0");
        return value;

    }


    /******************
     * get shared preferences int value
     *******************/
    public static int getPreferencesIntValue(Context con, String key) {
        SharedPreferences sharedPreferences = con.getSharedPreferences(
                "prefs_login", 0);
        int value = sharedPreferences.getInt(key, 0);
        return value;

    }


    public static String getStringExtraProcess(String key, Activity context) {
        try {
            return context.getIntent().getStringExtra(key);
        } catch (Exception e) {
            return "";
        }
    }


    public static String getStringExtraBundle(String key, Activity context) {
        Bundle getBundle = null;
        try {
            getBundle = context.getIntent().getExtras();
            return getBundle.getString(key);
        } catch (Exception e) {
            return "";
        }
    }


    /*******
     * do animated activity
     ********/
    public static void doAnim(Activity act, String flag) {
        if (flag.equals("left")) {
            act.overridePendingTransition(R.anim.slide_in_left,
                    R.anim.slide_out_right);
        } else if (flag.equals("right")) {
            act.overridePendingTransition(R.anim.slide_in_right,
                    R.anim.slide_out_left);
        } else if (flag.equals("no")) {
            act.overridePendingTransition(0, 0);
        }
    }


    /**
     * start activity without finish, without any value
     */
    public static void doStartActivityWithoutFinish(Activity act,
                                                    Class cls, String anim_left_right_no) {
        act.startActivity(sIntent.setClass(act, cls));
        doAnim(act, anim_left_right_no);
    }

    /**
     * start activity with finish, without any value
     */
    public static void doStartActivityWithFinish(Activity act,
                                                 Class cls, String anim_left_right_no) {
        act.startActivity(sIntent.setClass(act, cls));
        act.finish();
        doAnim(act, anim_left_right_no);
    }


    /**
     * start activity without finish, with Bundle
     */
    public static void doStartActivityWithoutFinishBundle(Activity act,
                                                          Class cls, Bundle bundle, String anim_left_right_no) {
        sIntent.putExtras(bundle);
        act.startActivity(sIntent.setClass(act, cls));
        doAnim(act, anim_left_right_no);
    }

    public static boolean isDeviceSupportCamera(Context _context) {
        if (_context.getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    // Used to retrive access key
    public static String getAccessKey() {
        return "r9ga6iv7ri1nc3ah4ra8u0h0an";
    }

    // Used to check network connection is available or not
    public static boolean isNetworkAvailable(Context _context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * show alert, title, message, single ok button
     */
    public static void showAlertDialog(final Activity _activity, String title, String msg, boolean isFinish) {
        AlertDialog.Builder alert = new AlertDialog.Builder(_activity);
        alert.setMessage("" + msg);
        if (isFinish) {
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    _activity.finish();
                    Utility.doAnim(_activity, "left");
                }
            });
        } else {
            alert.setPositiveButton("OK", null);
        }

        alert.show();
    }

    /**
     * show alert, title, message, single ok button
     */
    public static void showAlertDialogWithNegativeButton(final Activity _activity, String title, String msg, boolean isFinish) {
        AlertDialog.Builder alert = new AlertDialog.Builder(_activity);
        alert.setMessage("" + msg);
        if (isFinish) {
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    _activity.finish();
                    Utility.doAnim(_activity, "left");
                }
            });
        } else {
            alert.setPositiveButton("OK", null);
        }

        alert.show();
    }

}
