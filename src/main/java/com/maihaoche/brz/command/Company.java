package com.maihaoche.brz.command;

import java.util.List;

/**
 * Created by alex on 2018/1/4.
 */
public class Company {
    private final String id;
    private final String kind;
    private final String name;
    private final String address;
    private final String foundingTime;

    private final String expirationTime;
    private final String registeredCapital;
    private final String socialCreditCode;
    private final String businessLicense;

    private final LegalPerson legalPerson;
    private final List<Account> account;

    public Company(String id, String kind, String name, String address, String foundingTime, String expirationTime, String registeredCapital, String socialCreditCode, String businessLicense, String foundingTime1, LegalPerson legalPerson, List<Account> account) {
        this.id = id;
        this.kind = kind;
        this.name = name;
        this.address = address;
        this.foundingTime = foundingTime;
        this.expirationTime = expirationTime;
        this.registeredCapital = registeredCapital;
        this.socialCreditCode = socialCreditCode;
        this.businessLicense = businessLicense;
        this.legalPerson = legalPerson;
        this.account = account;
    }

    public String getId() {
        return id;
    }

    public String getKind() {
        return kind;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getFoundingTime() {
        return foundingTime;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public String getRegisteredCapital() {
        return registeredCapital;
    }

    public String getSocialCreditCode() {
        return socialCreditCode;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public LegalPerson getLegalPerson() {
        return legalPerson;
    }

    public List<Account> getAccount() {
        return account;
    }
}
