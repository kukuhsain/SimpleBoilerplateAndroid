package com.kukuhsain.simple.boilerplate.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kukuhsain.simple.boilerplate.R;
import com.kukuhsain.simple.boilerplate.ui.signin.SignInActivity;

/**
 * Created by kukuh on 01/01/17.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, SignInActivity.class));
            finish();
        }, 1500);
    }
}
