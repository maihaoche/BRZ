package com.maihaoche.brz.coder;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by alex on 2017/10/22.
 */
public class DefaultJsonHelper implements JsonHelper {

    private final Gson gson = new Gson();

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
