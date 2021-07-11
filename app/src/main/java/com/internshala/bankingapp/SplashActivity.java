package com.internshala.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {
    int SPLASH_TIME = 3000; //This is 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println(this);
                Intent mySuperIntent = new Intent(SplashActivity.this, MainActivity.class);
                mySuperIntent.putExtra("callingActivity",0);
                startActivity(mySuperIntent);
                finish();

            }
        }, SPLASH_TIME);
    }
}