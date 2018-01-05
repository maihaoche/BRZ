package com.maihaoche.brz.command;

/**
 * Created by alex on 2018/1/5.
 */
public class SignCommand {
    private final String contractId;
    private final String captcha;

    public SignCommand(String contractId, String captcha) {
        this.contractId = contractId;
        this.captcha = captcha;
    }

    public String getContractId() {
        return contractId;
    }

    public String getCaptcha() {
        return captcha;
    }
}
