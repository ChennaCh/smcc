package com.smcc.application.UserLogins;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.support.design.widget.NavigationView;


import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smcc.application.Activity.AdminWelcome;
import com.smcc.application.Adapters.SlideimageAdapter;
import com.smcc.application.Bean.GetFacultyBean;
import com.smcc.application.Fragments.Aboutus;
import com.smcc.application.Fragments.Acadamics;
import com.smcc.application.Fragments.Facilities;
import com.smcc.application.Fragments.Feedback;
import com.smcc.application.Fragments.Gallery;
import com.smcc.application.Fragments.Home;
import com.smcc.application.Fragments.Placements;
import com.smcc.application.HttpHandler;
import com.smcc.application.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class WelcomeGuest extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

   //ViewPager cviewPager;
    public static int navItemIndex = 0;
    private static final String TAG_HOME = "home";
    private static final String TAG_ABOUTUS = "aboutus";
    private static final String TAG_ACADAMICS = "acadamics";
    private static final String TAG_FACILITIES = "facilities";
    private static final String TAG_FEEDBACK = "feedback";
    private static final String TAG_GALLERY = "gallery";
    private static final String TAG_PLACEMENTS = "placements";

    public static String CURRENT_TAG = TAG_HOME;



    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;
    private NavigationView navigationView;
    Fragment fragment = null;
    FragmentManager fragmentManager = getSupportFragmentManager();
    android.support.v4.app.FragmentTransaction Ftransaction;
    Aboutus af;
    Home hf;
    DrawerLayout drawer;
    Acadamics facadamics;
    Facilities ffacilities;
    Feedback ffeedback;
    Gallery fgallery;
    Placements fplacements;
   // private static int currentPage = 0;
    //Integer[] COLLEGE= {R.drawable.college,R.drawable.auditorium,R.drawable.lib,R.drawable.library};
    //TextView cscrollText;
    //ArrayList<Integer> COLLEGEArray = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_guest);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mHandler = new Handler();
        TextView tv=(TextView)findViewById(R.id.tv1);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
// load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }
      // initializing navigation menu
      //  setUpNavigationView();

    }
    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            // show or hide the fab button
           // toggleFab();
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                //fragment = new Home();
                fragment=getHomeFragment();
                //hf = (Home) fragment;
                fragmentManager = getSupportFragmentManager();
                LinearLayout ll = (LinearLayout)findViewById(R.id.mainll);
                ll.removeAllViews();
                fragmentManager.beginTransaction().replace(R.id.mainll, fragment).commit();
                Ftransaction=fragmentManager.beginTransaction();
               // Ftransaction.show(hf);
                Ftransaction.commit();

            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // show or hide the fab button
        //toggleFab();

        //Closing drawer on item click
