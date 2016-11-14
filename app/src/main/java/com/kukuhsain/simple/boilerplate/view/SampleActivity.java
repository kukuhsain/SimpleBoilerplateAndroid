package com.kukuhsain.simple.boilerplate.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import com.kukuhsain.simple.boilerplate.R;
import com.kukuhsain.simple.boilerplate.model.pojo.Sample;
import com.kukuhsain.simple.boilerplate.view.adapter.SampleAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kukuh on 14/11/16.
 */

public class SampleActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rv_samples) RecyclerView rvSamples;

    private ActionBar actionBar;
    private RecyclerView.LayoutManager layoutManager;
    private SampleAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Sample Activity");

        layoutManager = new LinearLayoutManager(this);
        rvSamples.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter = new SampleAdapter(getDummySamples());
        rvSamples.setAdapter(adapter);
    }

    private List<Sample> getDummySamples() {
        List<Sample> samples = new ArrayList<>();
        int total = 10;
        for (int i=0; i<total; i++) {
            samples.add(new Sample(i, "Sample title "+i, "Sample description, Lorem ipsum "+i));
        }
        return samples;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_sample_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
