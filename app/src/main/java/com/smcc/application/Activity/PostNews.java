package com.smcc.application.Activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class PostNews extends Activity {
    EditText postnews;
    Button postbtn;
    String getnews;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_news);

        postnews = (EditText) findViewById(R.id.news_post);
        result = (TextView) findViewById(R.id.news_result);
        postbtn = (Button) findViewById(R.id.post_button);

        postnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    result.setText("");
            }
        });

        postbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getnews = postnews.getText().toString();
                insertToPostnews(getnews);
            }
        });

    }

    private void insertToPostnews(final String getnews) {

        class SendNewsReqAsyncTask extends AsyncTask<Object, Object, Void> {
            @Override
            protected Void doInBackground(Object... strings) {
                List<NameValuePair> nameNewsPairs = new ArrayList<NameValuePair>();
                nameNewsPairs.add(new BasicNameValuePair("news", getnews));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://www.fratelloinnotech.com/smec/addnews.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameNewsPairs));
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
                result.setText(" News Added Succefully ");
                postnews.setText("");
            }
        }
        SendNewsReqAsyncTask sendPostReqAsyncTask = new SendNewsReqAsyncTask();
        sendPostReqAsyncTask.execute(getnews);
    }
}
