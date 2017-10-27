package com.maihaoche.brz.network;

import com.maihaoche.brz.utils.ErrorCode;

/**
 * Created by alex on 2017/10/24.
 */
public class ResponseBody {
    private String code;
    private String message;
    private String nonce;
    private String ct;

    public ResponseBody() {
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public Boolean success() {
        return ErrorCode.SUCCESS.getCode().equals(this.code);
    }

    public Boolean fail() {
        return !ErrorCode.SUCCESS.getCode().equals(this.code);
    }
}
