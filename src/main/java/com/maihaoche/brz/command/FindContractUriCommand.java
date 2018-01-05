package com.maihaoche.brz.command;

/**
 * Created by alex on 2018/1/5.
 */
public class FindContractUriCommand {

    private final String contractId;
    private final String mime;

    public FindContractUriCommand(String contractId, String mime) {
        this.contractId = contractId;
        this.mime = mime;
    }

    public String getContractId() {
        return contractId;
    }

    public String getMime() {
        return mime;
    }
}
