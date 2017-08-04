package com.smcc.application.UserLogins;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.widget.TextView;

import com.smcc.application.Adapters.SlideimageAdapter;
import com.smcc.application.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class WelcomeFaculty extends Activity {
    ViewPager fviewPager;
    private static int currentPage = 0;
    //    private static int numPages = 0;
    // int[] img;
    Integer[] COLLEGE= {R.drawable.college,R.drawable.auditorium,R.drawable.lib,R.drawable.library};
    //SlideimageAdapter adapter;
    TextView fscrollText;
    ArrayList<Integer> COLLEGEArray = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_faculty);

        fscrollText = (TextView)findViewById(R.id.fscrollText);
        //String datascrolltext= (String)scrollText.getText();
        fscrollText.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        fscrollText.setSingleLine(true);
        fscrollText.setSelected(true);
        init();
    }

    private void init() {
        for(int i=0;i<COLLEGE.length;i++)
            COLLEGEArray.add(COLLEGE[i]);

        fviewPager = (ViewPager) findViewById(R.id.fviewpager);
        fviewPager.setAdapter(new SlideimageAdapter(WelcomeFaculty.this,COLLEGEArray));
        CircleIndicator findicator = (CircleIndicator) findViewById(R.id.findicator);
        findicator.setViewPager(fviewPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == COLLEGE.length) {
                    currentPage = 0;
                }
                fviewPager.setCurrentItem(currentPage++, true);
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
