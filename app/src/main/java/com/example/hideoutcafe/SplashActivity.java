package com.example.hideoutcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    Animation top,bottom,text;
ImageView bg,person,table;
//TextView txt;



    private TextAnimation textView;

    private static final long SPLASH_DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        top = AnimationUtils.loadAnimation(this,R.anim.top);
        bottom = AnimationUtils.loadAnimation(this,R.anim.bottom);
        text = AnimationUtils.loadAnimation(this,R.anim.text);


        bg = findViewById(R.id.bg);
        person = findViewById(R.id.person);
        table = findViewById(R.id.table);
        //txt = findViewById(R.id.text);

        textView = findViewById(R.id.textView);


        bg.setAnimation(top);
        person.setAnimation(bottom);
        table.setAnimation(bottom);
        //txt.setAnimation(text);




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                String textToAnimate = "Theist Cafe"; // Text to be animated
//                textView.animateText(textToAnimate); // Pass the text to animate
//                String textToAnimate = "Theist Cafe";
//                textView.setTextWithShake(textToAnimate);

            }
        }, 2000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DELAY);
    }
}