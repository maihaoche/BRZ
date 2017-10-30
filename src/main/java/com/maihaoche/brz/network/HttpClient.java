package com.maihaoche.brz.network;

import com.maihaoche.brz.result.DownloadFile;

import java.io.IOException;

/**
 * Created by alex on 2017/10/24.
 */
public interface HttpClient {
    DownloadFile download(String url, Object command, String accessToken) throws IOException;

    <T> T get(String url, Class<T> returnType) throws IOException;

    <T> T get(String url, Object command, Class<T> returnType) throws IOException;

    <T> T get(String url, Object command, Class<T> returnType, String accessToken) throws IOException;

    void post(String url, Object command, String accessToken) throws IOException;
}
