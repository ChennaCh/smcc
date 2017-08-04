package com.smcc.application.UserLogins;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.smcc.application.R;

public class Faculty extends Activity {
TextInputLayout facultyuname,facultypwd;
    EditText finputpwd,finputuname;
    Button facultylogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);
        facultyuname = (TextInputLayout) findViewById(R.id.facultynuname);
        facultypwd = (TextInputLayout) findViewById(R.id.facultypwd);
        finputpwd=(EditText)findViewById(R.id.finput_password);
        finputuname=(EditText)findViewById(R.id.finput_email);
        facultylogin=(Button)findViewById(R.id.fbtn_login);
        facultylogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), WelcomeFaculty.class);
                startActivity(intent);
            }
        });
    }
}
