package com.smcc.application.UserLogins;

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


import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smcc.application.Adapters.SlideimageAdapter;
import com.smcc.application.Fragments.Aboutus;
import com.smcc.application.Fragments.Acadamics;
import com.smcc.application.Fragments.Facilities;
import com.smcc.application.Fragments.Feedback;
import com.smcc.application.Fragments.Gallery;
import com.smcc.application.Fragments.Home;
import com.smcc.application.Fragments.Placements;
import com.smcc.application.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class WelcomeGuest extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
   ViewPager cviewPager;
    Fragment fragment = null;
    FragmentManager fragmentManager = getSupportFragmentManager();
    android.support.v4.app.FragmentTransaction Ftransaction;
    Runnable Update;
    Handler handler;
    Aboutus af;
    Home hf;
    Acadamics facadamics;
    Facilities ffacilities;
    Feedback ffeedback;
    Gallery fgallery;
    Placements fplacements;
    private static int currentPage = 0;
    Integer[] COLLEGE= {R.drawable.college,R.drawable.auditorium,R.drawable.lib,R.drawable.library};
    TextView cscrollText;
    ArrayList<Integer> COLLEGEArray = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_guest);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView tv=(TextView)findViewById(R.id.tv1);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        fragment = new Home();
        hf = (Home) fragment;
        fragmentManager = getSupportFragmentManager();
        LinearLayout ll = (LinearLayout)findViewById(R.id.mainll);
        ll.removeAllViews();
        fragmentManager.beginTransaction().replace(R.id.mainll, fragment).commit();
        Ftransaction=fragmentManager.beginTransaction();
        Ftransaction.show(hf);
        Ftransaction.commit();

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
            fragment = new Home();
            hf = (Home) fragment;
            fragmentManager = getSupportFragmentManager();
            LinearLayout ll = (LinearLayout)findViewById(R.id.mainll);
            ll.removeAllViews();
            fragmentManager.beginTransaction().replace(R.id.mainll, fragment).commit();
            Ftransaction=fragmentManager.beginTransaction();
            Ftransaction.show(hf);
            Ftransaction.commit();


            // Handle the camera action
        } else if (id == R.id.nav_aboutus) {
            fragment = new Aboutus();
            af = (Aboutus) fragment;
            fragmentManager = getSupportFragmentManager();
            LinearLayout ll = (LinearLayout) findViewById(R.id.mainll);
            ll.removeAllViews();
            fragmentManager.beginTransaction().replace(R.id.mainll, fragment).commit();
            Ftransaction=fragmentManager.beginTransaction();
            Ftransaction.show(af);
           // Ftransaction.hide(hf);
           Ftransaction.commit();

    }
        else if (id == R.id.nav_facilities) {
            fragment = new Facilities();
            ffacilities = (Facilities) fragment;
            fragmentManager = getSupportFragmentManager();
            LinearLayout ll = (LinearLayout) findViewById(R.id.mainll);
            ll.removeAllViews();
            fragmentManager.beginTransaction().replace(R.id.mainll, fragment).commit();
            Ftransaction=fragmentManager.beginTransaction();
            Ftransaction.show(ffacilities);
//            Ftransaction.hide(af);
//            Ftransaction.hide(hf);
//            Ftransaction.hide(ffeedback);
//            Ftransaction.hide(fgallery);
//            Ftransaction.hide(fplacements);
//            Ftransaction.hide(facadamics);

            Ftransaction.commit();


        } else if (id == R.id.nav_acadamics) {
            fragment = new Acadamics();
            facadamics = (Acadamics) fragment;
            fragmentManager = getSupportFragmentManager();
            LinearLayout ll = (LinearLayout) findViewById(R.id.mainll);
            ll.removeAllViews();
            fragmentManager.beginTransaction().replace(R.id.mainll, fragment).commit();
            Ftransaction=fragmentManager.beginTransaction();
            Ftransaction.show(facadamics);
//            Ftransaction.hide(hf);
//            Ftransaction.hide(af);
//            Ftransaction.hide(ffacilities);
//            Ftransaction.hide(ffeedback);
//            Ftransaction.hide(fgallery);
//            Ftransaction.hide(fplacements);

            Ftransaction.commit();

        } else if (id == R.id.nav_placements) {
            fragment = new Placements();
            fplacements = (Placements) fragment;
            fragmentManager = getSupportFragmentManager();
            LinearLayout ll = (LinearLayout) findViewById(R.id.mainll);
            ll.removeAllViews();
            fragmentManager.beginTransaction().replace(R.id.mainll, fragment).commit();
            Ftransaction=fragmentManager.beginTransaction();
            Ftransaction.show(fplacements);
//            Ftransaction.hide(hf);
//            Ftransaction.hide(af);
//            Ftransaction.hide(facadamics);
//            Ftransaction.hide(ffeedback);
//            Ftransaction.hide(ffacilities);
//            Ftransaction.hide(fgallery);
            Ftransaction.commit();

        } else if (id == R.id.nav_feedback) {
            fragment = new Feedback();
            ffeedback = (Feedback) fragment;
            fragmentManager = getSupportFragmentManager();
            LinearLayout ll = (LinearLayout) findViewById(R.id.mainll);
            ll.removeAllViews();
            fragmentManager.beginTransaction().replace(R.id.mainll, fragment).commit();
            Ftransaction=fragmentManager.beginTransaction();
            Ftransaction.show(ffeedback);
//            Ftransaction.hide(hf);
//            Ftransaction.hide(af);
//            Ftransaction.hide(ffacilities);
//            Ftransaction.hide(fplacements);
//            Ftransaction.hide(fgallery);
//            Ftransaction.hide(facadamics);
            Ftransaction.commit();

        }else if(id==R.id.nav_gallery){
            fragment = new Gallery();
            fgallery = (Gallery) fragment;
            fragmentManager = getSupportFragmentManager();
            LinearLayout ll = (LinearLayout) findViewById(R.id.mainll);
            ll.removeAllViews();
            fragmentManager.beginTransaction().replace(R.id.mainll, fragment).commit();
            Ftransaction=fragmentManager.beginTransaction();
            Ftransaction.show(fgallery);
//            Ftransaction.hide(af);
//            Ftransaction.hide(facadamics);
//            Ftransaction.hide(ffacilities);
//            Ftransaction.hide(ffeedback);
//            Ftransaction.hide(fplacements);
//            Ftransaction.hide(hf);

            Ftransaction.commit();

        }


//        fragment = new DashboardExpand();
//        Ds=(DashboardExpand) fragment;
//        Ds.Ctask=TrackSer.Ctask;
//        Frmap=fragmentManager.beginTransaction().add(R.id.maprel_layout, fragment);
//        Frmap.commit();
//        Frmap.hide(Ds);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
