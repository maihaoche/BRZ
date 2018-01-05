package com.maihaoche.brz.network;

import com.maihaoche.brz.result.DownloadFile;

import java.io.IOException;
import java.lang.reflect.Type;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by alex on 2017/10/24.
 */
public interface HttpClient {
    DownloadFile download(String url, Object command, String accessToken) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException;

    void get(String url, String accessToken) throws IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException;

    <T> T get(String url, Class<T> returnType) throws IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException;

    <T> T get(String url, Object command, Class<T> returnType) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException;

    <T> T get(String url, Object command, Class<T> returnType, String accessToken) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException;

    <T> T get(String url, Object command, Type returnType, String accessToken) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException;

    void post(String url, Object command, String accessToken) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException;

    void put(String url, Object command, String accessToken) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException;
}
