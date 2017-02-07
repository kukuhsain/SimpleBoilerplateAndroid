package com.kukuhsain.simple.boilerplate.injection.module;

import com.kukuhsain.simple.boilerplate.model.remote.RetrofitFactory;
import com.kukuhsain.simple.boilerplate.model.remote.RetrofitService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kukuh on 28/01/17.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    RetrofitService provideRetrofitService() {
        return RetrofitFactory.newInstance().create(RetrofitService.class);
    }
}
