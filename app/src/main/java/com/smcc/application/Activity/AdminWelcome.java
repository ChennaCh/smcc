package com.smcc.application.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.smcc.application.Adapters.SlideimageAdapter;
import com.smcc.application.Bean.GetFacultyBean;
import com.smcc.application.HttpHandler;
import com.smcc.application.R;
import com.smcc.application.UserLogins.Admin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class AdminWelcome extends Activity {

    private String TAG = AdminWelcome.class.getSimpleName();

   ViewPager viewPager;
    String username;
    private static int currentPage = 0;
    Button logoutbtn,addfacultybtn,viewfacultybtn,postnews,changepwd,feedback;
    Integer[] COLLEGE1= {R.drawable.smeccollege,R.drawable.smecauditorium,R.drawable.smecblock,R.drawable.smeccorridor};
    //SlideimageAdapter adapter;
    TextView scrollText;
    ArrayList<Integer> COLLEGEArray = new ArrayList<Integer>();

    private static String getnewsurl = "http://www.fratelloinnotech.com/smec/getnews.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_welcome);
        scrollText = (TextView)findViewById(R.id.admin_scrollText);
        //String datascrolltext= scrollText.getText().toString();
        Bundle b = getIntent().getExtras();
        username = b.getString("uname");

        logoutbtn = (Button)findViewById(R.id.admin_logout);
        addfacultybtn = (Button)findViewById(R.id.admin_addFaculty);
        viewfacultybtn = (Button) findViewById(R.id.view_Faculty);
        postnews= (Button) findViewById(R.id.postNews);
        changepwd = (Button) findViewById(R.id.changepwd);
        feedback = (Button) findViewById(R.id.admin_feedback1);

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent feedbackintent = new Intent(AdminWelcome.this,ViewFeedBack.class);
                startActivity(feedbackintent);
            }
        });

        changepwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chagepassintent = new Intent(AdminWelcome.this,ChangePassword.class);
                startActivity(chagepassintent);
            }
        });

        postnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent postnewsintent = new Intent(AdminWelcome.this,PostNews.class);
                startActivity(postnewsintent);
            }
        });

        viewfacultybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addfacultyintent = new Intent(AdminWelcome.this,ViewFacultyActivity.class);
                startActivity(addfacultyintent);
            }
        });

        addfacultybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addfacultyintent = new Intent(AdminWelcome.this,AddFacultyActivty.class);
                startActivity(addfacultyintent);
            }
        });

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
//        scrollText.setEllipsize(TextUtils.TruncateAt.MARQUEE);
//        scrollText.setSingleLine(true);
//        scrollText.setSelected(true);
        init();
//        new GetNews().execute();

    }

    private void init() {
        for(int i=0;i<COLLEGE1.length;i++)
            COLLEGEArray.add(COLLEGE1[i]);

        viewPager = (ViewPager)findViewById(R.id.adminvpager);
        viewPager.setAdapter(new SlideimageAdapter(AdminWelcome.this,COLLEGEArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.adminindicator);
        indicator.setViewPager(viewPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == COLLEGE1.length) {
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

//    private class GetNews extends AsyncTask<Void,Void,ArrayList<GetFacultyBean>> {
//
//        @Override
//        protected ArrayList<GetFacultyBean> doInBackground(Void... voids) {
//            HttpHandler sh = new HttpHandler();
//
//            // Making a request to url and getting response
//            String jsonStr = sh.makeServiceCall(getnewsurl);
//            Log.e(TAG, "Response from url: " + jsonStr);
//
//            if (jsonStr != null) {
//                try {
//                    JSONObject jsonObj = new JSONObject(jsonStr);
//                    JSONArray contacts = jsonObj.getJSONArray("result");
//
//                    for (int i = 0; i < contacts.length(); i++) {
//                        JSONObject c = contacts.getJSONObject(i);
//                        String id = c.getString("id");
//                        String news = c.getString("news");
//                        String date = c.getString("pdate");
//
//                        if (id.equals("21")){
//
//                            scrollText.setText(news);
//                        }
//
//
//                    }
//                } catch (final JSONException e) {
//                    Log.e(TAG, "Json parsing error: " + e.getMessage());
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(AdminWelcome.this, "Json parsing error: " + e.getMessage(), Toast.LENGTH_LONG).show();
//                        }
//                    });
//                }
//            } else {
//                Toast.makeText(getApplicationContext(), "Couldn't get json from server", Toast.LENGTH_SHORT).show();
//            }
//
//            return null;
//        }
//    }
}
