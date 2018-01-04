package com.maihaoche.brz.command;

import java.util.List;

/**
 * Created by alex on 2017/11/17.
 */
public class Order {
    private final String orderId;
    private final String kind;
    private final String applierName;
    private final String applierMobile;
    private final String bailAmount;
    private final String contractAmount;
    private final String totalLoanAmount;
    private final String totalMatAmount;
    private final String totalEarnestAmount;
    private final List<Contract> contract;
    private final Borrower borrower;
    private final Provider provider;
    private final Account account;
    private final Earnest earnest;
    private final List<Car> cars;

    public Order(String orderId, String kind, String applierName, String applierMobile, String bailAmount, String contractAmount, String totalLoanAmount, String totalMatAmount, String totalEarnestAmount, List<Contract> contract, Borrower borrower, Provider provider, Account account, Earnest earnest, List<Car> cars) {
        this.orderId = orderId;
        this.kind = kind;
        this.applierName = applierName;
        this.applierMobile = applierMobile;
        this.bailAmount = bailAmount;
        this.contractAmount = contractAmount;
        this.totalLoanAmount = totalLoanAmount;
        this.totalMatAmount = totalMatAmount;
        this.totalEarnestAmount = totalEarnestAmount;
        this.contract = contract;
        this.borrower = borrower;
        this.provider = provider;
        this.account = account;
        this.earnest = earnest;
        this.cars = cars;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getKind() {
        return kind;
    }

    public String getApplierName() {
        return applierName;
    }

    public String getApplierMobile() {
        return applierMobile;
    }

    public String getBailAmount() {
        return bailAmount;
    }

    public String getContractAmount() {
        return contractAmount;
    }

    public String getTotalLoanAmount() {
        return totalLoanAmount;
    }

    public String getTotalMatAmount() {
        return totalMatAmount;
    }

    public String getTotalEarnestAmount() {
        return totalEarnestAmount;
    }

    public List<Contract> getContract() {
        return contract;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public Provider getProvider() {
        return provider;
    }

    public Account getAccount() {
        return account;
    }

    public Earnest getEarnest() {
        return earnest;
    }

    public List<Car> getCars() {
        return cars;
    }
}
