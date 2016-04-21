package com.example.android.colortilesdeluxe;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game_Proper extends AppCompatActivity {
    private int generatedColor[] = new int[9];
    private int colorAr[] = new int[2];
    private int inputedColor[] = new int[9];
    private int clicks[] = {0,0,0,0,0,0,0,0,0};
    private int txtView1,txtView2,txtView3,txtView4,txtView5,txtView6,txtView7,txtView8,txtView9;
    private int memtime = 3, inputtime = 3,score=0,i,memInterval=1000;
    private int color1 = Color.argb(255, 255, 252, 78);
    private int color2 = Color.argb(255, 164, 39, 255);
    Timer t;
    Random rng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        memtime = 4;
        inputtime = 4;
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_game__proper);
        ActionBar actionBar = getSupportActionBar();
        txtView1 = findViewById(R.id.textView0).getId();
        txtView2 = findViewById(R.id.textView1).getId();
        txtView3 = findViewById(R.id.textView2).getId();
        txtView4 = findViewById(R.id.textView3).getId();
        txtView5 = findViewById(R.id.textView4).getId();
        txtView6 = findViewById(R.id.textView5).getId();
        txtView7 = findViewById(R.id.textView6).getId();
        txtView8 = findViewById(R.id.textView7).getId();
        txtView9 = findViewById(R.id.textView8).getId();
        actionBar.hide();
        memTime();
    }
    @Override
    public void onBackPressed() {
        if (t!= null) {
            t.cancel();
            t = null;
        }
        Intent BackpressedIntent = new Intent();
        BackpressedIntent .setClass(getApplicationContext(),Main_Menu.class);
        BackpressedIntent .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(BackpressedIntent);
        finish();
    }
    public void memTime() {
        final TextView timer = (TextView) findViewById(R.id.timer);
        final TextView currentscore = (TextView) findViewById(R.id.score);
        final TextView hint = (TextView) findViewById(R.id.tips);
        currentscore.setText("Score:"+score);
        hint.setText("Memorize the Pattern");
        hint.setTextSize(30);
        t = new Timer();
        notClickable();
        generateColors();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timer.setText("Timer:"+memtime);
                        memtime -= 1;
                        if(memtime<0){
                            memtime=4;
                            stopTimeAndClear();
                        }
                    }
                });
            }
        },500,memInterval);
    }

    public void inputTime() {
        final TextView timer = (TextView) findViewById(R.id.timer);
        final TextView hint = (TextView) findViewById(R.id.tips);
        hint.setText("Tap the tiles to change its color");
        hint.setTextSize(22);
        t = new Timer();
        clickable();
        jumbleColors();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timer.setText("Timer:"+inputtime);
                        inputtime -= 1;
                        if(inputtime<0){
                            inputtime=4;
                            stopTimeAndMem();
                        }
                    }
                });
            }
        },0,1000);
    }

    public void clickable(){
        for (i = 0; i < 9; i++) {
            if (i == 0) {
                TextView textView = (TextView) findViewById(R.id.textView0);
                textView.setClickable(true);
            } else if (i == 1) {
                TextView textView = (TextView) findViewById(R.id.textView1);
                textView.setClickable(true);
            } else if (i == 2) {
                TextView textView = (TextView) findViewById(R.id.textView2);
                textView.setClickable(true);
            } else if (i == 3) {
                TextView textView = (TextView) findViewById(R.id.textView3);
                textView.setClickable(true);
            } else if (i == 4) {
                TextView textView = (TextView) findViewById(R.id.textView4);
                textView.setClickable(true);
            } else if (i == 5) {
                TextView textView = (TextView) findViewById(R.id.textView5);
                textView.setClickable(true);
            } else if (i == 6) {
                TextView textView = (TextView) findViewById(R.id.textView6);
                textView.setClickable(true);
            } else if (i == 7) {
                TextView textView = (TextView) findViewById(R.id.textView7);
                textView.setClickable(true);
            } else {
                TextView textView = (TextView) findViewById(R.id.textView8);
                textView.setClickable(true);
            }
        }
    }
    public void notClickable(){
        for (i = 0; i < 9; i++) {
            if (i == 0) {
                TextView textView = (TextView) findViewById(R.id.textView0);
                textView.setClickable(false);
            } else if (i == 1) {
                TextView textView = (TextView) findViewById(R.id.textView1);
                textView.setClickable(false);
            } else if (i == 2) {
                TextView textView = (TextView) findViewById(R.id.textView2);
                textView.setClickable(false);
            } else if (i == 3) {
                TextView textView = (TextView) findViewById(R.id.textView3);
                textView.setClickable(false);
            } else if (i == 4) {
                TextView textView = (TextView) findViewById(R.id.textView4);
                textView.setClickable(false);
            } else if (i == 5) {
                TextView textView = (TextView) findViewById(R.id.textView5);
                textView.setClickable(false);
            } else if (i == 6) {
                TextView textView = (TextView) findViewById(R.id.textView6);
                textView.setClickable(false);
            } else if (i == 7) {
                TextView textView = (TextView) findViewById(R.id.textView7);
                textView.setClickable(false);
            } else {
                TextView textView = (TextView) findViewById(R.id.textView8);
                textView.setClickable(false);
            }
        }
    }
    public void generateColors() {
        int i;
        int indexColor;
        colorAr[0] = color1;
        colorAr[1] = color2;
        rng = new Random();
        for (i = 0; i < 9; i++) {
            indexColor = rng.nextInt(2);
            generatedColor[i] = colorAr[indexColor];
        }
        for (i = 0; i < 9; i++) {
            if (i == 0) {
                TextView textView = (TextView) findViewById(R.id.textView0);
                textView.setBackgroundColor(generatedColor[i]);
            } else if (i == 1) {
                TextView textView = (TextView) findViewById(R.id.textView1);
                textView.setBackgroundColor(generatedColor[i]);
            } else if (i == 2) {
                TextView textView = (TextView) findViewById(R.id.textView2);
                textView.setBackgroundColor(generatedColor[i]);
            } else if (i == 3) {
                TextView textView = (TextView) findViewById(R.id.textView3);
                textView.setBackgroundColor(generatedColor[i]);
            } else if (i == 4) {
                TextView textView = (TextView) findViewById(R.id.textView4);
                textView.setBackgroundColor(generatedColor[i]);
            } else if (i == 5) {
                TextView textView = (TextView) findViewById(R.id.textView5);
                textView.setBackgroundColor(generatedColor[i]);
            } else if (i == 6) {
                TextView textView = (TextView) findViewById(R.id.textView6);
                textView.setBackgroundColor(generatedColor[i]);
            } else if (i == 7) {
                TextView textView = (TextView) findViewById(R.id.textView7);
                textView.setBackgroundColor(generatedColor[i]);
            } else {
                TextView textView = (TextView) findViewById(R.id.textView8);
                textView.setBackgroundColor(generatedColor[i]);
            }
        }
    }

    public void jumbleColors() {
        int i;
        int indexColor;
        int color;
        for (i = 0; i < 9; i++) {
            indexColor = rng.nextInt(2);
            if(indexColor==0){
                color=color1;
                clicks[i]=0;
            }
            else{
                color=color2;
                clicks[i]=1;
            }
            if (i == 0) {
                TextView textView = (TextView) findViewById(R.id.textView0);
                textView.setBackgroundColor(color);
                inputedColor[i]=color;
            } else if (i == 1) {
                TextView textView = (TextView) findViewById(R.id.textView1);
                textView.setBackgroundColor(color);
                inputedColor[i]=color;
            } else if (i == 2) {
                TextView textView = (TextView) findViewById(R.id.textView2);
                textView.setBackgroundColor(color);
                inputedColor[i]=color;
            } else if (i == 3) {
                TextView textView = (TextView) findViewById(R.id.textView3);
                textView.setBackgroundColor(color);
                inputedColor[i]=color;
            } else if (i == 4) {
                TextView textView = (TextView) findViewById(R.id.textView4);
                textView.setBackgroundColor(color);
                inputedColor[i]=color;
            } else if (i == 5) {
                TextView textView = (TextView) findViewById(R.id.textView5);
                textView.setBackgroundColor(color);
                inputedColor[i]=color;
            } else if (i == 6) {
                TextView textView = (TextView) findViewById(R.id.textView6);
                textView.setBackgroundColor(color);
                inputedColor[i]=color;
            } else if (i == 7) {
                TextView textView = (TextView) findViewById(R.id.textView7);
                textView.setBackgroundColor(color);
                inputedColor[i]=color;
            } else {
                TextView textView = (TextView) findViewById(R.id.textView8);
                textView.setBackgroundColor(color);
                inputedColor[i]=color;
            }
        }
    }

    public void changeColor(View view) {
        int thisView = view.getId();
        int noOfclicks;
        int color;
        TextView thisTxtView = (TextView) findViewById(thisView);
        if(thisView==txtView1) {
            noOfclicks = (clicks[0]++) % 2;
            if (noOfclicks == 1) {
                color = color1;
                inputedColor[0] = color1;
            } else {
                color = color2;
                inputedColor[0] = color2;
            }
            thisTxtView.setBackgroundColor(color);
        }
        else if(thisView==txtView2) {
            noOfclicks = (clicks[1]++) % 2;
            if (noOfclicks == 1) {
                color = color1;
                inputedColor[1] = color1;
            } else {
                color = color2;
                inputedColor[1] = color2;
            }
            thisTxtView.setBackgroundColor(color);
        }
        else if(thisView==txtView3) {
            noOfclicks = (clicks[2]++) % 2;
            if (noOfclicks == 1) {
                color = color1;
                inputedColor[2] = color1;
            } else {
                color = color2;
                inputedColor[2] = color2;
            }
            thisTxtView.setBackgroundColor(color);
        }
        else if(thisView==txtView4) {
            noOfclicks = (clicks[3]++) % 2;
            if (noOfclicks == 1) {
                color = color1;
                inputedColor[3] = color1;
            } else {
                color = color2;
                inputedColor[3] = color2;
            }
            thisTxtView.setBackgroundColor(color);
        }
        else if(thisView==txtView5) {
            noOfclicks = (clicks[4]++) % 2;
            if (noOfclicks == 1) {
                color = color1;
                inputedColor[4] = color1;
            } else {
                color = color2;
                inputedColor[4] = color2;
            }
            thisTxtView.setBackgroundColor(color);
        }
        else if(thisView==txtView6) {
            noOfclicks = (clicks[5]++) % 2;
            if (noOfclicks == 1) {
                color = color1;
                inputedColor[5] = color1;
            } else {
                color = color2;
                inputedColor[5] = color2;
            }
            thisTxtView.setBackgroundColor(color);
        }
        else if(thisView==txtView7) {
            noOfclicks = (clicks[6]++) % 2;
            if (noOfclicks == 1) {
                color = color1;
                inputedColor[6] = color1;
            } else {
                color = color2;
                inputedColor[6] = color2;
            }
            thisTxtView.setBackgroundColor(color);
        }
        else if(thisView==txtView8) {
            noOfclicks = (clicks[7]++) % 2;
            if (noOfclicks == 1) {
                color = color1;
                inputedColor[7] = color1;
            } else {
                color = color2;
                inputedColor[7] = color2;
            }
            thisTxtView.setBackgroundColor(color);
        }
        else{
            noOfclicks = (clicks[8]++) % 2;
            if (noOfclicks == 1) {
                color = color1;
                inputedColor[8] = color1;
            } else {
                color = color2;
                inputedColor[8] = color2;
            }
            thisTxtView.setBackgroundColor(color);
        }

    }

    public void checkInputColor(){
        rng = new Random();
        int corrects=0;
        int error=0;
        for(int i=0;i<9;i++){
            clicks[i]=0;
            if(generatedColor[i]==inputedColor[i]){
                corrects++;
            }
            else{
                error++;
            }
        }
        if(error==0){
            score+=corrects;
            if(score<200){
                memInterval=1000;
            }
            else if(score>200 && score<=350){
                memInterval=750;
            }
            else if(score>350 && score<=500){
                memInterval=500;
            }
            else{
                memInterval=250;
            }
            color1=Color.argb(255,rng.nextInt(256),rng.nextInt(256),rng.nextInt(256));
            color2=getComplementaryColor(color1);
            memTime();
        }
        else{
            score+=corrects;
            Intent intent = new Intent(this,Highscore.class);
            intent.putExtra("currscore",score);
            startActivity(intent);
        }
    }

    public static int getComplementaryColor(int colorToInvert) {
        float[] hsv = new float[3];
        Color.RGBToHSV(Color.red(colorToInvert), Color.green(colorToInvert),
                Color.blue(colorToInvert), hsv);
        hsv[0] = (hsv[0] + 180) % 360;
        return Color.HSVToColor(hsv);
    }

    public void stopTimeAndClear(){
        if (t!= null) {
            t.cancel();
            t = null;
        }
        inputTime();
    }

    public void stopTimeAndMem(){
        if (t!= null) {
            t.cancel();
            t = null;
        }
        checkInputColor();
    }
}
