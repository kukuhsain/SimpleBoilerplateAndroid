package com.kukuhsain.simple.boilerplate.model.remote;

import com.kukuhsain.simple.boilerplate.model.datamodel.Sample;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by kukuh on 15/10/16.
 */

public interface RetrofitService {

    String BASE_URL = "http://simple-boilerplate.appspot.com";

    @GET("/api/v1/samples")
    Observable<List<Sample>> getSamples();
}
