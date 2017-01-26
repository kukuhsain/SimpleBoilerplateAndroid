package com.kukuhsain.simple.boilerplate.ui.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

    private Sample sample;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Detail");

        String sampleString = getIntent().getStringExtra("sample");
        sample = new Gson().fromJson(sampleString, Sample.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        tvName.setText(sample.getName());
        tvDescription.setText(sample.getDescription());
    }
}
