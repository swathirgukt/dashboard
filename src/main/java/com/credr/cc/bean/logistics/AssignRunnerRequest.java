package com.credr.cc.bean.logistics;

import java.util.Date;

public class AssignRunnerRequest  {
    String selectMode;
    String selectRunner;
    String status;
    String disputeReason;
    Date deliveryDate;
    String orderId;
    String userId;


    public String getDisputeReason() {
        return disputeReason;
    }

    public void setDisputeReason(String disputeReason) {
        this.disputeReason = disputeReason;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSelectMode() {
        return selectMode;
    }

    public void setSelectMode(String selectMode) {
        this.selectMode = selectMode;
    }

    public String getSelectRunner() {
        return selectRunner;
    }

    public void setSelectRunner(String selectRunner) {
        this.selectRunner = selectRunner;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "AssignRunnerRequest{" +
                "selectMode='" + selectMode + '\'' +
                ", selectRunner='" + selectRunner + '\'' +
                ", status='" + status + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
