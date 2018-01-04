package com.maihaoche.brz.command;

/**
 * Created by alex on 2018/1/4.
 */
public class Account {
    private final String bank;
    private final String number;
    private final String name;

    public Account(String bank, String number, String name) {
        this.bank = bank;
        this.number = number;
        this.name = name;
    }

    public String getBank() {
        return bank;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
