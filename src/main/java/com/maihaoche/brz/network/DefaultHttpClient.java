package com.maihaoche.brz.network;

import com.maihaoche.brz.cipher.CipherHelper;
import com.maihaoche.brz.cipher.DefaultCipherHelper;
import com.maihaoche.brz.coder.DefaultJsonHelper;
import com.maihaoche.brz.coder.JsonHelper;
import com.maihaoche.brz.command.AbstractCommand;
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
    private final static String SIGNATURE = "X-Signature";
    private final static String ACCESS_TOKEN = "X-Access-Token";

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

    public <T> T get(String url, AbstractCommand command, Class<T> returnType) throws IOException {
        return get(url, command, returnType, StringUtils.EMPTY);
    }

    public <T> T get(String url, AbstractCommand command, Class<T> returnType, String accessToken) throws IOException {
        String ciphertext = encrypt(command);
        String nonce = UUID.randomUUID().toString();

        String queryString = String.format("ct=%s&nonce=%s", ciphertext, nonce);
        String signed = sign(queryString);

        HttpGet httpGet = new HttpGet(url + "?" + queryString);
        httpGet.addHeader(SIGNATURE, signed);
        if (StringUtils.isNotBlank(accessToken)) {
            httpGet.addHeader(ACCESS_TOKEN, accessToken);
        }

        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(httpGet);

        ResponseBody responseBody = analyzeResponse(response);
        if (responseBody.success()) {
            byte[] bytes = cipherHelper.decrypt(Base64.decodeBase64(responseBody.getCt()));
            String JSON = new String(bytes, Config.ENCODING);
            return jsonHelper.fromJson(JSON, returnType);
        } else {
            throw new RuntimeException(String.format("返回值错误,错误码:%s,原因:%s", responseBody.getCode(), responseBody.getMessage()));
        }
    }

    public void post(String url, AbstractCommand command, String accessToken) throws IOException {
        String ciphertext = encrypt(command);

        RequestBody requestParam = new RequestBody(ciphertext);

        String requestBodyJSON = jsonHelper.toJson(requestParam);
        String signed = sign(requestBodyJSON);

        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader(SIGNATURE, signed);
        httpPost.addHeader(ACCESS_TOKEN, accessToken);

        httpPost.setEntity(new ByteArrayEntity(requestBodyJSON.getBytes(Config.ENCODING)));

        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(httpPost);

        ResponseBody responseBody = analyzeResponse(response);
        if (responseBody.fail()) {
            throw new RuntimeException(String.format("返回值错误,错误码:%s,原因:%s", responseBody.getCode(), responseBody.getMessage()));
        }
    }

    private ResponseBody analyzeResponse(HttpResponse response) throws IOException {
        if (response.getStatusLine().getStatusCode() == NO_CONTENT) {
            Header header = response.getFirstHeader(SIGNATURE);
            if (header == null) {
                throw new RuntimeException("不存name为X-Signature的header");
            }
            String signature = header.getValue();
            if (StringUtils.isBlank(signature)) {
                throw new RuntimeException("X-Signature的值为空");
            }

            String body = IOUtils.toString(response.getEntity().getContent(), Config.ENCODING);
            if (verifySignature(body, signature)) {
                ResponseBody responseBody = jsonHelper.fromJson(body, ResponseBody.class);
                return responseBody;
            } else {
                throw new RuntimeException("验签失败");
            }
        } else {
            throw new RuntimeException("请求失败,code:" + String.valueOf(response.getStatusLine().getStatusCode()));
        }
    }

    private String encrypt(AbstractCommand command) throws UnsupportedEncodingException {
        String plaintext = jsonHelper.toJson(command);
        byte[] result = cipherHelper.encrypt(plaintext.getBytes(Config.ENCODING));
        return Base64.encodeBase64URLSafeString(result);
    }

    private String sign(String content) throws UnsupportedEncodingException {
        byte[] result = cipherHelper.sign(content.getBytes(Config.ENCODING));
        return Base64.encodeBase64URLSafeString(result);
    }

    private Boolean verifySignature(String content, String signature) throws UnsupportedEncodingException {
        return cipherHelper.verify(content.getBytes(Config.ENCODING), signature.getBytes(Config.ENCODING));
    }
}
