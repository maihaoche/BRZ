package com.maihaoche.brz.command;

/**
 * Created by alex on 2017/10/22.
 */
public class RequestAccessTokenCommand extends AbstractCommand {
    private final String corpId;
    private final String corpKey;

    public RequestAccessTokenCommand(String corpId, String corpKey) {
        this.corpId = corpId;
        this.corpKey = corpKey;
    }

    public String getCorpId() {
        return corpId;
    }

    public String getCorpKey() {
        return corpKey;
    }
}
