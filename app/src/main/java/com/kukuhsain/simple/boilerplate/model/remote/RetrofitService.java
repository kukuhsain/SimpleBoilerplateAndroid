package com.kukuhsain.simple.boilerplate.model.remote;

import com.google.gson.JsonObject;
import com.kukuhsain.simple.boilerplate.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
