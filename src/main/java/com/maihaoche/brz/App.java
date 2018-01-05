package com.maihaoche.brz;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.maihaoche.brz.cipher.DefaultCipherHelper;
import com.maihaoche.brz.coder.DefaultJsonHelper;
import com.maihaoche.brz.command.*;
import com.maihaoche.brz.network.DefaultHttpClient;
import com.maihaoche.brz.network.HttpClient;
import com.maihaoche.brz.result.AccessToken;
import com.maihaoche.brz.result.DownloadFile;
import com.maihaoche.brz.utils.Config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
            Gson gson = new Gson();


//          例子1，获取access token
            AccessToken accessToken = requestAccessToken(Config.CORP_ID, Config.CORP_KEY);
            System.out.println(accessToken);


            List<Order> orders = findOrder(accessToken.getToken(), "G20180104106309");
            System.out.println(gson.toJson(orders));

            for (Contract contract : orders.get(0).getContract()) {
                Map<String, String> uris = findContractUri(accessToken.getToken(), contract.getId(), contract.getMime());
                System.out.println(URLDecoder.decode(uris.get("uri"), "UTF-8"));
            }

//
//            List<String> carIds = Collections.singletonList("100571421");
//            List<Map<String, String>> waybill = findWaybill(accessToken.getToken(), carIds);
//            System.out.println(gson.toJson(waybill));
//
//            List<Map<String, String>> warehouse = findWarehouse(accessToken.getToken(), carIds);
//            System.out.println(gson.toJson(warehouse));

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

//            修改签章人
//            alterSigner(accessToken.getToken(), "重楼", "18058182593");
//
//            sendCaptcha(accessToken.getToken());


        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static AccessToken requestAccessToken(String corpId, String corpKey) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String requestAccessTokenUrl = String.format("%s/v1/corp/token?id=%s&key=%s", Config.DOMAIN, corpId, corpKey);
        AccessToken accessToken = HTTP_CLIENT.get(requestAccessTokenUrl, AccessToken.class);
        return accessToken;
    }

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

    private static List<Map<String, String>> findWaybill(String accessToken, List<String> carIds) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String notifyUrl = String.format("%s/v1/car/waybill", Config.DOMAIN);
        return HTTP_CLIENT.get(notifyUrl, carIds, new TypeToken<List<Map<String, Object>>>() {
        }.getType(), accessToken);
    }

    private static List<Map<String, String>> findWarehouse(String accessToken, List<String> carIds) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String notifyUrl = String.format("%s/v1/car/warehouse", Config.DOMAIN);
        return HTTP_CLIENT.get(notifyUrl, carIds, new TypeToken<List<Map<String, Object>>>() {
        }.getType(), accessToken);
    }

    private static void alterSigner(String accessToken, String name, String mobile) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String notifyUrl = String.format("%s/v1/signer", Config.DOMAIN);
        HTTP_CLIENT.post(notifyUrl, new AlterSignerCommand(mobile, name), accessToken);
    }

    private static void sendCaptcha(String accessToken) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String notifyUrl = String.format("%s/v1/captcha", Config.DOMAIN);
        HTTP_CLIENT.get(notifyUrl, accessToken);
    }

    private static void agree(String accessToken, String orderId) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String notifyUrl = String.format("%s/v1/agree", Config.DOMAIN);
        HTTP_CLIENT.put(notifyUrl, orderId, accessToken);
    }

    private static void reject(String accessToken, String orderId) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String notifyUrl = String.format("%s/v1/reject", Config.DOMAIN);
        HTTP_CLIENT.put(notifyUrl, orderId, accessToken);
    }

    private static Map<String, String> findContractUri(String accessToken, String contractId, String mime) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String notifyUrl = String.format("%s/v1/contract/uri", Config.DOMAIN);

        Map<String, String> uri = HTTP_CLIENT.get(notifyUrl, new FindContractUriCommand(contractId, mime), new TypeToken<Map<String, String>>() {
        }.getType(), accessToken);

        return uri;
    }

    private static void sign(String accessToken, String contractId, String captcha) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String notifyUrl = String.format("%s/v1/sign", Config.DOMAIN);
        HTTP_CLIENT.post(notifyUrl, new SignCommand(contractId, captcha), accessToken);
    }
}
