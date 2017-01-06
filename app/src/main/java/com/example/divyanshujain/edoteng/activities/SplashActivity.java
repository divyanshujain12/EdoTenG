package com.example.divyanshujain.edoteng.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.divyanshujain.edoteng.Constants.Constants;
import com.example.divyanshujain.edoteng.R;
import com.example.divyanshujain.edoteng.Utils.MySharedPereference;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SplashActivity extends AppCompatActivity implements AnimationListener{

    @InjectView(R.id.appLogoIV)
    ImageView appLogoIV;
    Animation logoAnim = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        ButterKnife.inject(this);

        logoAnim = AnimationUtils.loadAnimation(this,R.anim.fab_in);
        logoAnim.setAnimationListener(this);
        appLogoIV.startAnimation(logoAnim);



    }

    private void startHandler() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = null;
                if (!MySharedPereference.getInstance().getBoolean(SplashActivity.this, Constants.IS_LOGGED_IN)) {
                    i = new Intent(SplashActivity.this, LoginActivity.class);

                } else {
                    i = new Intent(SplashActivity.this, HomeActivity.class);
                }
                startActivity(i);
                finish();
            }
        }, 2000);
    }

    Handler handler = new Handler();

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        startHandler();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
