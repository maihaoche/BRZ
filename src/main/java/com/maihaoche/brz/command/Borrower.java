package com.maihaoche.brz.command;

import java.util.List;

/**
 * Created by alex on 2018/1/4.
 */
public class Borrower extends Company {
    private final String creditLine;

    public Borrower(String id, String kind, String name, String address, String foundingTime, String expirationTime, String registeredCapital, String socialCreditCode, String businessLicense, String foundingTime1, LegalPerson legalPerson, List<Account> account, String creditLine) {
        super(id, kind, name, address, foundingTime, expirationTime, registeredCapital, socialCreditCode, businessLicense, foundingTime1, legalPerson, account);
        this.creditLine = creditLine;
    }

    public String getCreditLine() {
        return creditLine;
    }
}
