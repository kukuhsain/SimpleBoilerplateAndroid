package com.kukuhsain.simple.boilerplate.model.remote;

import com.kukuhsain.simple.boilerplate.BuildConfig;
import com.kukuhsain.simple.boilerplate.model.datamodel.Sample;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by kukuh on 15/10/16.
 */

public interface RetrofitService {

    String BASE_URL = "http://simple-boilerplate.appspot.com";

    class Creator {
        public static RetrofitService newInstance() {
            RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
                    .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(loggingInterceptor)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(rxAdapter)
                    .build();
            return retrofit.create(RetrofitService.class);
        }
    }

    @GET("/api/v1/samples")
    Observable<List<Sample>> getSamples();
}
