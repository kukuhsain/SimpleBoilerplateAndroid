package com.kukuhsain.simple.boilerplate.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kukuhsain.simple.boilerplate.R;
import com.kukuhsain.simple.boilerplate.model.datamodel.Sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kukuh on 14/11/16.
 */

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {
    private List<Sample> mSamples;

    public MainAdapter() {
        mSamples = new ArrayList<>();
    }

    public void setSamples(List<Sample> samples) {
        mSamples = samples;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sample, parent, false);
        return new MainViewHolder(parent.getContext(), view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        holder.bind(mSamples.get(position));
    }

    @Override
    public int getItemCount() {
        return mSamples.size();
    }
}
