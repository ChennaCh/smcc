package com.smcc.application.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.smcc.application.Adapters.GetFacultyAdapter;
import com.smcc.application.Bean.GetFacultyBean;
import com.smcc.application.HttpHandler;
import com.smcc.application.R;
import com.smcc.application.UserLogins.Admin;
import com.smcc.application.UserLogins.LoginTypes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewFacultyActivity extends AppCompatActivity {

    private String TAG = ViewFacultyActivity.class.getSimpleName();

    private ListView list;
    private ProgressBar viewprogress;
    private Activity activity;
    private ArrayList<GetFacultyBean> adapterItems = new ArrayList<GetFacultyBean>();

    private static String url = "http://www.fratelloinnotech.com/smec/getfaculty.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_faculty);

        list = (ListView) findViewById(R.id.view_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        viewprogress = (ProgressBar) findViewById(R.id.viewfaculty_progress);
        viewprogress.getIndeterminateDrawable().setColorFilter(0xFFFF0000, android.graphics.PorterDuff.Mode.MULTIPLY);
        new GetContacts().execute();

    }

    private class GetContacts extends AsyncTask<Void,Void,ArrayList<GetFacultyBean>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            viewprogress.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<GetFacultyBean> doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);
            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray contacts = jsonObj.getJSONArray("result");

                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String id = c.getString("id");
                        String name = c.getString("name");
                        String pass = c.getString("password");
                        String phone = c.getString("mobile");
                        String email = c.getString("email");
                        String branch = c.getString("branch");
                        String gender = c.getString("gender");
                        String qualification = c.getString("qualification");
                        String about = c.getString("about");

//                        validation(name,pass);
                        adapterItems.add(new GetFacultyBean(id,name,pass,phone,email,branch,gender,qualification,about));
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity, "Json parsing error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            else {
                Toast.makeText(getApplicationContext(), "Couldn't get json from server", Toast.LENGTH_SHORT).show();
            }

            return adapterItems;
        }

        @Override
        protected void onPostExecute(final ArrayList<GetFacultyBean> getFacultyBeen) {
            super.onPostExecute(getFacultyBeen);
            viewprogress.setVisibility(View.GONE);


            if (getFacultyBeen != null){
                GetFacultyAdapter adater1 = new GetFacultyAdapter(ViewFacultyActivity.this,getFacultyBeen);
                list.setAdapter(adater1);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                        Toast.makeText(ViewFacultyActivity.this, "Toast "+position, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ViewFacultyActivity.this,FacultyDetail.class);
                        GetFacultyBean  b = getFacultyBeen.get(position);
                        ArrayList<String> values = new ArrayList<String>();

                        values.add(b.getGname());
                        values.add(b.getGmobile());
                        values.add(b.getGbranch());
                        values.add(b.getGqualification());
                        values.add(b.getGabout());
                        values.add(b.getGemail());
                        values.add(b.getId());
                        intent.putStringArrayListExtra("values",values);
                        startActivity(intent);
                    }
                });
            } else {

                Toast.makeText(activity, "Not able to fetch data from server, please check url.", Toast.LENGTH_SHORT).show();

            }
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
