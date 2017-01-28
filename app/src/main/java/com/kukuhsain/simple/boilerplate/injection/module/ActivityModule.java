package com.kukuhsain.simple.boilerplate.injection.module;

import android.app.Activity;

import com.kukuhsain.simple.boilerplate.injection.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kukuh on 28/01/17.
 */

@Module
public class ActivityModule {
    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @PerActivity
    public Activity provideActivity() {
        return mActivity;
    }
}
