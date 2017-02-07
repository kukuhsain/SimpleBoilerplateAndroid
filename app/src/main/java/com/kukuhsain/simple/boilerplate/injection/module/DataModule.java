package com.kukuhsain.simple.boilerplate.injection.module;

import android.content.Context;

import com.kukuhsain.simple.boilerplate.model.local.PreferencesHelper;
import com.kukuhsain.simple.boilerplate.model.local.RealmHelper;
import com.kukuhsain.simple.boilerplate.model.remote.RetrofitFactory;
import com.kukuhsain.simple.boilerplate.model.remote.RetrofitService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kukuh on 28/01/17.
 */

@Module
public class DataModule {

    @Provides
    @Singleton
    RetrofitService provideRetrofitService() {
        return RetrofitFactory.newInstance().create(RetrofitService.class);
    }

    @Provides
    @Singleton
    public PreferencesHelper providePreferencesHelper(Context context) {
        return new PreferencesHelper(context);
    }

    @Provides
    @Singleton
    public RealmHelper provideRealmHelper() {
        return new RealmHelper();
    }
}
