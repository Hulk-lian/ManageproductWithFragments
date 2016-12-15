package com.jtcode.manageproductfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen_Activity extends AppCompatActivity {
    // Spalsh Duration
    private static final long SPLASH_SCREEN_DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        /**
         * New Timer Task.
         */
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                // Launch the next Activity
                Intent mainIntent = new Intent().setClass(
                        SplashScreen_Activity.this, Login_Activity.class);
                startActivity(mainIntent);

                // And close the Splash
                finish();
            }
        };

        //Launch the Splash
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }
}