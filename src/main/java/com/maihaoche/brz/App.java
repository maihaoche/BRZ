package com.maihaoche.brz;

import com.google.gson.reflect.TypeToken;
import com.maihaoche.brz.cipher.DefaultCipherHelper;
import com.maihaoche.brz.coder.DefaultJsonHelper;
import com.maihaoche.brz.command.DownloadContractCommand;
import com.maihaoche.brz.command.Order;
import com.maihaoche.brz.command.SendMessageCommand;
import com.maihaoche.brz.network.DefaultHttpClient;
import com.maihaoche.brz.network.HttpClient;
import com.maihaoche.brz.result.AccessToken;
import com.maihaoche.brz.result.DownloadFile;
import com.maihaoche.brz.utils.Config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;

/**
 * 卖好车开放平台，例子
 */
public class App {

    private static final HttpClient HTTP_CLIENT = new DefaultHttpClient(
            new DefaultCipherHelper(Config.PLATFORM_PUBLIC_KEY, Config.CORP_PRIVATE_KEY),
            new DefaultJsonHelper()
    );

    public static void main(String[] args) throws UnsupportedEncodingException {
        try {

//          例子1，获取access token
            AccessToken accessToken = requestAccessToken(Config.CORP_ID, Config.CORP_KEY);
            System.out.println(accessToken);

            List<Order> order = findOrder(accessToken.getToken(), "G20180104106309");
            System.out.println(order);

////          例子2、放款通知卖好车
//            notify(accessToken.getToken(), new SendMessageCommand<List<String>>(UUID.randomUUID().toString(), "lent", Collections.<String>emptyList()));
//
////          例子3、还款通知卖好车
//            notify(accessToken.getToken(), new SendMessageCommand<List<String>>(UUID.randomUUID().toString(), "repaid", Collections.<String>emptyList()));
//
////          例子4 通知卖好车，合同签署
//            notify(accessToken.getToken(), new SendMessageCommand<List<Contract>>(UUID.randomUUID().toString(), "contract", Collections.singletonList(new Contract("", "", ""))));
//
//            //例子4、下载合同
//            DownloadFile downloadFile = downloadContract(accessToken.getToken(), new DownloadContractCommand("G20170807107513", "100632980607197184"));

//            OutputStream outputStream = new FileOutputStream(downloadFile.getName());
//            outputStream.write(downloadFile.getContent());
//            outputStream.close();

// TOD保存到本地/CDN
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    //
    private static AccessToken requestAccessToken(String corpId, String corpKey) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String requestAccessTokenUrl = String.format("%s/v1/corp/token?id=%s&key=%s", Config.DOMAIN, corpId, corpKey);
        AccessToken accessToken = HTTP_CLIENT.get(requestAccessTokenUrl, AccessToken.class);
        return accessToken;
    }

//    private static void notify(String accessToken, SendMessageCommand<List<String>> command) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        String notifyUrl = String.format("%s/v1/message", Config.DOMAIN);
//        HTTP_CLIENT.post(notifyUrl, new Sen, accessToken);
//    }

    private static void notify(String accessToken, SendMessageCommand<?> command) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String notifyUrl = String.format("%s/v1/message", Config.DOMAIN);
        HTTP_CLIENT.post(notifyUrl, command, accessToken);
    }

    private static DownloadFile downloadContract(String accessToken, DownloadContractCommand downloadContractCommand) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String notifyUrl = String.format("%s/v1/contract", Config.DOMAIN);
        return HTTP_CLIENT.download(notifyUrl, downloadContractCommand, accessToken);
    }

    private static List<Order> findOrder(String accessToken, String orderNo) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String notifyUrl = String.format("%s/v1/orders", Config.DOMAIN);
        return HTTP_CLIENT.get(notifyUrl, Collections.singletonList(orderNo), new TypeToken<List<Order>>() {
        }.getType(), accessToken);
    }
}
