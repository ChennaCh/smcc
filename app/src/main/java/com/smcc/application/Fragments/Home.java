package com.smcc.application.Fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.smcc.application.Activity.GuestFeedback;
import com.smcc.application.AboutUsActivities.Vision;
import com.smcc.application.Adapters.SlideimageAdapter;
import com.smcc.application.Bean.GetFacultyBean;
import com.smcc.application.HttpHandler;
import com.smcc.application.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    Integer[] COLLEGE= {R.drawable.smeccollege,R.drawable.smecauditorium,R.drawable.smecblock,R.drawable.smeccorridor};
    TextView gscrollText ;
    ArrayList<Integer> COLLEGEArray = new ArrayList<Integer>();
    View view;
    private static String getnewsurl2 = "http://www.fratelloinnotech.com/smec/getnews.php";
    private String TAG = Home.class.getSimpleName();
    private static String getnewsurl = "http://www.fratelloinnotech.com/smec/getnews.php";


    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        gscrollText = (TextView)view.findViewById(R.id.guest_scrollText);
        gscrollText.setText("welcome to guest");
        aboutus=(Button)view.findViewById(R.id.aboutus);
        facilities=(Button)view.findViewById(R.id.facilities);
        acadamics=(Button)view.findViewById(R.id.academics);
        feedback=(Button)view.findViewById(R.id.guest_feedback);
        gallery=(Button)view.findViewById(R.id.gallery);
        placements=(Button)view.findViewById(R.id.placements);




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
        gscrollText.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        gscrollText.setSingleLine(true);
        gscrollText.setSelected(true);
       init();
       new GetNews(getActivity()).execute();

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

    private class GetNews extends AsyncTask<Void,Void,ArrayList<String>> {
        Activity mContex;
        String news="";
        public GetNews( Activity mContex)
        {
            this.mContex = mContex;
        }
        @Override
        protected ArrayList<String> doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler();
            ArrayList<String> list = new ArrayList<>();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(getnewsurl);
            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {

                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray contacts = jsonObj.getJSONArray("result");

                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                       // String id = c.getString("id");
                       news += c.getString("news")+" - "+c.getString("pdate")+"  ******  ";
                    }
                    mContex.runOnUiThread(new Runnable() {

                        public void run() {
                            gscrollText.setText(news);
                            // Toast.makeText(mContex, "Example for Toast", Toast.LENGTH_SHORT).show();

                        }
                    });
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());

                }
            } else {
                Toast.makeText(getActivity(), "Couldn't get json from server", Toast.LENGTH_SHORT).show();
            }

            return null;
        }
    }
}
