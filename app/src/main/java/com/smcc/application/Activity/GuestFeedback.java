package com.smcc.application.Activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class GuestFeedback extends Activity {
    private EditText guestname,guestmobile,guestfeedback,guestemail;
    private Button feedbackbutton;
    private String gname,gmobile,gfeedback,gemail,feebackbranch;
    private TextView feedbackresult;
    private Spinner feedbackspinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_feedback);

        guestname = (EditText) findViewById(R.id.guest_name);
        guestmobile = (EditText) findViewById(R.id.guest_mobile);
        guestemail = (EditText) findViewById(R.id.guest_email);
        guestfeedback = (EditText) findViewById(R.id.guest_data);
        feedbackbutton = (Button) findViewById(R.id.feedback_button);
        feedbackresult = (TextView) findViewById(R.id.feedback_result);

        feedbackspinner = (Spinner) findViewById(R.id.feedback_spinner);
        List<String> categories1 = new ArrayList<String>();
        categories1.add("CSE");
        categories1.add("ECE");
        categories1.add("EEE");
        categories1.add("IT");

        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.custom_textview_to_spinner, categories1);
        dataAdapter.setDropDownViewResource(R.layout.custom_textview_to_spinner);
        feedbackspinner.setAdapter(dataAdapter);

        feedbackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gemail = guestemail.getText().toString();
                gname = guestname.getText().toString();
                gmobile = guestmobile.getText().toString();
                gfeedback = guestfeedback.getText().toString();
                feebackbranch = feedbackspinner.getSelectedItem().toString();
                String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                if (!gemail.matches(emailPattern)){
                    guestemail.requestFocus();
                    guestemail.setError("Enter ValidEmail");
                }else if (gname.equals("")){
                    guestname.requestFocus();
                    guestname.setError("Enter Name");
                }else if (gmobile.length()<10){
                    guestmobile.requestFocus();
                    guestmobile.setError("Enter Valid MobileNumber");
                }else if (gfeedback.equals("")){
                    guestfeedback.requestFocus();
                    guestfeedback.setError("Please Enter Some FeedBack");
                }else if (gname.trim().length()>0 && gmobile.trim().length()>0 && gfeedback.trim().length()>0){
                    sendFeedBack(gname,gmobile,gfeedback,gemail,feebackbranch);
                }

            }
        });
    }

    private void sendFeedBack(final String gname, final String gmobile, final String gfeedback,
                              final String gemail,final String feebackbranch) {
        class SendfeedReqAsyncTask extends AsyncTask<Object, Object, Void> {
            @Override
            protected Void doInBackground(Object... strings) {
                List<NameValuePair> namePassPairs = new ArrayList<NameValuePair>();
                namePassPairs.add(new BasicNameValuePair("name", gname));
                namePassPairs.add(new BasicNameValuePair("mobile", gmobile));
                namePassPairs.add(new BasicNameValuePair("email", gemail));
                namePassPairs.add(new BasicNameValuePair("branch", feebackbranch));
                namePassPairs.add(new BasicNameValuePair("password", gfeedback));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://fratelloinnotech.com/smec/addfeedback.php");
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
                feedbackresult.setVisibility(View.VISIBLE);
                feedbackresult.setText("Feedback Send Successfully ");
                guestname.setText("");
                guestmobile.setText("");
                guestemail.setText("");
                guestfeedback.setText("");

            }
        }
        SendfeedReqAsyncTask sendFeedback = new SendfeedReqAsyncTask();
        sendFeedback.execute(gname, gmobile, gfeedback,gemail,feebackbranch);
    }
}
