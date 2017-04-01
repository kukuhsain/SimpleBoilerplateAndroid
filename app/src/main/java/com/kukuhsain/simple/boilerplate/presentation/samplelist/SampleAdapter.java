package com.kukuhsain.simple.boilerplate.presentation.samplelist;

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

public class SampleAdapter extends RecyclerView.Adapter<SampleViewHolder> {
    private List<Sample> mSamples;

    public SampleAdapter() {
        mSamples = new ArrayList<>();
    }

    public void setSamples(List<Sample> samples) {
        mSamples = samples;
    }

    @Override
    public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sample, parent, false);
        return new SampleViewHolder(parent.getContext(), view);
    }

    @Override
    public void onBindViewHolder(SampleViewHolder holder, int position) {
        holder.bind(mSamples.get(position));
    }

    @Override
    public int getItemCount() {
        return mSamples.size();
    }
}
