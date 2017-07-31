package com.smcc.application.Fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smcc.application.Activity.Vision;
import com.smcc.application.Adapters.SlideimageAdapter;
import com.smcc.application.R;
import com.smcc.application.UserLogins.WelcomeGuest;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    ViewPager gviewPager;
    Fragment fragment = null;
    Runnable Update;
    Handler handler;
    Aboutus af;
    Vision vision;
    Button aboutus;
    //FragmentManager fragmentManager = getSupportFragmentManager();
    android.support.v4.app.FragmentTransaction Ftransaction;
    private static int currentPage = 0;

    Integer[] COLLEGE= {R.drawable.college,R.drawable.auditorium,R.drawable.lib,R.drawable.library};
    TextView gscrollText;
    ArrayList<Integer> COLLEGEArray = new ArrayList<Integer>();
    View view;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_home, container, false);
        gscrollText = (TextView)view.findViewById(R.id.hscrollText);
        aboutus=(Button)view.findViewById(R.id.aboutus);

        //String datascrolltext= (String)gscrollText.getText();
        gscrollText.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        gscrollText.setSingleLine(true);
        gscrollText.setSelected(true);
        init();
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                getFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.innerhomell, new Aboutus())
//                        .commit();
                //LinearLayout ll=(LinearLayout)view.findViewById(R.id.mainll);
                FragmentTransaction t = getFragmentManager().beginTransaction();
                Fragment mFrag = new Aboutus();
                t.replace(R.id.mainll, mFrag);
                t.commit();


            }
        });

        return view;

    }

    private void init() {

        for(int i=0;i<COLLEGE.length;i++)
            COLLEGEArray.add(COLLEGE[i]);
        gviewPager = (ViewPager)view.findViewById(R.id.hviewpager);
        gviewPager.setAdapter(new SlideimageAdapter(getActivity(),COLLEGEArray));
        CircleIndicator gindicator = (CircleIndicator)view.findViewById(R.id.hindicator);
        gindicator.setViewPager(gviewPager);

        // Auto start of viewpager
        handler = new Handler(Looper.getMainLooper());
        Update = new Runnable() {
            public void run() {
                if (currentPage == COLLEGE.length) {
                    currentPage = 0;
                }
                gviewPager.setCurrentItem(currentPage++, true);
            }
        };
        //Update.run();
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
        handler.post(Update);
    }
}
