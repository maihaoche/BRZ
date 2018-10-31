package com.maihaoche.brz.command;

/**
 * Created by alex on 2018/10/31.
 */
public class QueryFormalitiesCommand {
    private final String orderNo;
    private final Long carId;

    public QueryFormalitiesCommand(String orderNo, Long carId) {
        this.orderNo = orderNo;
        this.carId = carId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public Long getCarId() {
        return carId;
    }

    @Override
    public String toString() {
        return "OrderAndCarId{" +
                "orderNo='" + orderNo + '\'' +
                ", carId=" + carId +
                '}';
    }
}
