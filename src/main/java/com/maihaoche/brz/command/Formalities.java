package com.maihaoche.brz.command;

import java.io.Serializable;import java.lang.String;

/**
 * <p> 车辆手续信息 </p>
 *
 * @author: 文轩（wenxuan@maihaoche.com）
 * @date: 2018/10/31 11:03 AM
 */
public class Formalities implements Serializable{

    //存放地点
    private String location="";
    //钥匙
    private String keys="";
    //关单 有/无
    private String customsDeclaration="";
    //合格证 有/无
    private String certificate="";
    //一致性证书 有/无
    private String consistencyCertificate = "";
    //商检单 有/无
    private String inspectionSheet = "";
    //车购税表 有/无
    private String taxTable = "";
    //发票 有/无
    private String invoice;
    //安检报告 有/无
    private String securityInspectionReport;
    //说明书 有/无
    private String instruction;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public String getCustomsDeclaration() {
        return customsDeclaration;
    }

    public void setCustomsDeclaration(String customsDeclaration) {
        this.customsDeclaration = customsDeclaration;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getConsistencyCertificate() {
        return consistencyCertificate;
    }

    public void setConsistencyCertificate(String consistencyCertificate) {
        this.consistencyCertificate = consistencyCertificate;
    }

    public String getInspectionSheet() {
        return inspectionSheet;
    }

    public void setInspectionSheet(String inspectionSheet) {
        this.inspectionSheet = inspectionSheet;
    }

    public String getTaxTable() {
        return taxTable;
    }

    public void setTaxTable(String taxTable) {
        this.taxTable = taxTable;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getSecurityInspectionReport() {
        return securityInspectionReport;
    }

    public void setSecurityInspectionReport(String securityInspectionReport) {
        this.securityInspectionReport = securityInspectionReport;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}