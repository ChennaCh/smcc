package com.smcc.application.UserLogins;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.smcc.application.AdminWelcome;
import com.smcc.application.R;

public class Admin extends Activity {
    public TextInputLayout adminuname,adminpwd;
    public EditText inputuname,inputpwd;
    Button adminLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        adminuname = (TextInputLayout) findViewById(R.id.adminuname);
        adminpwd = (TextInputLayout) findViewById(R.id.adminpwd);
        inputpwd=(EditText)findViewById(R.id.input_password);
        inputuname=(EditText)findViewById(R.id.input_email);
        adminLogin=(Button)findViewById(R.id.btn_login);
        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), AdminWelcome.class);
                startActivity(intent);
            }
        });


    }
}
