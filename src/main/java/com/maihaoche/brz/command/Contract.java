package com.maihaoche.brz.command;

/**
 * Created by alex on 2017/11/17.
 */
public class Contract {
    private final String orderId;
    private final String contractType;
    private final String url;

    public Contract(String orderId, String contractType, String url) {
        this.orderId = orderId;
        this.contractType = contractType;
        this.url = url;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getContractType() {
        return contractType;
    }

    public String getUrl() {
        return url;
    }
}
