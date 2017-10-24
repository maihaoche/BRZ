package com.maihaoche.brz.param;

import com.maihaoche.brz.utils.ErrorCode;

/**
 * Created by alex on 2017/10/22.
 */
public class Response {
    private String code;
    private String message;
    private String r;

    public Response() {
    }

    public Response(String code, String message, String r) {
        this.code = code;
        this.message = message;
        this.r = r;
    }

    public Boolean success() {
        return ErrorCode.SUCCESS.getCode().equals(this.code);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getR() {
        return r;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setR(String r) {
        this.r = r;
    }
}
