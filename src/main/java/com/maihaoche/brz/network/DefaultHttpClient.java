package com.maihaoche.brz.network;

import com.maihaoche.brz.cipher.CipherHelper;
import com.maihaoche.brz.cipher.DefaultCipherHelper;
import com.maihaoche.brz.coder.DefaultJsonHelper;
import com.maihaoche.brz.coder.JsonHelper;
import com.maihaoche.brz.result.DownloadFile;
import com.maihaoche.brz.utils.Config;
import com.maihaoche.brz.utils.NonceUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

/**
 * Created by alex on 2017/10/24.
 */
public class DefaultHttpClient implements HttpClient {

    private final static String SIGNATURE = "X-Signature";
    private final static String NONCE = "X-Nonce";

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

    public DownloadFile download(String url, Object command, String accessToken) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        HttpResponse response = get(url, command, accessToken);
        String fileName = URLDecoder.decode(response.getFirstHeader("Content-Disposition").getValue(), Config.ENCODING);
        byte[] content = IOUtils.toByteArray(response.getEntity().getContent());

        String signature = response.getFirstHeader(SIGNATURE).getValue();

        if (DigestUtils.md5Hex(content).equals(signature)) {
            return new DownloadFile(fileName, content);
        } else {
            throw new RuntimeException("文件损坏");
        }

    }

    private CloseableHttpClient buildHttpsClient() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
            public boolean isTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
                return true;
            }
        }).build();
        httpClientBuilder.setSSLContext(sslContext);

        return httpClientBuilder.build();
    }

    public <T> T get(String url, Class<T> returnType) throws IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {


        CloseableHttpClient client = buildHttpsClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);

        ResponseBody responseBody = analyzeBody(response);
        if (responseBody.fail()) {
            throw new RuntimeException(String.format("返回值错误,错误码:%s,原因:%s", responseBody.getCode(), responseBody.getMessage()));
        }

        byte[] ct = Base64.decodeBase64(responseBody.getCt());
        byte[] pt = cipherHelper.decrypt(ct);

        String json = new String(pt, Config.ENCODING);

        return jsonHelper.fromJson(json, returnType);
    }

    public void get(String url, String accessToken) throws IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

        CloseableHttpClient client = buildHttpsClient();
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader(ACCESS_TOKEN, accessToken);

        HttpResponse response = client.execute(httpGet);

        ResponseBody responseBody = analyzeBody(response);
        if (responseBody.fail()) {
            throw new RuntimeException(String.format("返回值错误,错误码:%s,原因:%s", responseBody.getCode(), responseBody.getMessage()));
        }
    }

    public <T> T get(String url, Object command, Class<T> returnType) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        return get(url, command, returnType, StringUtils.EMPTY);
    }

    public <T> T get(String url, Object command, Class<T> returnType, String accessToken) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        HttpResponse response = get(url, command, accessToken);
        ResponseBody responseBody = analyzeBody(response);
        if (responseBody.fail()) {
            throw new RuntimeException(String.format("返回值错误,错误码:%s,原因:%s", responseBody.getCode(), responseBody.getMessage()));
        }

        byte[] ct = Base64.decodeBase64(responseBody.getCt());
        byte[] pt = cipherHelper.decrypt(ct);

        return jsonHelper.fromJson(new String(pt, Config.ENCODING), returnType);
    }

    public <T> T get(String url, Object command, Type returnType, String accessToken) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        HttpResponse response = get(url, command, accessToken);
        ResponseBody responseBody = analyzeBody(response);
        if (responseBody.fail()) {
            throw new RuntimeException(String.format("返回值错误,错误码:%s,原因:%s", responseBody.getCode(), responseBody.getMessage()));
        }

        byte[] ct = Base64.decodeBase64(responseBody.getCt());
        byte[] pt = cipherHelper.decrypt(ct);

        return jsonHelper.fromJson(new String(pt, Config.ENCODING), returnType);
    }

    private HttpResponse get(String url, Object command, String accessToken) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String ciphertext = encrypt(command);

        String nonce = NonceUtils.nonce();
        String queryString = String.format("ct=%s", ciphertext);
        String signed = sign(queryString, nonce);

        HttpGet httpGet = new HttpGet(String.format("%s?%s", url, queryString));
        httpGet.addHeader(SIGNATURE, signed);
        httpGet.addHeader(NONCE, nonce);
        if (StringUtils.isNotBlank(accessToken)) {
            httpGet.addHeader(ACCESS_TOKEN, accessToken);
        }

        CloseableHttpClient client = buildHttpsClient();
        return client.execute(httpGet);
    }

    public void post(String url, Object command, String accessToken) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String ciphertext = encrypt(command);

        String nonce = NonceUtils.nonce();
        String requestBodyJSON = jsonHelper.toJson(Collections.singletonMap("ct", ciphertext));
        String signed = sign(requestBodyJSON, nonce);

        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader(SIGNATURE, signed);
        httpPost.addHeader(NONCE, nonce);
        httpPost.addHeader(ACCESS_TOKEN, accessToken);

        httpPost.setEntity(new ByteArrayEntity(requestBodyJSON.getBytes(Config.ENCODING)));

        CloseableHttpClient client = buildHttpsClient();
        CloseableHttpResponse response = client.execute(httpPost);

        ResponseBody responseBody = analyzeBody(response);
        if (responseBody.fail()) {
            throw new RuntimeException(String.format("返回值错误,错误码:%s,原因:%s", responseBody.getCode(), responseBody.getMessage()));
        }
    }

    public void put(String url, Object command, String accessToken) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String ciphertext = encrypt(command);

        String nonce = NonceUtils.nonce();
        String requestBodyJSON = jsonHelper.toJson(Collections.singletonMap("ct", ciphertext));
        String signed = sign(requestBodyJSON, nonce);

        HttpPut httpPutt = new HttpPut(url);
        httpPutt.addHeader("Content-Type", "application/json");
        httpPutt.addHeader(SIGNATURE, signed);
        httpPutt.addHeader(NONCE, nonce);
        httpPutt.addHeader(ACCESS_TOKEN, accessToken);

        httpPutt.setEntity(new ByteArrayEntity(requestBodyJSON.getBytes(Config.ENCODING)));

        CloseableHttpClient client = buildHttpsClient();
        CloseableHttpResponse response = client.execute(httpPutt);

        ResponseBody responseBody = analyzeBody(response);
        if (responseBody.fail()) {
            throw new RuntimeException(String.format("返回值错误,错误码:%s,原因:%s", responseBody.getCode(), responseBody.getMessage()));
        }
    }

    private Boolean isSuccess(HttpResponse response) {
        return response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 204;
    }

    private ResponseHeader analyzeHeader(HttpResponse response) throws IOException {
        Header headerSignature = response.getFirstHeader(SIGNATURE);
        if (headerSignature == null) {
            throw new RuntimeException("不存name为X-Signature的header");
        }
        String signature = headerSignature.getValue();
        if (StringUtils.isBlank(signature)) {
            throw new RuntimeException("X-Signature的值为空");
        }

        Header headerNonce = response.getFirstHeader(NONCE);
        if (headerNonce == null) {
            throw new RuntimeException("不存name为X-Nonce 的header");
        }
        String nonce = headerNonce.getValue();
        if (StringUtils.isBlank(nonce)) {
            throw new RuntimeException("不存name为X-Nonce 的header");
        }

        return new ResponseHeader(signature, nonce);
    }

    private ResponseBody analyzeBody(HttpResponse response) throws IOException {

        if (isSuccess(response)) {
            ResponseHeader header = analyzeHeader(response);
            String body = IOUtils.toString(response.getEntity().getContent(), Config.ENCODING);
            if (verifySignature(body, header.getNonce(), header.getSignature())) {
                ResponseBody responseBody = jsonHelper.fromJson(body, ResponseBody.class);
                return responseBody;
            } else {
                throw new RuntimeException("验签失败");
            }
        } else {
            throw new RuntimeException("请求错误,code:" + response.getStatusLine().getStatusCode());
        }
    }

    private Boolean verifySignature(String content, String nonce, String signature) throws UnsupportedEncodingException {
        byte[] si = Base64.decodeBase64(signature);
        return cipherHelper.verify(content.getBytes(Config.ENCODING), nonce.getBytes(Config.ENCODING), si);
    }

    private String encrypt(Object command) throws UnsupportedEncodingException {
        String plaintext = jsonHelper.toJson(command);
        byte[] result = cipherHelper.encrypt(plaintext.getBytes(Config.ENCODING));
        return Base64.encodeBase64URLSafeString(result);
    }

    private String sign(String content, String nonce) throws UnsupportedEncodingException {
        byte[] result = cipherHelper.sign(content.getBytes(Config.ENCODING), nonce.getBytes(Config.ENCODING));
        return Base64.encodeBase64URLSafeString(result);
    }


}
