package com.smcc.application.UserLogins;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.smcc.application.Activity.AdminWelcome;
import com.smcc.application.Activity.FacultyChangePassword;
import com.smcc.application.Activity.FacultyViewFeedback;
import com.smcc.application.Activity.StartingActivity;
import com.smcc.application.Adapters.SlideimageAdapter;
import com.smcc.application.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class WelcomeFaculty extends AppCompatActivity {
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.facultytoolbar);
        //TextView toolbarTextView = (TextView) findViewById(R.id.toolbarTextView);
        setSupportActionBar(toolbar);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome_faculty_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id==R.id.facultyhub){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("http://119.235.48.130/smec"));
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
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
