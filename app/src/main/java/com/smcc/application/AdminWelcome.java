package com.smcc.application;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.smcc.application.Adapters.SlideimageAdapter;
import com.smcc.application.UserLogins.Admin;
import com.smcc.application.UserLogins.LoginTypes;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class AdminWelcome extends Activity {
   ViewPager viewPager;
    String username;
    private static int currentPage = 0;
    Button logoutbtn;
    Integer[] COLLEGE= {R.drawable.college,R.drawable.auditorium,R.drawable.lib,R.drawable.library};
    //SlideimageAdapter adapter;
    TextView scrollText;
    ArrayList<Integer> COLLEGEArray = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_welcome);
        scrollText = (TextView)findViewById(R.id.adminscrollText);
       // String datascrolltext= (String)scrollText.getText();
        Bundle b = getIntent().getExtras();
        username = b.getString("uname");

        logoutbtn = (Button)findViewById(R.id.admin_logout);

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(AdminWelcome.this)
                        .setIcon(R.drawable.alerticon)
                        .setTitle("Logout")
                        .setMessage("Are you sure you want to LogOut !!")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent adminintent = new Intent(AdminWelcome.this, Admin.class);
                                startActivity(adminintent);
                            }

                        })

                        .setNegativeButton("No", null)
                        .show();
            }
        });

        scrollText.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        scrollText.setSingleLine(true);
        scrollText.setSelected(true);
        init();
    }

    private void init() {
        for(int i=0;i<COLLEGE.length;i++)
            COLLEGEArray.add(COLLEGE[i]);

        viewPager = (ViewPager)findViewById(R.id.adminvpager);
        viewPager.setAdapter(new SlideimageAdapter(AdminWelcome.this,COLLEGEArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.adminindicator);
        indicator.setViewPager(viewPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == COLLEGE.length) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(AdminWelcome.this)
                .setIcon(R.drawable.alerticon)
                .setTitle("Logout")
                .setMessage("Are you sure you want to LogOut !!")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent adminintent = new Intent(AdminWelcome.this, Admin.class);
                        startActivity(adminintent);
                    }

                })

                .setNegativeButton("No", null)
                .show();
    }

}
