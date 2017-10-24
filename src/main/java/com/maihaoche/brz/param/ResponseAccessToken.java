package com.maihaoche.brz.param;

/**
 * Created by alex on 2017/10/22.
 */
public class ResponseAccessToken extends Response {
    private String data;

    public ResponseAccessToken() {
    }

    public ResponseAccessToken(String code, String message, String r, String data) {
        super(code, message, r);
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
