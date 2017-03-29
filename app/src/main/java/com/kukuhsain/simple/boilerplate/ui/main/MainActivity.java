package com.kukuhsain.simple.boilerplate.ui.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import com.kukuhsain.simple.boilerplate.model.DataManager;
import com.kukuhsain.simple.boilerplate.model.datamodel.Sample;
import com.kukuhsain.simple.boilerplate.ui.detail.DetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kukuh on 14/11/16.
 */

public class MainActivity extends AppCompatActivity implements MainMvpView {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rv_samples) RecyclerView rvSamples;

    private MainPresenter presenter;
    private MainAdapter adapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter(DataManager.getInstance());
        presenter.attachView(this);

        initActionBar();
        initRv();
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Sample List");
        }
    }

    private void initRv() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvSamples.setLayoutManager(layoutManager);
        adapter = new MainAdapter();
        rvSamples.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getSamples();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    public void onItemClicked(Sample sample) {
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
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showSamples(List<Sample> samples) {
        adapter.setSamples(samples);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showEmptySample() {

    }

    @Override
    public void showLoading() {
        if (progressDialog != null) {
            progressDialog.show();
        } else {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
        }
    }

    @Override
    public void dismissLoading() {
        if (progressDialog != null) progressDialog.dismiss();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
