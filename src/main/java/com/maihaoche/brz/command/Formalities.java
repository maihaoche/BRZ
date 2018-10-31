package com.maihaoche.brz.command;

import java.io.Serializable;import java.lang.String;

/**
 * <p> 车辆手续信息 </p>
 *
 * @author: 文轩（wenxuan@maihaoche.com）
 * @date: 2018/10/31 11:03 AM
 */
public class Formalities implements Serializable{

    /**
     * 车辆钥匙存放地点
     */
    private String storeAddress;

    /**
     * 钥匙数量
     */
    private int keys;

    /**
     * 关单
     */
    private String cargoDeclaration;

    /**
     * 合格证
     */
    private String certificate;

    /**
     * 车辆一致性证书
     */
    private String vehicleConsistencyCertificate;

    /**
     * 商检单
     */
    private String checklist;

    /**
     * 车购税表
     */
    private String purchaseTaxList;

    /**
     * 发票
     */
    private String invoice;

    /**
     * 安检报告
     */
    private String securityReport;

    /**
     * 说明书
     */
    private String instruction;

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public int getKeys() {
        return keys;
    }

    public void setKeys(int keys) {
        this.keys = keys;
    }

    public String getCargoDeclaration() {
        return cargoDeclaration;
    }

    public void setCargoDeclaration(String cargoDeclaration) {
        this.cargoDeclaration = cargoDeclaration;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getVehicleConsistencyCertificate() {
        return vehicleConsistencyCertificate;
    }

    public void setVehicleConsistencyCertificate(String vehicleConsistencyCertificate) {
        this.vehicleConsistencyCertificate = vehicleConsistencyCertificate;
    }

    public String getChecklist() {
        return checklist;
    }

    public void setChecklist(String checklist) {
        this.checklist = checklist;
    }

    public String getPurchaseTaxList() {
        return purchaseTaxList;
    }

    public void setPurchaseTaxList(String purchaseTaxList) {
        this.purchaseTaxList = purchaseTaxList;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getSecurityReport() {
        return securityReport;
    }

    public void setSecurityReport(String securityReport) {
        this.securityReport = securityReport;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}