package com.maihaoche.brz.command;

/**
 * Created by alex on 2017/10/27.
 */
public class DownloadContractCommand {

    private final String orderId;
    private final String contractId;

    public DownloadContractCommand(String orderId, String contractId) {
        this.orderId = orderId;
        this.contractId = contractId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getContractId() {
        return contractId;
    }
}
