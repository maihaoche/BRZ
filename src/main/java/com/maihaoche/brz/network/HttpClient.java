package com.maihaoche.brz.network;

import java.io.IOException;

/**
 * Created by alex on 2017/10/24.
 */
public interface HttpClient {
    <T> T get(String url, Object param, Class<T> responseClazz) throws IOException;

    <T> T post(String url, Object param, Class<T> clazz) throws IOException;
}
