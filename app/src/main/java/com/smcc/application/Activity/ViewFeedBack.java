package com.smcc.application.Activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

public class ViewFeedBack extends AppCompatActivity {

    private RecyclerView recyle;
    private FeedBackAdapter feedBackAdapter;
    ProgressBar progress;
    private String TAG = ViewFeedBack.class.getSimpleName();
    List<GetFeedbackBean> data=new ArrayList<>();
    private static String feedbackurl = "http://fratelloinnotech.com/smec/getfeedback.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feed_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        progress = (ProgressBar) findViewById(R.id.feedabck_progress1);
        recyle = (RecyclerView) findViewById(R.id.feedback_recyclerview);
        new AsyncFetch().execute();
    }

    private class AsyncFetch extends AsyncTask<Void,Void,List<GetFeedbackBean>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<GetFeedbackBean> doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(feedbackurl);
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
                        String email = c.getString("email");
                        String branch = c.getString("branch");
                        String message = c.getString("msg");

                        data.add(new GetFeedbackBean(id,name,phone,email,branch,message));
//
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ViewFeedBack.this, "Json parsing error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            else {
                Toast.makeText(getApplicationContext(), "Couldn't get json from server", Toast.LENGTH_SHORT).show();
            }

            return data;
        }

        @Override
        protected void onPostExecute(List<GetFeedbackBean> getFeedbackBeen) {
            super.onPostExecute(getFeedbackBeen);
            progress.setVisibility(View.GONE);

            feedBackAdapter = new FeedBackAdapter(ViewFeedBack.this,getFeedbackBeen);
            recyle.setAdapter(feedBackAdapter);
            recyle.setLayoutManager(new LinearLayoutManager(ViewFeedBack.this));

        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
