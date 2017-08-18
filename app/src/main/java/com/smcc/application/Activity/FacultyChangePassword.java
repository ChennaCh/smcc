package com.smcc.application.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.smcc.application.R;

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

public class FacultyChangePassword extends Activity {
    private EditText ftusername, ftpassword, ftchgpassword, ftconfirmpassword;
    private String ftstoredname, ftstoredpassword, ftuserid, ftpassid, ftchgid, ftconfirmid;
    private Button ftchangepasswoed;
    private TextView ftpassresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        ftusername = (EditText) findViewById(R.id.pass_username);
        ftpassword = (EditText) findViewById(R.id.pass_currentpass);
        ftchgpassword = (EditText) findViewById(R.id.pass_new);
        ftconfirmpassword = (EditText) findViewById(R.id.pas_confirm);
        ftpassresult = (TextView) findViewById(R.id.pass_result);

        ftusername.requestFocus();

        SharedPreferences preferences = getSharedPreferences("facultydetails", MODE_PRIVATE);
        ftstoredname = preferences.getString("fausername", null);
        ftstoredpassword = preferences.getString("fapassword", null);

        ftusername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ftpassresult.setText("");
            }
        });
        ftpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ftpassresult.setText("");
            }
        });
        ftchgpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ftpassresult.setText("");
            }
        });
        ftconfirmpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ftpassresult.setText("");
            }
        });

        ftchangepasswoed = (Button) findViewById(R.id.change_password);
        ftchangepasswoed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ftuserid = ftusername.getText().toString();
                ftpassid = ftpassword.getText().toString();
                ftchgid = ftchgpassword.getText().toString();
                ftconfirmid = ftconfirmpassword.getText().toString();
                if (ftstoredname.equals(ftuserid) && ftstoredpassword.equals(ftpassid)) {
                    if (ftchgid.equals(ftconfirmid)) {
                        insertToChangePassword(ftuserid, ftpassid, ftchgid);
                    }
                    else {
                        ftchgpassword.requestFocus();
                        ftchgpassword.setError("Password Doesn't Match");
                    }
                } else {
                    ftusername.requestFocus();
                    ftusername.setError("WrongUserId/Password");
                }
            }
        });


    }

    private void insertToChangePassword(final String userid, final String passid, final String chgid) {

        class SendPassReqAsyncTask extends AsyncTask<Object, Object, Void> {
            @Override
            protected Void doInBackground(Object... strings) {
                List<NameValuePair> namePassPairs = new ArrayList<NameValuePair>();
                namePassPairs.add(new BasicNameValuePair("name", userid));
                namePassPairs.add(new BasicNameValuePair("opassword", passid));
                namePassPairs.add(new BasicNameValuePair("password", chgid));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://www.fratelloinnotech.com/smec/facultychpwd.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(namePassPairs));
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
                ftpassresult.setVisibility(View.VISIBLE);
                ftpassresult.setText("Password Changed Succefully ");
                ftusername.setText("");
                ftpassword.setText("");
                ftchgpassword.setText("");
                ftconfirmpassword.setText("");

            }
        }
        SendPassReqAsyncTask sendPassrequest = new SendPassReqAsyncTask();
        sendPassrequest.execute(userid, passid, chgid);
    }
}