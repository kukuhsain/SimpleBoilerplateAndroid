package com.kukuhsain.simple.boilerplate.injection.module;

import android.content.Context;

import com.kukuhsain.simple.boilerplate.SimpleApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kukuh on 28/01/17.
 */

@Module
public class ApplicationModule {

    private final SimpleApp mApplication;

    public ApplicationModule(SimpleApp application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return mApplication;
    }
}
