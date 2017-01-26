package com.kukuhsain.simple.boilerplate.ui.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kukuhsain.simple.boilerplate.R;
import com.kukuhsain.simple.boilerplate.model.local.PreferencesHelper;
import com.kukuhsain.simple.boilerplate.model.datamodel.Sample;
import com.kukuhsain.simple.boilerplate.ui.detail.DetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kukuh on 14/11/16.
 */

public class MainActivity extends AppCompatActivity implements MainPresenter.MainView {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rv_samples) RecyclerView rvSamples;

    private MainPresenter mainPresenter;
    private ProgressDialog progressDialog;
    private ActionBar actionBar;
    private RecyclerView.LayoutManager layoutManager;
    private MainAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Sample List");

        mainPresenter = new MainPresenter(this);

        layoutManager = new LinearLayoutManager(this);
        rvSamples.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        showLoading();
        mainPresenter.requestDummySamples();
    }

    public void onItemClicked(Sample sample) {
        Toast.makeText(this, sample.getName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("sample", new Gson().toJson(sample));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sign_out:
                PreferencesHelper.getInstance().clearData();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
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

    @Override
    public void onSuccess(List<Sample> samples) {
        runOnUiThread(() -> {
            adapter = new MainAdapter(samples);
            rvSamples.setAdapter(adapter);
            dismissLoading();
        });
    }

    @Override
    public void onError(String message) {
        runOnUiThread(() -> {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            dismissLoading();
        });
    }
}
