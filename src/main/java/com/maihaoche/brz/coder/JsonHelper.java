package com.maihaoche.brz.coder;

/**
 * Created by alex on 2017/10/22.
 */
public interface JsonHelper {

    <T> String toJson(T o);

    <T> T fromJson(String text, Class<T> clazz);
}
