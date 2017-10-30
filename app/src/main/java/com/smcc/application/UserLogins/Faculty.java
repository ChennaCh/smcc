package com.smcc.application.UserLogins;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.smcc.application.Activity.AdminWelcome;
import com.smcc.application.HttpHandler;
import com.smcc.application.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Faculty extends AppCompatActivity {

    private String TAG = Faculty.class.getSimpleName();
    private EditText finputpwd,finputuname;
    private Button facultylogin;
    private TextView faculty_status;
    private int flag = 0;
    String fusername,fpwd;
    private ProgressBar faculty_proress;

    String user,passwrd,fbranch;

    private static String facultyloginurl = "http://www.fratelloinnotech.com/smec/getfaculty.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);

        finputuname = (EditText) findViewById(R.id.faculty_email);
        finputpwd = (EditText) findViewById(R.id.faculty_password) ;
        facultylogin=(Button)findViewById(R.id.faculty_login);
        faculty_status = (TextView) findViewById(R.id.faculty_settext);

        faculty_proress = (ProgressBar) findViewById(R.id.faculty_progress);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        facultylogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fusername=finputuname.getText().toString();
                fpwd=finputpwd.getText().toString();

                if(fusername.equals("")){

                    finputuname.setError("Enter valid username");
                    finputuname.setFocusable(true);

                }else if(fpwd.equals("")) {

                    finputpwd.setError("Enter valid Password");
                    finputpwd.setFocusable(true);
                }
                  else {//faculty_status.setText("");
                    new FacultyGetContacts().execute();
                }

            }
        });
    }


    private class FacultyGetContacts extends AsyncTask<Void,Void,Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            faculty_proress.setVisibility(View.VISIBLE);

        }

        @Override
        protected Integer doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(facultyloginurl);
            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray contacts = jsonObj.getJSONArray("result");
                    flag = 0;

                    user = finputuname.getText().toString();
                    passwrd = finputpwd.getText().toString();
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String name = c.getString("name");
                        String pass = c.getString("password");
                        fbranch = c.getString("branch");
//                        String date = c.getString("udate");
//                        validation(name,pass);
                        if (user.equals(name) && passwrd.equals(pass)){
                            flag = 1;
                            break;
                        }
//                        adapterItems.add(new AdminBean(name,pass,date));
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Json parsing error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            else {
                faculty_status.setText("Invalid Username and Password");
            }

            return flag;
        }

        @Override
        protected void onPostExecute(Integer i) {
            super.onPostExecute(i);
            faculty_proress.setVisibility(View.GONE);
            if (i == 1){
                SharedPreferences preferences = getSharedPreferences("facultydetails",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                String fausernmae = finputuname.getText().toString();
                String fapass = finputpwd.getText().toString();
                editor.putString("fausername",fausernmae);
                editor.putString("fapassword",fapass);
                editor.putString("fbranch",fbranch);
                editor.commit();
                Toast.makeText(getApplicationContext(), "Successfully Login", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(), WelcomeFaculty.class);
                intent.putExtra("uname",user);
                startActivity(intent);
            }else {
//                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
                faculty_status.setText("Invalid Username and Password");
            }
        }
    }
    @Override
    public void onBackPressed() {
        Intent inte = new Intent(Faculty.this,LoginTypes.class);
        startActivity(inte);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

