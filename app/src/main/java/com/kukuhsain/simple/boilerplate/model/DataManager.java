package com.kukuhsain.simple.boilerplate.model;

import com.kukuhsain.simple.boilerplate.model.datamodel.Sample;
import com.kukuhsain.simple.boilerplate.model.local.PreferencesHelper;
import com.kukuhsain.simple.boilerplate.model.local.RealmHelper;
import com.kukuhsain.simple.boilerplate.model.remote.RetrofitService;

import java.util.ArrayList;
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

    public Observable<List<Sample>> getDummySamples() {
        List<Sample> samples = new ArrayList<>();
        int total = 10;
        for (int i=0; i<total; i++) {
            samples.add(new Sample(i, "Sample title "+i, "Sample description, Lorem ipsum "+i));
        }
        return Observable.just(samples);
    }

    public Observable<String> signUp(String name, String email, String password, String phoneNumber) {
        return mRetrofitService.signUp(name, email, password, phoneNumber)
                .map(jsonObject -> jsonObject.get("data").getAsJsonObject()
                        .get("access_token").getAsString());
    }

    public Observable<String> signIn(String email, String password) {
        return mRetrofitService.signIn(email, password)
                .map(jsonObject -> jsonObject.get("data").getAsJsonObject()
                        .get("access_token").getAsString());
    }
}
