package com.kukuhsain.simple.boilerplate.injection.component;

import android.content.Context;

import com.kukuhsain.simple.boilerplate.injection.module.ApplicationModule;
import com.kukuhsain.simple.boilerplate.injection.module.DataModule;
import com.kukuhsain.simple.boilerplate.model.DataManager;
import com.kukuhsain.simple.boilerplate.model.local.PreferencesHelper;
import com.kukuhsain.simple.boilerplate.model.local.RealmHelper;
import com.kukuhsain.simple.boilerplate.model.remote.RetrofitService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by kukuh on 28/01/17.
 */

@Singleton
@Component(modules = {ApplicationModule.class, DataModule.class})
public interface ApplicationComponent {

    Context context();
    PreferencesHelper preferencesHelper();
    RealmHelper realmHelper();
    RetrofitService retrofitService();
    DataManager dataManager();
}
