package com.flightbooking.activies;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.flightbooking.R;

public class SplashActivity extends AppCompatActivity {
    Animation anim;
    LinearLayout splashImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashImage=(LinearLayout)findViewById(R.id.splashImage);
        final int ScreenDisplay = 3000;
        anim = AnimationUtils.loadAnimation(this, R.anim.initial_anim);
        anim.setDuration(3000);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                splashImage.setBackground(getResources().getDrawable(R.drawable.splashlogo));
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        splashImage.setAnimation(anim);
        splashImage.animate();
    }
}
