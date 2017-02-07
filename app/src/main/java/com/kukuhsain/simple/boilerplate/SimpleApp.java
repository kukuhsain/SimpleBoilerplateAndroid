package com.kukuhsain.simple.boilerplate;

import android.app.Application;
import android.content.Context;

import com.kukuhsain.simple.boilerplate.injection.component.ActivityComponent;
import com.kukuhsain.simple.boilerplate.injection.component.ApplicationComponent;
import com.kukuhsain.simple.boilerplate.injection.component.DaggerApplicationComponent;
import com.kukuhsain.simple.boilerplate.injection.module.ApplicationModule;
import com.kukuhsain.simple.boilerplate.injection.module.DataModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

/**
 * Created by kukuh on 08/10/16.
 */

public class SimpleApp extends Application {

    private ApplicationComponent mApplicationComponent;
    private ActivityComponent mActivityComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setupRealm();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        mApplicationComponent = createComponent();

        Timber.d("Application onCreate...");
    }

    public static SimpleApp get(Context context) {
        return (SimpleApp) context.getApplicationContext();
    }

    private void setupRealm() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public ApplicationComponent createComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .dataModule(new DataModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
