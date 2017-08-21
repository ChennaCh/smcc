package com.smcc.application.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.smcc.application.Adapters.FeedBackAdapter;
import com.smcc.application.Bean.GetFeedbackBean;
import com.smcc.application.HttpHandler;
import com.smcc.application.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FacultyViewFeedback extends AppCompatActivity {
    private RecyclerView facultyrecyle;
    private FeedBackAdapter facultyfeedBackAdapter;
    ProgressBar facultyviewprogress;
    private String TAG = FacultyViewFeedback.class.getSimpleName();
    List<GetFeedbackBean> ftdata=new ArrayList<>();
    private static String ftfeedbackurl = "http://www.fratelloinnotech.com/smec/getfeedbybranch.php";
    private String fbranch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_view_feedback);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        SharedPreferences preferences = getSharedPreferences("facultydetails", MODE_PRIVATE);
        fbranch = preferences.getString("fbranch", null);

        facultyviewprogress = (ProgressBar) findViewById(R.id.faculty_feedprogress);
        facultyrecyle = (RecyclerView) findViewById(R.id.faculty_recyclerview);
        new FacultyAsyncFetch().execute();


    }

    private class FacultyAsyncFetch extends AsyncTask<Void,Void,List<GetFeedbackBean>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            facultyviewprogress.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<GetFeedbackBean> doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(ftfeedbackurl+"?branch="+fbranch);
            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray contacts = jsonObj.getJSONArray("result");
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String id = c.getString("id");
                        String name = c.getString("name");
                        String phone = c.getString("mobile");
                        String branch = c.getString("branch");
                        String email = c.getString("email");
                        String message = c.getString("msg");
                        ftdata.add(new GetFeedbackBean(id, name, phone, email, branch, message));
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(FacultyViewFeedback.this, "Json parsing error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            else {
                Toast.makeText(getApplicationContext(), "Couldn't get json from server", Toast.LENGTH_SHORT).show();
            }

            return ftdata;
        }

        @Override
        protected void onPostExecute(List<GetFeedbackBean> facgetFeedbackBeen) {
            super.onPostExecute(facgetFeedbackBeen);
            facultyviewprogress.setVisibility(View.GONE);

            facultyfeedBackAdapter = new FeedBackAdapter(FacultyViewFeedback.this,facgetFeedbackBeen);
            facultyrecyle.setAdapter(facultyfeedBackAdapter);
            facultyrecyle.setLayoutManager(new LinearLayoutManager(FacultyViewFeedback.this));

        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
