package com.kukuhsain.simple.boilerplate.ui.signin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.kukuhsain.simple.boilerplate.R;
import com.kukuhsain.simple.boilerplate.ui.main.MainActivity;
import com.kukuhsain.simple.boilerplate.ui.signup.SignUpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kukuh on 01/01/17.
 */

public class SignInActivity extends AppCompatActivity {
    @BindView(R.id.et_email) EditText tvEmail;
    @BindView(R.id.et_password) EditText etPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_sign_in)
    public void signIn() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @OnClick(R.id.tv_sign_up)
    public void signUp() {
        startActivity(new Intent(this, SignUpActivity.class));
    }
}