//        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }
        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }
        super.onBackPressed();
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome_guest, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            fragment = new Home();
            hf = (Home) fragment;
            fragmentManager = getSupportFragmentManager();
            LinearLayout ll = (LinearLayout)findViewById(R.id.mainll);
            ll.removeAllViews();
            fragmentManager.beginTransaction().replace(R.id.mainll, fragment).commit();
            Ftransaction=fragmentManager.beginTransaction();
            Ftransaction.show(hf);
            Ftransaction.commit();

        } else if (id == R.id.nav_aboutus) {
            navItemIndex = 1;
            CURRENT_TAG = TAG_ABOUTUS;
            fragment = new Aboutus();
            af = (Aboutus) fragment;
            fragmentManager = getSupportFragmentManager();
            LinearLayout ll = (LinearLayout) findViewById(R.id.mainll);
            ll.removeAllViews();
            fragmentManager.beginTransaction().replace(R.id.mainll, fragment).commit();
            Ftransaction=fragmentManager.beginTransaction();
            Ftransaction.show(af);
           Ftransaction.commit();

    }
        else if (id == R.id.nav_facilities) {
            navItemIndex = 2;
            CURRENT_TAG = TAG_FACILITIES;
            fragment = new Facilities();
            ffacilities = (Facilities) fragment;
            fragmentManager = getSupportFragmentManager();
            LinearLayout ll = (LinearLayout) findViewById(R.id.mainll);
            ll.removeAllViews();
            fragmentManager.beginTransaction().replace(R.id.mainll, fragment).commit();
            Ftransaction=fragmentManager.beginTransaction();
            Ftransaction.show(ffacilities);
            Ftransaction.commit();


        } else if (id == R.id.nav_acadamics) {
            navItemIndex = 3;
            CURRENT_TAG = TAG_ACADAMICS;
            fragment = new Acadamics();
            facadamics = (Acadamics) fragment;
            fragmentManager = getSupportFragmentManager();
            LinearLayout ll = (LinearLayout) findViewById(R.id.mainll);
            ll.removeAllViews();
            fragmentManager.beginTransaction().replace(R.id.mainll, fragment).commit();
            Ftransaction=fragmentManager.beginTransaction();
            Ftransaction.show(facadamics);
            Ftransaction.commit();

        } else if (id == R.id.nav_placements) {
            navItemIndex = 4;
            CURRENT_TAG = TAG_PLACEMENTS;
            fragment = new Placements();
            fplacements = (Placements) fragment;
            fragmentManager = getSupportFragmentManager();
            LinearLayout ll = (LinearLayout) findViewById(R.id.mainll);
            ll.removeAllViews();
            fragmentManager.beginTransaction().replace(R.id.mainll, fragment).commit();
            Ftransaction=fragmentManager.beginTransaction();
            Ftransaction.show(fplacements);
            Ftransaction.commit();

        } else if (id == R.id.nav_feedback) {
            navItemIndex = 5;
            CURRENT_TAG = TAG_FEEDBACK;
            fragment = new Feedback();
            ffeedback = (Feedback) fragment;
            fragmentManager = getSupportFragmentManager();
            LinearLayout ll = (LinearLayout) findViewById(R.id.mainll);
            ll.removeAllViews();
            fragmentManager.beginTransaction().replace(R.id.mainll, fragment).commit();
            Ftransaction=fragmentManager.beginTransaction();
            Ftransaction.show(ffeedback);
            Ftransaction.commit();

        }else if(id==R.id.nav_gallery){
            navItemIndex = 6;
            CURRENT_TAG = TAG_GALLERY;
            fragment = new Gallery();
            fgallery = (Gallery) fragment;
            fragmentManager = getSupportFragmentManager();
            LinearLayout ll = (LinearLayout) findViewById(R.id.mainll);
            ll.removeAllViews();
            fragmentManager.beginTransaction().replace(R.id.mainll, fragment).commit();
            Ftransaction=fragmentManager.beginTransaction();
            Ftransaction.show(fgallery);
            Ftransaction.commit();

        }


//Checking if the item is in checked state or not, if not make it in checked state
        if (item.isChecked()) {
            item.setChecked(false);
        } else {
            item.setChecked(true);
        }
        item.setChecked(true);
        loadHomeFragment();
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // home
                fragment = new Home();
                hf = (Home) fragment;
                return hf;
            case 1:
                fragment = new Aboutus();
                af = (Aboutus) fragment;
                return af;
            case 2:
                fragment = new Acadamics();
                facadamics = (Acadamics) fragment;
                return facadamics;
            case 3:
                fragment = new Facilities();
                ffacilities = (Facilities) fragment;
                return ffacilities;

            case 4:
                fragment = new Feedback();
                ffeedback = (Feedback) fragment;
                return ffeedback;
            case 5:
                fragment = new Gallery();
                fgallery = (Gallery) fragment;
                return fgallery;
            case 6:
                fragment = new Placements();
                fplacements = (Placements) fragment;
                return fplacements;
            default:
                return new Home();
        }
    }


}
