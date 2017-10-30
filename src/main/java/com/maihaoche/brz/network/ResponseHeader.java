package com.maihaoche.brz.network;

/**
 * Created by alex on 2017/10/24.
 */
public class ResponseHeader {
    private final String signature;
    private final String nonce;

    public ResponseHeader(String signature, String nonce) {
        this.signature = signature;
        this.nonce = nonce;
    }

    public String getSignature() {
        return signature;
    }

    public String getNonce() {
        return nonce;
    }
}
