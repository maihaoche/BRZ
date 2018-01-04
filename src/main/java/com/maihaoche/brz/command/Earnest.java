package com.maihaoche.brz.command;

import java.util.List;

/**
 * Created by alex on 2018/1/4.
 */
public class Earnest {
    private final String id;
    private final String amount;
    private final String remittanceMode;
    private final Account account;
    private final String remittanceTime;
    private final List<String> voucher;

    public Earnest(String id, String amount, String remittanceMode, Account account, String remittanceTime, List<String> voucher) {
        this.id = id;
        this.amount = amount;
        this.remittanceMode = remittanceMode;
        this.account = account;
        this.remittanceTime = remittanceTime;
        this.voucher = voucher;
    }

    public String getId() {
        return id;
    }

    public String getAmount() {
        return amount;
    }

    public String getRemittanceMode() {
        return remittanceMode;
    }

    public Account getAccount() {
        return account;
    }

    public String getRemittanceTime() {
        return remittanceTime;
    }

    public List<String> getVoucher() {
        return voucher;
    }
}
