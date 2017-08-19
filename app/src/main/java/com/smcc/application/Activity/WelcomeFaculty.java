package com.smcc.application.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.smcc.application.Adapters.SlideimageAdapter;
import com.smcc.application.R;
import com.smcc.application.UserLogins.Faculty;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class WelcomeFaculty extends Activity {
    ViewPager fviewPager;
    private static int currentPage = 0;
    Button logout,fchangepass,ffeedback;
    Integer[] COLLEGE= {R.drawable.college,R.drawable.auditorium,R.drawable.lib,R.drawable.library};
    TextView fscrollText;
    ArrayList<Integer> COLLEGEArray = new ArrayList<Integer>();
    private String usernmae1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_faculty);

        fscrollText = (TextView)findViewById(R.id.fscrollText);
        logout = (Button) findViewById(R.id.facu_logout);
        fscrollText.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        fscrollText.setSingleLine(true);
        fscrollText.setSelected(true);
        fchangepass = (Button) findViewById(R.id.f_changepass);
        ffeedback = (Button) findViewById(R.id.f_feedback);

        SharedPreferences preferences = getSharedPreferences("facultydetails",MODE_PRIVATE);
        usernmae1 = preferences.getString("fausername",null);
        fscrollText.setText("Welcome to "+usernmae1.toUpperCase());

        ffeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeFaculty.this, FacultyViewFeedback.class));
            }
        });

        fchangepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(WelcomeFaculty.this, FacultyChangePassword.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(WelcomeFaculty.this)
                        .setIcon(R.drawable.alerticon)
                        .setTitle("Logout")
                        .setMessage("Are you sure you want to LogOut !!")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent facultyintent = new Intent(WelcomeFaculty.this, Faculty.class);
                                startActivity(facultyintent);
                            }

                        })

                        .setNegativeButton("No", null)
                        .show();
            }
        });
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

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(WelcomeFaculty.this)
                .setIcon(R.drawable.alerticon)
                .setTitle("Logout")
                .setMessage("Are you sure you want to LogOut !!")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent facultyintent = new Intent(WelcomeFaculty.this, Faculty.class);
                        startActivity(facultyintent);
                    }

                })

                .setNegativeButton("No", null)
                .show();
    }

}
