package com.kukuhsain.simple.boilerplate.model.remote;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.kukuhsain.simple.boilerplate.model.local.PreferencesHelper;
import com.kukuhsain.simple.boilerplate.model.pojo.Sample;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by kukuh on 15/10/16.
 */

public class SimpleApi {
    public static final String BASE_URL = "http://simple-boilerplate.appspot.com";
    private static SimpleApi INSTANCE;
    private ApiEndpoint api;
    private static String accessToken;

    private SimpleApi() {
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
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

    private <T> T getData(JsonObject jsonObject, Class<T> tClass) {
        String data = jsonObject.get("data").getAsJsonObject().toString();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return gson.fromJson(data, tClass);
    }

    public Observable<String> signUp(String name, String email, String password, String phoneNumber) {
        return api.signUp(name, email, password, phoneNumber)
                .map(jsonObject -> jsonObject.get("data").getAsJsonObject()
                        .get("access_token").getAsString());
    }

    public Observable<String> signIn(String email, String password) {
        return api.signIn(email, password)
                .map(jsonObject -> jsonObject.get("data").getAsJsonObject()
                        .get("access_token").getAsString());
    }

    private interface ApiEndpoint {
        @FormUrlEncoded
        @POST("/api/v1/auth/register")
        Observable<JsonObject> signUp(@Field("name") String name,
                                      @Field("email") String email,
                                      @Field("password") String password,
                                      @Field("phone") String phoneNumber);

        @FormUrlEncoded
        @POST("/api/v1/auth/login")
        Observable<JsonObject> signIn(@Field("email") String email,
                                      @Field("password") String password);
    }
}
