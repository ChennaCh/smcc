package com.smcc.application.Activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.smcc.application.Bean.AddFacultyBean;
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

public class AddFacultyActivty extends Activity {

    private String TAG = AddFacultyActivty.class.getSimpleName();

    private Button addfacultybtn, male, female;
    private Spinner addspinner, qualificationspinner;
    private String item;
    private EditText username, password, email, phone,about;
    ProgressBar addprogress;
    ImageView gender;
    private String gendertype = "MALE";
    private AddFacultyBean addbean;
    private String getname, getpass, getemail, getphone, gettype, getqualification,aboutfaculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faculty_activty);
        addfacultybtn = (Button) findViewById(R.id.faculty_add);

        username = (EditText) findViewById(R.id.add_name);
        password = (EditText) findViewById(R.id.add_password);
        phone = (EditText) findViewById(R.id.add_phone);
        email = (EditText) findViewById(R.id.add_email);
        addprogress = (ProgressBar) findViewById(R.id.add_progress);
        gender = (ImageView) findViewById(R.id.user_profile_photo);
        about = (EditText) findViewById(R.id.faculty_about);

        male = (Button) findViewById(R.id.add_male);
        male.setText("MALE");
        male.setTextColor(Color.rgb(255, 0, 0));

        female = (Button) findViewById(R.id.ad_female);

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                male.setText("MALE");
                male.setTextColor(Color.rgb(255, 0, 0));
                female.setTextColor(Color.rgb(0, 0, 0));
                gender.setImageResource(R.drawable.men);
                gendertype = "MALE";
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                female.setText("FEMALE");
                male.setTextColor(Color.rgb(0, 0, 0));
                female.setTextColor(Color.rgb(255, 0, 0));
                gender.setImageResource(R.drawable.women);
                gendertype = "FEMALE";
            }
        });


        qualificationspinner = (Spinner) findViewById(R.id.faculty__qual_spinner);
        List<String> categories1 = new ArrayList<String>();
        categories1.add("PHD");
        categories1.add("M-Tech");
        categories1.add("M-Phil");

        final ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, R.layout.custom_textview_to_spinner, categories1);
        dataAdapter1.setDropDownViewResource(R.layout.custom_textview_to_spinner);
        qualificationspinner.setAdapter(dataAdapter1);

        addspinner = (Spinner) findViewById(R.id.faculty_spinner);
        List<String> categories = new ArrayList<String>();
        categories.add("CSE");
        categories.add("ECE");
        categories.add("EEE");
        categories.add("IT");

        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.custom_textview_to_spinner, categories);
        dataAdapter.setDropDownViewResource(R.layout.custom_textview_to_spinner);
        addspinner.setAdapter(dataAdapter);

        addfacultybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getname = username.getText().toString();
                getpass = password.getText().toString();
                getphone = phone.getText().toString();
                getemail = email.getText().toString();
                gettype = addspinner.getSelectedItem().toString();
                getqualification = qualificationspinner.getSelectedItem().toString();
                aboutfaculty = about.getText().toString();

                insertToDatabase(getname, getpass, getphone, getemail, gettype, gendertype,aboutfaculty);
            }
        });
    }

    private void insertToDatabase(final String getname, final String getpass, final String getphone,
                                  final String getemail, final String gettype, final String gendertype, final String aboutfaculty) {
        class SendPostReqAsyncTask extends AsyncTask<Object, Object, String> {
            @Override
            protected String doInBackground(Object... strings) {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("name", getname));
                nameValuePairs.add(new BasicNameValuePair("password", getpass));
                nameValuePairs.add(new BasicNameValuePair("mobile", getphone));
                nameValuePairs.add(new BasicNameValuePair("email", getemail));
                nameValuePairs.add(new BasicNameValuePair("branch", gettype));
                nameValuePairs.add(new BasicNameValuePair("qualification", getqualification));
                nameValuePairs.add(new BasicNameValuePair("gender", gendertype));
                nameValuePairs.add(new BasicNameValuePair("about", aboutfaculty));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://www.fratelloinnotech.com/smec/addfaculty.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                    HttpEntity entity = response.getEntity();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return "Success";
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(getname, getpass, getphone, getemail, gettype, getqualification, gendertype,aboutfaculty);
    }
}
