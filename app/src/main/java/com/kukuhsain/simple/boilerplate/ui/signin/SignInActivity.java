package com.kukuhsain.simple.boilerplate.ui.signin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import com.kukuhsain.simple.boilerplate.R;
import com.kukuhsain.simple.boilerplate.model.remote.SimpleApi;
import com.kukuhsain.simple.boilerplate.ui.main.MainActivity;
import com.kukuhsain.simple.boilerplate.ui.signup.SignUpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kukuh on 01/01/17.
 */

public class SignInActivity extends AppCompatActivity {
    @BindView(R.id.et_email) EditText etEmail;
    @BindView(R.id.et_password) EditText etPassword;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_sign_in)
    public void signIn() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please insert a valid email address!");
            etEmail.requestFocus();
        } else if (password.length() < 6) {
            etPassword.setError("Minimum password input is 6 characters!");
            etPassword.requestFocus();
        } else {
            showLoading();
            SimpleApi.getInstance()
                    .signIn(email, password)
                    .subscribe(accessToken -> {
                        Intent intent = new Intent(this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        runOnUiThread(() -> {
                            Toast.makeText(this, "You have successfully signed in", Toast.LENGTH_SHORT).show();
                            dismissLoading();
                        });
                        startActivity(intent);
                    }, throwable -> {
                        throwable.printStackTrace();
                        runOnUiThread(() -> {
                            Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            dismissLoading();
                        });
                    });
        }
    }

    @OnClick(R.id.tv_sign_up)
    public void signUp() {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    private void showLoading() {
        if (progressDialog != null) {
            progressDialog.show();
        } else {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
        }
    }

    private void dismissLoading() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
