package com.smcc.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class StartingActivity extends Activity {
    private final int SPLASH_DISPLAY_LENGTH = 2500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_gif);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent intent = new Intent(StartingActivity.this,LoginTypes.class);
                startActivity(intent);

            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
