package com.smcc.application.UserLogins;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.smcc.application.Activity.AdminWelcome;
import com.smcc.application.Bean.AdminBean;
import com.smcc.application.HttpHandler;
import com.smcc.application.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Admin extends Activity {
    private String TAG = Admin.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView lv;
    private ArrayList<AdminBean> adapterItems = null;
    private Activity activity;
    private ProgressBar progressBar;
    int flag = 0;

    public EditText inputuname,inputpwd;
    Button adminLogin;
    String user,passwrd;
    TextView textView;

    private static String url = "http://www.fratelloinnotech.com/smec/adminusers.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        inputpwd=(EditText)findViewById(R.id.input_password);
        inputuname=(EditText)findViewById(R.id.input_email);
        progressBar = (ProgressBar) findViewById(R.id.admin_progress);
        adminLogin=(Button)findViewById(R.id.btn_login);
        textView = (TextView)findViewById(R.id.settext);

        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("");
                new GetContacts1().execute();
            }
        });
    }

    private class GetContacts1 extends AsyncTask<Void,Void,Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected Integer doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);
            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray contacts = jsonObj.getJSONArray("result");
                    flag = 0;

                    user = inputuname.getText().toString();
                    passwrd = inputpwd.getText().toString();
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String name = c.getString("name");
                        String pass = c.getString("password");
                        String date = c.getString("udate");
//                        validation(name,pass);
                        if (user.equals(name) && passwrd.equals(pass)){
                            flag = 1;
                            break;
                        }
//                        adapterItems.add(new AdminBean(name,pass,date));
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
                textView.setText("Invalid Username and Password");
            }

            return flag;
        }

        @Override
        protected void onPostExecute(Integer i) {
            super.onPostExecute(i);
            progressBar.setVisibility(View.GONE);
            if (i == 1){
                Toast.makeText(getApplicationContext(), "Successfully Login", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(), AdminWelcome.class);
                intent.putExtra("uname",user);
                startActivity(intent);
            }else {
//                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
                textView.setText("Invalid Username and Password");
            }
        }
    }
    @Override
    public void onBackPressed() {
        Intent inte = new Intent(Admin.this,LoginTypes.class);
        startActivity(inte);
    }
}
