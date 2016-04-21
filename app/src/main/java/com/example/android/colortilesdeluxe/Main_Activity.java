package com.example.android.colortilesdeluxe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Random;
import java.util.RandomAccess;
import java.util.Timer;
import java.util.TimerTask;


public class Main_Activity extends AppCompatActivity {
    private int j=0,c,index;
    private Random rng;
    private Timer t;
    private TextView topleft,topright,botright,botleft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        loadingOn();
    }
    public void loadingOn() {
        rng = new Random();
        t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(j==12) {
                            stopLoad();
                            }
                            c = Color.argb(255,rng.nextInt(256),rng.nextInt(256),rng.nextInt(256));
                            j++;
                            topleft = (TextView) findViewById(R.id.topleft);
                            topright = (TextView) findViewById(R.id.topright);
                            botleft = (TextView) findViewById(R.id.bottomleft);
                            botright = (TextView) findViewById(R.id.bottomright);
                            TextView loading = (TextView) findViewById(R.id.loading);
                            index = j % 4;
                            if (index == 0) {
                                topleft.setBackgroundColor(c);
                                loading.setText("LOADING");
                            } else if (index == 1) {
                                topright.setBackgroundColor(c);
                                loading.setText("LOADING.");
                            } else if (index == 2) {
                                botright.setBackgroundColor(c);
                                loading.setText("LOADING..");
                            } else {
                                botleft.setBackgroundColor(c);
                                loading.setText("LOADING...");
                            }
                        }
                    });
                }
        },500, 300);
}
    public void stopLoad(){
        if (t!= null) {
            t.cancel();
            t = null;
        }
        Intent intent = new Intent(this,Home_Screen.class);
        startActivity(intent);
    }

}



