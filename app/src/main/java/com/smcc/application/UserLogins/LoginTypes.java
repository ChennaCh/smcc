package com.smcc.application.UserLogins;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.smcc.application.R;
import com.smcc.application.Utils.NotificationUtils;
import com.smcc.application.app.Config;

public class LoginTypes extends Activity {
    Button adminbtn, facultybtn, guestbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_types);

        adminbtn = (Button) findViewById(R.id.admin);
        facultybtn = (Button) findViewById(R.id.faculty);
        guestbtn = (Button) findViewById(R.id.guest);

        adminbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent adminintent = new Intent(LoginTypes.this, Admin.class);
                startActivity(adminintent);
            }
        });

        facultybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent facultyintent = new Intent(LoginTypes.this, Faculty.class);
                startActivity(facultyintent);
            }
        });

        guestbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent guestintent = new Intent(LoginTypes.this, WelcomeGuest.class);
                startActivity(guestintent);
            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

}
