package com.kukuhsain.simple.boilerplate.ui.main;

import com.kukuhsain.simple.boilerplate.model.datamodel.Sample;
import com.kukuhsain.simple.boilerplate.model.remote.SimpleApi;

import java.util.List;

import rx.schedulers.Schedulers;

/**
 * Created by kukuh on 16/12/16.
 */

public class MainPresenter {
    private MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }

    public void requestDummySamples() {
        SimpleApi.getInstance()
                .getDummySamples()
                .subscribeOn(Schedulers.io())
                .subscribe(samples -> {
                    view.onSuccess(samples);
                }, throwable -> {
                    view.onError(throwable.getMessage());
                    throwable.printStackTrace();
                });
    }

    public interface MainView {
        void onSuccess(List<Sample> samples);
        void onError(String message);
    }
}
