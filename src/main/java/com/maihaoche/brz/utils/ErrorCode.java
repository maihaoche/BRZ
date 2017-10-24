package com.maihaoche.brz.utils;

/**
 * Created by alex on 2017/10/24.
 */
public enum ErrorCode {
    SUCCESS("100000");

    String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
