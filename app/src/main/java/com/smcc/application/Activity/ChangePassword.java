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

public class ChangePassword extends Activity {
    private EditText username, password, chgpassword, confirmpassword;
    private String storedname, storedpassword, userid, passid, chgid, confirmid;
    private Button changepasswoed;
    private TextView passresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        username = (EditText) findViewById(R.id.pass_username);
        password = (EditText) findViewById(R.id.pass_currentpass);
        chgpassword = (EditText) findViewById(R.id.pass_new);
        confirmpassword = (EditText) findViewById(R.id.pas_confirm);
        passresult = (TextView) findViewById(R.id.pass_result);

        username.requestFocus();

        SharedPreferences preferences = getSharedPreferences("userdetails", MODE_PRIVATE);
        storedname = preferences.getString("username", null);
        storedpassword = preferences.getString("password", null);

        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passresult.setText("");
            }
        });
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passresult.setText("");
            }
        });
        chgpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passresult.setText("");
            }
        });
        confirmpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passresult.setText("");
            }
        });

        changepasswoed = (Button) findViewById(R.id.change_password);
        changepasswoed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userid = username.getText().toString();
                passid = password.getText().toString();
                chgid = chgpassword.getText().toString();
                confirmid = confirmpassword.getText().toString();
                if (storedname.equals(userid) && storedpassword.equals(passid)) {
                    if (chgid.equals(confirmid)) {
                        insertToChangePassword(userid, passid, chgid);
                    }
                    else {
                        chgpassword.requestFocus();
                        chgpassword.setError("Password Doesn't Match");
                    }
                } else {
                    username.requestFocus();
                    username.setError("WrongUserId/Password");
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
                    HttpPost httpPost = new HttpPost("http://www.fratelloinnotech.com/smec/adminchpwd.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(namePassPairs));
                    HttpResponse response = httpClient.execute(httpPost);
//                    Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                    HttpEntity entity = response.getEntity();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                passresult.setVisibility(View.VISIBLE);
                passresult.setText("Password Changed Succefully ");
                username.setText("");
                password.setText("");
                chgpassword.setText("");
                confirmpassword.setText("");

            }
        }
        SendPassReqAsyncTask sendPassrequest = new SendPassReqAsyncTask();
        sendPassrequest.execute(userid, passid, chgid);
    }
}
