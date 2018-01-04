package com.maihaoche.brz.command;

/**
 * Created by alex on 2018/1/4.
 */
public class Formalities {
    private final String location;
    private final String keys;
    private final String customsDeclaration;
    private final String certificate;
    private final String consistencyCertificate;
    private final String inspectionSheet;
    private final String taxTable;
    private final String invoice;
    private final String securityInspectionReport;
    private final String instruction;

    public Formalities(String location, String keys, String customsDeclaration, String certificate, String consistencyCertificate, String inspectionSheet, String taxTable, String invoice, String securityInspectionReport, String instruction) {
        this.location = location;
        this.keys = keys;
        this.customsDeclaration = customsDeclaration;
        this.certificate = certificate;
        this.consistencyCertificate = consistencyCertificate;
        this.inspectionSheet = inspectionSheet;
        this.taxTable = taxTable;
        this.invoice = invoice;
        this.securityInspectionReport = securityInspectionReport;
        this.instruction = instruction;
    }

    public String getLocation() {
        return location;
    }

    public String getKeys() {
        return keys;
    }

    public String getCustomsDeclaration() {
        return customsDeclaration;
    }

    public String getCertificate() {
        return certificate;
    }

    public String getConsistencyCertificate() {
        return consistencyCertificate;
    }

    public String getInspectionSheet() {
        return inspectionSheet;
    }

    public String getTaxTable() {
        return taxTable;
    }

    public String getInvoice() {
        return invoice;
    }

    public String getSecurityInspectionReport() {
        return securityInspectionReport;
    }

    public String getInstruction() {
        return instruction;
    }
}
