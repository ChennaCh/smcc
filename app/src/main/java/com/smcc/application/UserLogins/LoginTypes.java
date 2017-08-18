package com.smcc.application.UserLogins;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.smcc.application.R;

public class LoginTypes extends Activity {
    Button adminbtn, facultybtn, guestbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample);

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
                Toast.makeText(LoginTypes.this, "kk", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

//        finish();
    }


}
