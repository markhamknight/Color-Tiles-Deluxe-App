package com.example.android.colortilesdeluxe;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class Highscore extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,Main_Menu.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_highscore);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        int value=0;
        SharedPreferences prefs = this.getSharedPreferences("highscore", Context.MODE_PRIVATE);
        int score = prefs.getInt("high", 0); //0 is the default value
        TextView currentScore = (TextView)findViewById(R.id.currentscore);
        TextView highScore = (TextView)findViewById(R.id.highscore);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            value = extras.getInt("currscore");
            if(score==0){
                score=value;
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("high", score);
                editor.commit();
            }
            else if(value>score){
                score=value;
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("high", score);
                editor.commit();
            }
        }
        currentScore.setText(""+value);
        highScore.setText(""+score);

    }

    public void tryAgain(View view){
        Intent intent = new Intent(this,Game_Proper.class);
        startActivity(intent);
    }

    public void goHome(View view){
        Intent intent = new Intent(this,Main_Menu.class);
        startActivity(intent);
    }

}
