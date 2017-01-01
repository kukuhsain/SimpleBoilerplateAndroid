package com.kukuhsain.simple.boilerplate.ui.signup;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kukuh on 31/12/16.
 */

public class SignUpActivity extends AppCompatActivity {
    @BindView(R.id.et_name) EditText etName;
    @BindView(R.id.et_email) EditText etEmail;
    @BindView(R.id.et_password) EditText etPassword;
    @BindView(R.id.et_phone_number) EditText etPhoneNumber;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_sign_up)
    public void signUp() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();
        String phoneNumber = etPhoneNumber.getText().toString().trim();
        if (name.isEmpty()) {
            etName.setError("Please insert your name!");
            etName.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please insert a valid email address!");
            etEmail.requestFocus();
        } else if (password.length() < 6) {
            etPassword.setError("Minimum password input is 6 characters!");
            etPassword.requestFocus();
        } else if (!Patterns.PHONE.matcher(phoneNumber).matches()) {
            etPhoneNumber.setError("Please insert a valid phone number!");
        } else {
            showLoading();
            SimpleApi.getInstance()
                    .signUp(name, email, password, phoneNumber)
                    .subscribe(accessToken -> {
                        Intent intent = new Intent(this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        runOnUiThread(() -> {
                            Toast.makeText(this, "You have successfully signed up for a new account", Toast.LENGTH_SHORT).show();
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
