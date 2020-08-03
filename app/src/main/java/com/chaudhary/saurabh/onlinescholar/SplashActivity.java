package com.chaudhary.saurabh.onlinescholar;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class SplashActivity extends AppCompatActivity {

    //Setting Splash Delay Length
    private final static int SPLASH_DELAY_LENGTH = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, login.class));
                finish();
            }
        }, SPLASH_DELAY_LENGTH);
    }
}
