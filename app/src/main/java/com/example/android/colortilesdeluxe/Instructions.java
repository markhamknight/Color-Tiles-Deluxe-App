package com.example.android.colortilesdeluxe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

public class Instructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_instructions);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }
    @Override
    public void onBackPressed() {
        Intent BackpressedIntent = new Intent();
        BackpressedIntent .setClass(getApplicationContext(),Main_Menu.class);
        BackpressedIntent .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(BackpressedIntent);
        finish();
    }

    public void playNow(View view){
        Intent intent = new Intent(this,Main_Menu.class);
        startActivity(intent);
    }
}
