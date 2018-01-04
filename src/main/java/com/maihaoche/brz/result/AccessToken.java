package com.maihaoche.brz.result;

/**
 * Created by alex on 2017/10/22.
 */
public class AccessToken {
    private String token;//TOKEN 值
    private Integer expire;//过期时间

    public AccessToken() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getExpire() {
        return expire;
    }

    public void setExpire(Integer expire) {
        this.expire = expire;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "token='" + token + '\'' +
                ", expire=" + expire +
                '}';
    }
}
