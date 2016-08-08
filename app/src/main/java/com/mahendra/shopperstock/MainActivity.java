package com.mahendra.shopperstock;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.mahendra.shopperstock.fragment.FeMaleFragment;
import com.mahendra.shopperstock.fragment.HomeFragment;
import com.mahendra.shopperstock.fragment.KidsFragment;
import com.mahendra.shopperstock.fragment.MaleFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView tv_mailid;
    public static String marketAssociatedEmailId = "";
    private String TAG = "ShopperSTock";
    public int fragment_type = 0;
    public int lastf_type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getDeviceLoginEmailId();
        initUI();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if(lastf_type != fragment_type) {
                    checkforFragmenttype(fragment_type);
                }
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                View container = findViewById(R.id.mainll);
                container.setTranslationX(slideOffset * drawerView.getWidth());
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }


        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        TextView iv = (TextView) headerView.findViewById(R.id.textView_mailid);
        iv.setText(marketAssociatedEmailId);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.getMenu().getItem(0).setChecked(true);

        HomeFragment homeFragment = new HomeFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.contenr, homeFragment).commit();

    }

    private void checkforFragmenttype(int fragment_type) {

        switch (fragment_type){

       case 0:
           lastf_type = 0;
           HomeFragment homeFragment = new HomeFragment();
           getSupportFragmentManager().beginTransaction().replace(R.id.contenr, homeFragment).commit();
           break;

        case 1:
            lastf_type = 1;
            MaleFragment maleFragment = new MaleFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.contenr, maleFragment).commit();
            break;

        case 2:
            lastf_type = 2;
            FeMaleFragment femaleFragment = new FeMaleFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.contenr, femaleFragment).commit();
            break;

        case 3:
            lastf_type = 3;
            KidsFragment kidsFragment = new KidsFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.contenr, kidsFragment).commit();
              break;
        }
    }

    public void initUI(){

        //tv_mailid = (TextView) findViewById(R.id.textView_mailid);
      //  tv_mailid.setText(marketAssociatedEmailId);

    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_mainhome) {


            fragment_type = 0;

            // Handle the camera action

        } else if (id == R.id.nav_male) {
            fragment_type = 1;


        } else if (id == R.id.nav_female) {
            fragment_type = 2;


        } else if (id == R.id.nav_child) {
            fragment_type = 3;


        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    void getDeviceLoginEmailId() {
        Account[] arrayofAccount = AccountManager.get(MainActivity.this)
                .getAccountsByType("com.google");
        if (arrayofAccount.length > 0) {
            this.marketAssociatedEmailId = arrayofAccount[0].name;
            Log.d(TAG, marketAssociatedEmailId);
            return;
        }
        Log.d(TAG, "Email Id is NUll");
    }
}
