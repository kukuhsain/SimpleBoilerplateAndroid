package com.kukuhsain.simple.boilerplate.ui.main;

import com.kukuhsain.simple.boilerplate.model.DataManager;
import com.kukuhsain.simple.boilerplate.model.datamodel.Sample;
import com.kukuhsain.simple.boilerplate.model.remote.RetrofitService;
import com.kukuhsain.simple.boilerplate.ui.base.BasePresenter;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kukuh on 16/12/16.
 */

public class MainPresenter extends BasePresenter<MainMvpView> {
    private final DataManager mDataManager;

    public MainPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    public void getSamples() {
        checkViewAttached();
        getMvpView().showLoading();
        mDataManager.getDummySamples()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(samples -> {
                    mDataManager.getRealmHelper().saveOrUpdateSamples(samples);
                    if (isViewAttached()) {
                        getMvpView().showSamples(samples);
                        getMvpView().dismissLoading();
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                    if (isViewAttached()) {
                        getMvpView().showToast(throwable.getMessage());
                        getMvpView().dismissLoading();
                    }
                });
    }
}
