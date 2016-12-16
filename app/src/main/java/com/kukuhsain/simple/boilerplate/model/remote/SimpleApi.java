package com.kukuhsain.simple.boilerplate.model.remote;

import com.kukuhsain.simple.boilerplate.model.local.PreferencesHelper;
import com.kukuhsain.simple.boilerplate.model.pojo.Sample;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by kukuh on 15/10/16.
 */

public class SimpleApi {
    public static final String BASE_URL = "http://simple-tour.appspot.com";
    private static SimpleApi INSTANCE;
    private ApiEndpoint api;
    private static String accessToken;

    private SimpleApi() {
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();
        api = retrofit.create(ApiEndpoint.class);
    }

    public static SimpleApi getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SimpleApi();
        }
        accessToken = PreferencesHelper.getInstance().getAccessToken();
        return INSTANCE;
    }

    public Observable<List<Sample>> getDummySamples() {
        List<Sample> samples = new ArrayList<>();
        int total = 10;
        for (int i=0; i<total; i++) {
            samples.add(new Sample(i, "Sample title "+i, "Sample description, Lorem ipsum "+i));
        }
        return Observable.just(samples);
    }

    private interface ApiEndpoint {

    }
}
