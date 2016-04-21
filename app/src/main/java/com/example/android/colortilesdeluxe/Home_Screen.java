package com.example.android.colortilesdeluxe;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Home_Screen extends AppCompatActivity {
    private int j=0,color,count;
    private Timer time;
    private Random rnd;
    private TextView c,o,l,o2,r,t,i,l2,e,s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_home__screen);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        changeColors();

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
     Intent intent = new Intent(this,Main_Menu.class);
        startActivity(intent);
        return true;
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
    public void changeColors(){
        time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rnd = new Random();
                        color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                        count=j%10;
                        if(count==0){
                            c = (TextView)findViewById(R.id.C);
                            c.setBackgroundColor(color);
                        }
                        else if(count==1){
                            o = (TextView) findViewById(R.id.O);
                            o.setBackgroundColor(color);
                        }
                        else if(count==2){
                            l = (TextView) findViewById(R.id.L);
                            l.setBackgroundColor(color);
                        }
                        else if(count==3){
                            o2 = (TextView) findViewById(R.id.O2);
                            o2.setBackgroundColor(color);
                        }
                        else if(count==4){
                            r = (TextView) findViewById(R.id.R);
                            r.setBackgroundColor(color);
                        }
                        else if(count==5){
                            t = (TextView) findViewById(R.id.T);
                            t.setBackgroundColor(color);
                        }
                        else if(count==6){
                            i = (TextView) findViewById(R.id.I);
                            i.setBackgroundColor(color);
                        }
                        else if(count==7){
                            l2 = (TextView) findViewById(R.id.L2);
                            l2.setBackgroundColor(color);
                        }
                        else if(count==8){
                            e = (TextView) findViewById(R.id.E);
                            e.setBackgroundColor(color);
                        }
                        else{
                            s = (TextView) findViewById(R.id.S);
                            s.setBackgroundColor(color);
                        }
                        j++;
                    }
                });
            }
        },500,200);
    }


}
