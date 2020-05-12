package com.credr.cc.bean.logistics;

import java.util.Date;

public class FranchiseLogisticsRecord {
    private String orderId;
    private String salesExecutive;
    private String runner;
    private String deliveryTAT;
    private RequestStatus deliveryStatus;
    private String registrationNumber;
    private String bikeVariant;
    private String wareHouseCity;
    private String disputeReason;
    private Date assignedDate;
    private Date requisitionDate;
    private Date pickupDate;
    private Date deliveryDate;
    private Date deliveryExpectedDate;
    //private String bikeMake;
    //private String bikeMakeNo;
    //private String bikeModel;
    //private String bikeModelNo;
    // private String bikeVariantNo;
    //private String bikeMakeYear;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSalesExecutive() {
        return salesExecutive;
    }

    public void setSalesExecutive(String salesExecutive) {
        this.salesExecutive = salesExecutive;
    }

    public String getRunner() {
        return runner;
    }

    public void setRunner(String runner) {
        this.runner = runner;
    }

    public String getDeliveryTAT() {
        return deliveryTAT;
    }

    public void setDeliveryTAT(String deliveryTAT) {
        this.deliveryTAT = deliveryTAT;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Date getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(Date assignedDate) {
        this.assignedDate = assignedDate;
    }

    public Date getRequisitionDate() {
        return requisitionDate;
    }

    public void setRequisitionDate(Date requisitionDate) {
        this.requisitionDate = requisitionDate;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getDeliveryExpectedDate() {
        return deliveryExpectedDate;
    }

    public void setDeliveryExpectedDate(Date deliveryExpectedDate) {
        this.deliveryExpectedDate = deliveryExpectedDate;
    }

    public RequestStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(RequestStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }


    public String getBikeVariant() {
        return bikeVariant;
    }

    public void setBikeVariant(String bikeVariant) {
        this.bikeVariant = bikeVariant;
    }
/*
    public String getBikeMake() {
        return bikeMake;
    }

    public void setBikeMake(String bikeMake) {
        this.bikeMake = bikeMake;
    }

    public String getBikeMakeNo() {
        return bikeMakeNo;
    }

    public void setBikeMakeNo(String bikeMakeNo) {
        this.bikeMakeNo = bikeMakeNo;
    }

    public String getBikeModel() {
        return bikeModel;
    }

    public void setBikeModel(String bikeModel) {
        this.bikeModel = bikeModel;
    }

    public String getBikeModelNo() {
        return bikeModelNo;
    }

    public void setBikeModelNo(String bikeModelNo) {
        this.bikeModelNo = bikeModelNo;
    }

    public String getBikeVariantNo() {
        return bikeVariantNo;
    }

    public void setBikeVariantNo(String bikeVariantNo) {
        this.bikeVariantNo = bikeVariantNo;
    }

    public String getBikeMakeYear() {
        return bikeMakeYear;
    }

    public void setBikeMakeYear(String bikeMakeYear) {
        this.bikeMakeYear = bikeMakeYear;
    }
*/

    public String getWareHouseCity() {
        return wareHouseCity;
    }

    public void setWareHouseCity(String wareHouseCity) {
        this.wareHouseCity = wareHouseCity;
    }

    public String getDisputeReason() {
        return disputeReason;
    }

    public void setDisputeReason(String disputeReason) {
        this.disputeReason = disputeReason;
    }
}
