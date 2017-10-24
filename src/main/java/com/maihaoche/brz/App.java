package com.maihaoche.brz;

import com.maihaoche.brz.cipher.DefaultCipherHelper;
import com.maihaoche.brz.coder.DefaultJsonHelper;
import com.maihaoche.brz.network.DefaultHttpClient;
import com.maihaoche.brz.network.HttpClient;
import com.maihaoche.brz.param.RequestAccessToken;
import com.maihaoche.brz.param.ResponseAccessToken;
import com.maihaoche.brz.utils.Config;

import java.io.IOException;
import java.util.UUID;

/**
 * 卖好车开放平台，例子
 */
public class App {

    private static final HttpClient HTTP_CLIENT = new DefaultHttpClient(
            new DefaultCipherHelper(Config.PLATFORM_PUBLIC_KEY, Config.CORP_PRIVATE_KEY),
            new DefaultJsonHelper()
    );

    public static void main(String[] args) {
        try {
            //例子1，获取access token
            requestAccessToken(Config.CORP_ID, Config.CORP_KEY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String requestAccessToken(String corpId, String corpKey) throws IOException {
        RequestAccessToken requestAccessToken = new RequestAccessToken(corpId, corpKey, UUID.randomUUID().toString());
        String requestAccessTokenUrl = String.format("%s/accessToken", Config.DOMAIN);
        ResponseAccessToken responseAccessToken = HTTP_CLIENT.get(requestAccessTokenUrl, requestAccessToken, ResponseAccessToken.class);
        if (responseAccessToken.success()) {
            return responseAccessToken.getData();
        } else {
            throw new RuntimeException(responseAccessToken.getMessage());
        }
    }
}
