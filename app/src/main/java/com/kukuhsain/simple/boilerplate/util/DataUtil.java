package com.kukuhsain.simple.boilerplate.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * Created by kukuh on 27/01/17.
 */

public class DataUtil {
    public static <T> T getData(JsonObject jsonObject, Class<T> tClass) {
        String data = jsonObject.get("data").getAsJsonObject().toString();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return gson.fromJson(data, tClass);
    }
}
