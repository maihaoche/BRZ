package com.maihaoche.brz;

import com.maihaoche.brz.cipher.DefaultCipherHelper;
import com.maihaoche.brz.coder.DefaultJsonHelper;
import com.maihaoche.brz.network.DefaultHttpClient;
import com.maihaoche.brz.network.HttpClient;
import com.maihaoche.brz.command.*;
import com.maihaoche.brz.result.AccessToken;
import com.maihaoche.brz.result.DownloadFile;
import com.maihaoche.brz.utils.Config;

import java.io.IOException;
import java.util.Collections;
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
            AccessToken accessToken = requestAccessToken(Config.CORP_ID, Config.CORP_KEY);

            //例子2、放款通知卖好车
            notify(accessToken.getToken(), new SendMessageCommand(UUID.randomUUID().toString(), "lent", Collections.<String>emptyList()));

            //例子3、还款通知卖好车
            notify(accessToken.getToken(), new SendMessageCommand(UUID.randomUUID().toString(), "repaid", Collections.<String>emptyList()));

            //例子4、下载合同
            DownloadFile file = downloadContract(accessToken.getToken(), new DownloadContractCommand("1", "1"));
            //TODO 保存到本地/CDN
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static AccessToken requestAccessToken(String corpId, String corpKey) throws IOException {
        RequestAccessTokenCommand requestAccessTokenCommand = new RequestAccessTokenCommand(corpId, corpKey);
        String requestAccessTokenUrl = String.format("%s/accessToken", Config.DOMAIN);
        AccessToken accessToken = HTTP_CLIENT.get(requestAccessTokenUrl, requestAccessTokenCommand, AccessToken.class);
        return accessToken;
    }

    private static void notify(String accessToken, SendMessageCommand sendMessageCommand) throws IOException {
        String notifyUrl = String.format("%s/message", Config.DOMAIN);
        HTTP_CLIENT.post(notifyUrl, sendMessageCommand, accessToken);
    }

    private static DownloadFile downloadContract(String accessToken, DownloadContractCommand downloadContractCommand) throws IOException {
        String notifyUrl = String.format("%s/contract", Config.DOMAIN);
        return HTTP_CLIENT.download(notifyUrl, downloadContractCommand, accessToken);
    }
}
