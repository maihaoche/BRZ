package com.maihaoche.brz.network;

import com.maihaoche.brz.cipher.CipherHelper;
import com.maihaoche.brz.cipher.DefaultCipherHelper;
import com.maihaoche.brz.coder.DefaultJsonHelper;
import com.maihaoche.brz.coder.JsonHelper;
import com.maihaoche.brz.utils.Config;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * Created by alex on 2017/10/24.
 */
public class DefaultHttpClient implements HttpClient {

    private final static int NO_CONTENT = 204;
    private final static String SIGNATURE = "X-SIGNATURE";

    private final CipherHelper cipherHelper;
    private final JsonHelper jsonHelper;

    public DefaultHttpClient() {
        this.cipherHelper = new DefaultCipherHelper(Config.PLATFORM_PUBLIC_KEY, Config.CORP_PRIVATE_KEY);
        this.jsonHelper = new DefaultJsonHelper();
    }

    public DefaultHttpClient(CipherHelper cipherHelper, JsonHelper jsonHelper) {
        this.cipherHelper = cipherHelper;
        this.jsonHelper = jsonHelper;
    }

    public <T> T get(String url, Object param, Class<T> clazz) throws IOException {
        String ciphertext = encrypt(param);
        String r = UUID.randomUUID().toString();

        String queryString = String.format("ct=%s&r=%s", ciphertext, r);
        String signed = sign(queryString);

        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader(SIGNATURE, signed);

        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(httpGet);

        return analyzeResponse(response, clazz);
    }

    public <T> T post(String url, Object param, Class<T> clazz) throws IOException {
        String ciphertext = encrypt(param);
        String r = UUID.randomUUID().toString();
        RequestBody requestBody = new RequestBody(ciphertext, r);
        String requestBodyJSON = jsonHelper.toJson(requestBody);
        String signed = sign(requestBodyJSON);

        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(SIGNATURE, signed);
        httpPost.setEntity(new ByteArrayEntity(requestBodyJSON.getBytes(Config.ENCODING)));

        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(httpPost);

        return analyzeResponse(response, clazz);
    }

    private <T> T analyzeResponse(HttpResponse response, Class<T> clazz) throws IOException {
        if (response.getStatusLine().getStatusCode() == NO_CONTENT) {
            Header header = response.getFirstHeader(SIGNATURE);
            if (header == null) {
                throw new RuntimeException("不存name为X-SIGNATURE的header");
            }
            String signature = header.getValue();
            if (StringUtils.isBlank(signature)) {
                throw new RuntimeException("签名值为空");
            }

            String body = IOUtils.toString(response.getEntity().getContent(), Config.ENCODING);
            if (verify(body, signature)) {
                ResponseBody responseBody = jsonHelper.fromJson(body, ResponseBody.class);
                byte[] result = cipherHelper.decrypt(Base64.decodeBase64(responseBody.getCt()));
                String JSON = new String(result, Config.ENCODING);
                return jsonHelper.fromJson(JSON, clazz);
            } else {
                throw new RuntimeException("验签失败");
            }
        } else {
            throw new RuntimeException("请求失败,code:" + String.valueOf(response.getStatusLine().getStatusCode()));
        }
    }

    private String encrypt(Object param) throws UnsupportedEncodingException {
        String plaintext = jsonHelper.toJson(param);
        byte[] result = cipherHelper.encrypt(plaintext.getBytes(Config.ENCODING));
        return Base64.encodeBase64URLSafeString(result);
    }

    private String sign(String content) throws UnsupportedEncodingException {
        byte[] result = cipherHelper.sign(content.getBytes(Config.ENCODING));
        return Base64.encodeBase64URLSafeString(result);
    }

    private Boolean verify(String content, String signature) throws UnsupportedEncodingException {
        return cipherHelper.verify(content.getBytes(Config.ENCODING), signature.getBytes(Config.ENCODING));
    }
}
