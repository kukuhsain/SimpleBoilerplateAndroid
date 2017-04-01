package com.kukuhsain.simple.boilerplate.presentation.samplelist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kukuhsain.simple.boilerplate.R;
import com.kukuhsain.simple.boilerplate.model.datamodel.Sample;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kukuh on 16/12/16.
 */

public class SampleViewHolder extends RecyclerView.ViewHolder {
    private SampleListActivity sampleListActivity;
    @BindView(R.id.tv_name) TextView tvName;
    @BindView(R.id.tv_description) TextView tvDescription;

    public SampleViewHolder(Context context, View itemView) {
        super(itemView);
        this.sampleListActivity = (SampleListActivity) context;
        ButterKnife.bind(this, itemView);
    }

    public void bind(Sample sample) {
        tvName.setText(sample.getName());
        tvDescription.setText(sample.getDescription());
        itemView.setOnClickListener(view -> {
            sampleListActivity.onItemClicked(sample);
        });
    }
}
