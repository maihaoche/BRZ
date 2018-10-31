package com.maihaoche.brz.command;

/**
 * Created by alex on 2018/10/31.
 */
public class QueryCarrierCommand {
    private final Long carrierId;
    private final String carrierName;

    public QueryCarrierCommand(Long carrierId, String carrierName) {
        this.carrierId = carrierId;
        this.carrierName = carrierName;
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public String getCarrierName() {
        return carrierName;
    }

    @Override
    public String toString() {
        return "QueryCarrierCommand{" +
                "carrierId=" + carrierId +
                ", carrierName='" + carrierName + '\'' +
                '}';
    }
}
