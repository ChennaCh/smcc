package com.smcc.application.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.smcc.application.R;
import com.smcc.application.UserLogins.Admin;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FacultyDetail extends AppCompatActivity {
    TextView branch,phone,qualification,message,mail;
    ArrayList<String> details =  new ArrayList<>();
    String tname,tmobile,tqualification,tabout,tid;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar collapsingtoolbar;
    Toolbar toolbar;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        branch = (TextView) findViewById(R.id.faculty_branch);
        phone = (TextView) findViewById(R.id.faculty_phoneno);
        qualification = (TextView) findViewById(R.id.faculty_qualification);
        message = (TextView) findViewById(R.id.faculty_msg);
        mail = (TextView) findViewById(R.id.faculty_mail12);
        fab = (FloatingActionButton)findViewById(R.id.deletefac);
//        collapsingtoolbar = (Toolbar) findViewById(R.id.collapsing_name);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_name);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        try {
            Bundle b = getIntent().getExtras();
            details =  b.getStringArrayList("values");

        }catch (Exception e){
            e.printStackTrace();
        }

        collapsingToolbarLayout.setTitleEnabled(true);
        collapsingToolbarLayout.setTitle(details.get(0).toUpperCase());
        tid = details.get(6);
        phone.setText(details.get(1));
        branch.setText(details.get(2));
        qualification.setText(details.get(3));
        message.setText(details.get(4));
        mail.setText(details.get(5));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(FacultyDetail.this)
                        .setIcon(R.drawable.deleteicion)
                        .setTitle("Delete")
                        .setMessage("Are you sure you want to Delete !!")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                insertToDatabase(tid);

                            }

                        })

                        .setNegativeButton("No", null)
                        .show();
            }
        });

    }

    private void insertToDatabase(final String tid) {
        class SendPostReqAsyncTask extends AsyncTask<Object, Object, Void> {
            @Override
            protected Void doInBackground(Object... strings) {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("fid", tid));



                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://www.fratelloinnotech.com/smec/deletefaculty.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Faculty Deleted Successfully", Toast.LENGTH_LONG).show();
                Intent deleteintent = new Intent(FacultyDetail.this, ViewFacultyActivity.class);
                startActivity(deleteintent);
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(tid);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
