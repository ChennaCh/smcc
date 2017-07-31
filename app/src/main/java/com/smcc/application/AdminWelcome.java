package com.smcc.application;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.widget.TextView;

import com.smcc.application.Adapters.SlideimageAdapter;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class AdminWelcome extends Activity {
   ViewPager viewPager;
    private static int currentPage = 0;
//    private static int numPages = 0;
   // int[] img;
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

}
