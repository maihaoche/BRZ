package com.maihaoche.brz.param;

/**
 * Created by alex on 2017/10/22.
 */
public class RequestAccessToken extends Request {
    private String corpId;
    private String corpKey;

    public RequestAccessToken() {
    }

    public RequestAccessToken(String corpId, String corpKey, String r) {
        super(r);
        this.corpId = corpId;
        this.corpKey = corpKey;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getCorpKey() {
        return corpKey;
    }

    public void setCorpKey(String corpKey) {
        this.corpKey = corpKey;
    }
}
