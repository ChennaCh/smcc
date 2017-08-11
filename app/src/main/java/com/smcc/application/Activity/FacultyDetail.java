package com.smcc.application.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.smcc.application.R;

import java.util.ArrayList;

public class FacultyDetail extends AppCompatActivity {
    TextView branch,phone,qualification,message;
    ArrayList<String> details =  new ArrayList<>();
    String tname,tmobile,tqualification,tabout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar collapsingtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_detail);

        branch = (TextView) findViewById(R.id.faculty_branch);
        phone = (TextView) findViewById(R.id.faculty_phoneno);
        qualification = (TextView) findViewById(R.id.faculty_qualification);
        message = (TextView) findViewById(R.id.faculty_msg);
        collapsingtoolbar = (Toolbar) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collasing_toolbar);

        try {
            Bundle b = getIntent().getExtras();
            details =  b.getStringArrayList("values");

        }catch (Exception e){
            e.printStackTrace();
        }
//        collapsingToolbarLayout.setTitle(details.get(0));
        collapsingtoolbar.setTitle(details.get(0));
//        setSupportActionBar(collapsingtoolbar);
        phone.setText(details.get(1));
        branch.setText(details.get(2));
        qualification.setText(details.get(3));
        message.setText(details.get(4));


    }
}
