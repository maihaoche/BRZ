package com.maihaoche.brz.command;

import java.util.List;

/**
 * Created by alex on 2018/1/4.
 */
public class Provider extends Company {
    public Provider(String id, String kind, String name, String address, String foundingTime, String expirationTime, String registeredCapital, String socialCreditCode, String businessLicense, String foundingTime1, LegalPerson legalPerson, List<Account> account) {
        super(id, kind, name, address, foundingTime, expirationTime, registeredCapital, socialCreditCode, businessLicense, foundingTime1, legalPerson, account);
    }
}
