package com.kukuhsain.simple.boilerplate.model;

import com.kukuhsain.simple.boilerplate.model.datamodel.Sample;
import com.kukuhsain.simple.boilerplate.model.local.PreferencesHelper;
import com.kukuhsain.simple.boilerplate.model.local.RealmHelper;
import com.kukuhsain.simple.boilerplate.model.remote.RetrofitService;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by kukuh on 27/01/17.
 */

public class DataManager {

    private final RetrofitService mRetrofitService;
    private final PreferencesHelper mPreferencesHelper;
    private final RealmHelper mRealmHelper;

    @Inject
    public DataManager(RetrofitService retrofitService, PreferencesHelper preferencesHelper,
                       RealmHelper realmHelper) {
        mRetrofitService = retrofitService;
        mPreferencesHelper = preferencesHelper;
        mRealmHelper = realmHelper;
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
