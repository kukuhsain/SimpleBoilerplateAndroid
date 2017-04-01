package com.kukuhsain.simple.boilerplate.presentation.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.kukuhsain.simple.boilerplate.R;
import com.kukuhsain.simple.boilerplate.model.datamodel.Sample;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kukuh on 16/12/16.
 */

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tv_name) TextView tvName;
    @BindView(R.id.tv_description) TextView tvDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        initActionBar();
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Detail");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
    }

    private void initView() {
        String sampleString = getIntent().getStringExtra("sample");
        Sample sample = new Gson().fromJson(sampleString, Sample.class);
        tvName.setText(sample.getName());
        tvDescription.setText(sample.getDescription());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
