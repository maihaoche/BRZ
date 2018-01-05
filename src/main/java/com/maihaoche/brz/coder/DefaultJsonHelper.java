package com.maihaoche.brz.coder;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by alex on 2017/10/22.
 */
public class DefaultJsonHelper implements JsonHelper {

    private final Gson gson;

    public DefaultJsonHelper() {
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING)
//                .setDateFormat("yyyy-MM-dd HH:mm")
//                .serializeNulls();
        this.gson = new Gson();//gsonBuilder.create();
    }

    public <T> String toJson(T o) {
        return gson.toJson(o);
    }

    public <T> T fromJson(String text, Class<T> clazz) {
        return gson.fromJson(text, clazz);
    }

    public <T> T fromJson(String text, Type type) {
        return gson.fromJson(text, type);
    }
}
