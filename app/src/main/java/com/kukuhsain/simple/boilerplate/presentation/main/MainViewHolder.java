package com.kukuhsain.simple.boilerplate.presentation.main;

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

public class MainViewHolder extends RecyclerView.ViewHolder {
    private MainActivity mainActivity;
    @BindView(R.id.tv_name) TextView tvName;
    @BindView(R.id.tv_description) TextView tvDescription;

    public MainViewHolder(Context context, View itemView) {
        super(itemView);
        this.mainActivity = (MainActivity) context;
        ButterKnife.bind(this, itemView);
    }

    public void bind(Sample sample) {
        tvName.setText(sample.getName());
        tvDescription.setText(sample.getDescription());
        itemView.setOnClickListener(view -> {
            mainActivity.onItemClicked(sample);
        });
    }
}
