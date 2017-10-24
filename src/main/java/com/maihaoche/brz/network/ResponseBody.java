package com.maihaoche.brz.network;

/**
 * Created by alex on 2017/10/24.
 */
public class ResponseBody {
    private String ct;
    private String r;

    public ResponseBody() {
    }

    public ResponseBody(String ct, String r) {
        this.ct = ct;
        this.r = r;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }
}
