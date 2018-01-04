package com.maihaoche.brz.command;

import java.util.List;

/**
 * Created by alex on 2018/1/4.
 */
public class Car {
    private final String id;
    private final String vin;
    private final String brand;
    private final String model;
    private final String series;
    private final String interior;
    private final String exterior;
    private final String mileage;
    private final String engineDisplacement;
    private final String dateOfManufacture;
    private final String loanAmount;
    private final String earnestAmount;
    private final String matAmount;
    private final String marketPrice;
    private final List<String> guarantee;
    private final List<Formalities> formalities;
    private final CheckPhoto logistics;
    private final CheckPhoto warehouse;

    public Car(String id, String vin, String brand, String model, String series, String interior, String exterior, String mileage, String engineDisplacement, String dateOfManufacture, String loanAmount, String earnestAmount, String matAmount, String marketPrice, List<String> guarantee, List<Formalities> formalities, CheckPhoto logistics, CheckPhoto warehouse) {
        this.id = id;
        this.vin = vin;
        this.brand = brand;
        this.model = model;
        this.series = series;
        this.interior = interior;
        this.exterior = exterior;
        this.mileage = mileage;
        this.engineDisplacement = engineDisplacement;
        this.dateOfManufacture = dateOfManufacture;
        this.loanAmount = loanAmount;
        this.earnestAmount = earnestAmount;
        this.matAmount = matAmount;
        this.marketPrice = marketPrice;
        this.guarantee = guarantee;
        this.formalities = formalities;
        this.logistics = logistics;
        this.warehouse = warehouse;
    }

    public String getId() {
        return id;
    }

    public String getVin() {
        return vin;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getSeries() {
        return series;
    }

    public String getInterior() {
        return interior;
    }

    public String getExterior() {
        return exterior;
    }

    public String getMileage() {
        return mileage;
    }

    public String getEngineDisplacement() {
        return engineDisplacement;
    }

    public String getDateOfManufacture() {
        return dateOfManufacture;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public String getEarnestAmount() {
        return earnestAmount;
    }

    public String getMatAmount() {
        return matAmount;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public List<String> getGuarantee() {
        return guarantee;
    }

    public List<Formalities> getFormalities() {
        return formalities;
    }

    public CheckPhoto getLogistics() {
        return logistics;
    }

    public CheckPhoto getWarehouse() {
        return warehouse;
    }
}
