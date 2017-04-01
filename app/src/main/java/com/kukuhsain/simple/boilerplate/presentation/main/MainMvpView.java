package com.kukuhsain.simple.boilerplate.presentation.main;

import com.kukuhsain.simple.boilerplate.model.datamodel.Sample;
import com.kukuhsain.simple.boilerplate.presentation.base.MvpView;

import java.util.List;

/**
 * Created by kukuh on 27/01/17.
 */

public interface MainMvpView extends MvpView {

    void showSamples(List<Sample> samples);

    void showEmptySample();

    void showLoading();

    void dismissLoading();

    void showToast(String message);
}
