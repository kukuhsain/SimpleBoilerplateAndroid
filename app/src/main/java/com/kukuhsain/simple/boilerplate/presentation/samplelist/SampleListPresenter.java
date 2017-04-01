package com.kukuhsain.simple.boilerplate.presentation.samplelist;

import com.kukuhsain.simple.boilerplate.model.DataManager;
import com.kukuhsain.simple.boilerplate.model.datamodel.Sample;
import com.kukuhsain.simple.boilerplate.presentation.base.BasePresenter;
import com.kukuhsain.simple.boilerplate.util.RxUtil;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by kukuh on 16/12/16.
 */

public class SampleListPresenter extends BasePresenter<SampleListMvpView> {
    private final DataManager mDataManager;
    private Subscription mGetSamplesSubscription;

    public SampleListPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    public void getSamples() {
        checkViewAttached();
        RxUtil.unsubscribe(mGetSamplesSubscription);
        getMvpView().showLoading();
        List<Sample> savedSamples = mDataManager.getRealmHelper().getAllSamples();
        getMvpView().showSamples(savedSamples);
        mGetSamplesSubscription = mDataManager.getSamples()
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

    @Override
    public void detachView() {
        super.detachView();
        RxUtil.unsubscribe(mGetSamplesSubscription);
    }
}
