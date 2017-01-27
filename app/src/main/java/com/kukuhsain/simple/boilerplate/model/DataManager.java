package com.kukuhsain.simple.boilerplate.model;

import com.kukuhsain.simple.boilerplate.model.datamodel.Sample;
import com.kukuhsain.simple.boilerplate.model.local.PreferencesHelper;
import com.kukuhsain.simple.boilerplate.model.local.RealmHelper;
import com.kukuhsain.simple.boilerplate.model.remote.RetrofitService;

import java.util.List;

import rx.Observable;

/**
 * Created by kukuh on 27/01/17.
 */

public class DataManager {

    private final RetrofitService mRetrofitService;
    private final PreferencesHelper mPreferencesHelper;
    private final RealmHelper mRealmHelper;

    public DataManager(RetrofitService mRetrofitService, PreferencesHelper mPreferencesHelper,
                       RealmHelper mRealmHelper) {
        this.mRetrofitService = mRetrofitService;
        this.mPreferencesHelper = mPreferencesHelper;
        this.mRealmHelper = mRealmHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public RealmHelper getRealmHelper() {
        return mRealmHelper;
    }

    public Observable<List<Sample>> getSamples() {
        return mRetrofitService.getSamples();
    }
}
