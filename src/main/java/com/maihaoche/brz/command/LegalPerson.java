package com.maihaoche.brz.command;

import java.util.List;

/**
 * Created by alex on 2018/1/4.
 */
public class LegalPerson {
    private final String id;
    private final String name;
    private final String idNumber;
    private final String idAddress;
    private final String mobile;
    private final String effectiveTime;
    private final String expirationTime;
    private final List<String> idPhotos;

    public LegalPerson(String id, String name, String idNumber, String idAddress, String mobile, String effectiveTime, String expirationTime, List<String> idPhotos) {
        this.id = id;
        this.name = name;
        this.idNumber = idNumber;
        this.idAddress = idAddress;
        this.mobile = mobile;
        this.effectiveTime = effectiveTime;
        this.expirationTime = expirationTime;
        this.idPhotos = idPhotos;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getIdAddress() {
        return idAddress;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public List<String> getIdPhotos() {
        return idPhotos;
    }
}
