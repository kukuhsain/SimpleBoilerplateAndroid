package com.kukuhsain.simple.boilerplate.injection.module;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kukuhsain.simple.boilerplate.BuildConfig;
import com.kukuhsain.simple.boilerplate.model.remote.RetrofitService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by kukuh on 28/01/17.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    Cache provideOkHttpCache(Context context) {
        int cacheSize = 15 * 1024 * 1024; // 15 MB
        return new Cache(context.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG ?
                        HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache, HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .addNetworkInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Provides
    @Singleton
    RetrofitService provideRetrofitService(OkHttpClient okHttpClient, Gson gson) {
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory
                .createWithScheduler(Schedulers.io());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxAdapter)
                .build();
        return retrofit.create(RetrofitService.class);
    }
}
