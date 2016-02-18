package com.skandiacup.splinedevelopment.skandiacup.MainViews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.skandiacup.splinedevelopment.skandiacup.R;
import com.skandiacup.splinedevelopment.skandiacup.repository.DataManager;
import com.skandiacup.splinedevelopment.skandiacup.repository.FTPcallback;

public class SplashScreenActivity  extends Activity {
        private static int SPLASH_TIME_OUT = 2000;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);

            DataManager.getInstanceSplashScreen().loadConfigFileFTP(new FTPcallback<Boolean>() {
                @Override
                public void successCallback(Boolean data) {
                    Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }

                @Override
                public void errorCallback() {
                    Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            });
        }
    }