package com.smcc.application.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.smcc.application.Activity.GuestFeedback;
import com.smcc.application.AboutUsActivities.Vision;
import com.smcc.application.Adapters.SlideimageAdapter;
import com.smcc.application.R;

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
    Button aboutus,acadamics,facilities,feedback,gallery,placements;
    //FragmentManager fragmentManager = getSupportFragmentManager();
    android.support.v4.app.FragmentTransaction Ftransaction;
    private static int currentPage = 0;

    Integer[] COLLEGE= {R.drawable.college,R.drawable.auditorium,R.drawable.college,R.drawable.auditorium};
    TextView gscrollText ;
    ArrayList<Integer> COLLEGEArray = new ArrayList<Integer>();
    View view;
    private static String getnewsurl2 = "http://www.fratelloinnotech.com/smec/getnews.php";
    private String TAG = Home.class.getSimpleName();


    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_home, container, false);
        gscrollText = (TextView)view.findViewById(R.id.hscrollText);
        aboutus=(Button)view.findViewById(R.id.aboutus);
        facilities=(Button)view.findViewById(R.id.facilities);
        acadamics=(Button)view.findViewById(R.id.academics);
        feedback=(Button)view.findViewById(R.id.guest_feedback);
        gallery=(Button)view.findViewById(R.id.gallery);
        placements=(Button)view.findViewById(R.id.placements);


        //String datascrolltext= (String)gscrollText.getText();
        gscrollText.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        gscrollText.setSingleLine(true);
        gscrollText.setSelected(true);
        init();
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction t = getFragmentManager().beginTransaction();
                Fragment mFrag = new Aboutus();
                t.add(R.id.frame, mFrag);
                t.addToBackStack(Home.this.toString());
                //t.hide();
                t.commit();

            }
        });
        acadamics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction t = getFragmentManager().beginTransaction();
                Fragment mFrag = new Acadamics();
                t.add(R.id.frame, mFrag);
                t.addToBackStack(Home.this.toString());
                t.commit();


            }
        });
        facilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction t = getFragmentManager().beginTransaction();
                Fragment mFrag = new Facilities();
                t.add(R.id.frame, mFrag);
                t.addToBackStack(Home.this.toString());
                t.commit();


            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent feedbackintent = new Intent(getContext(), GuestFeedback.class);
                startActivity(feedbackintent);

            }
        });
        placements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction t = getFragmentManager().beginTransaction();
                Fragment mFrag = new Placements();
                t.add(R.id.frame, mFrag);
                t.addToBackStack(Home.this.toString());
                t.commit();


            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction t = getFragmentManager().beginTransaction();
                Fragment mFrag = new ContactUs();
                t.add(R.id.frame, mFrag);
                t.addToBackStack(Home.this.toString());
                t.commit();


            }
        });
       // new GetNewsGuest().execute();

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

//    private class GetNewsGuest extends AsyncTask<Void,Void,Void> {
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            HttpHandler sh = new HttpHandler();
//
//            // Making a request to url and getting response
//            String jsonStr = sh.makeServiceCall(getnewsurl2);
//            Log.e(TAG, "Response from url: " + jsonStr);
//
//            if (jsonStr != null) {
//                try {
//                    JSONObject jsonObj = new JSONObject(jsonStr);
//                    JSONArray contacts = jsonObj.getJSONArray("result");
//                    String allnews="";
//                    for (int i = 0; i < contacts.length(); i++) {
//                        JSONObject c = contacts.getJSONObject(i);
//                        String id = c.getString("id");
//                        String news = c.getString("news");
//                        String date1value = c.getString("pdate");
//                        allnews+=news;
//
//
//                    }
//                    try {
//                        gscrollText.setText(allnews);
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//                } catch (final JSONException e) {
//                    Log.e(TAG, "Json parsing error: " + e.getMessage());
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(getActivity(), "Json parsing error: " + e.getMessage(), Toast.LENGTH_LONG).show();
//                        }
//                    });
//                }
//            } else {
//                Toast.makeText(getActivity(), "Couldn't get json from server", Toast.LENGTH_SHORT).show();
//            }
//
//            return null;
//        }
//    }

}
