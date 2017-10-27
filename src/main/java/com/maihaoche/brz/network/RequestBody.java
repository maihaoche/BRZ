package com.maihaoche.brz.network;

import java.util.UUID;

/**
 * Created by alex on 2017/10/26.
 */
public class RequestBody {
    private final String ct;
    private final String nonce = UUID.randomUUID().toString();

    public RequestBody(String ct) {
        this.ct = ct;
    }

    public String getCt() {
        return ct;
    }

    public String getNonce() {
        return nonce;
    }
}
