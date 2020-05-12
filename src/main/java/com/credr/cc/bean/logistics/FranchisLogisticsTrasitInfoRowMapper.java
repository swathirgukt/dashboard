package com.credr.cc.bean.logistics;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FranchisLogisticsTrasitInfoRowMapper implements RowMapper<FranchisLogisticsTrasitInfo> {
    @Override
    public FranchisLogisticsTrasitInfo mapRow(ResultSet resultSet, int i) throws SQLException {

        FranchisLogisticsTrasitInfo showRoomDeliveryTrasitInfo = new FranchisLogisticsTrasitInfo();
        showRoomDeliveryTrasitInfo.setOrderId(resultSet.getInt("order_id"));
        showRoomDeliveryTrasitInfo.setRunnerId(resultSet.getInt("runner_id"));
        showRoomDeliveryTrasitInfo.setSalesExecutiveId(resultSet.getInt("sales_executive_id"));
        showRoomDeliveryTrasitInfo.setDeliveryStatus(resultSet.getString("delivery_status"));
        showRoomDeliveryTrasitInfo.setDisputeReason(resultSet.getString("dispute_reason"));
        showRoomDeliveryTrasitInfo.setActualDeliveryDate(resultSet.getDate("actual_delivery_date"));
        showRoomDeliveryTrasitInfo.setAssignedDate(resultSet.getDate("assigned_date"));
        showRoomDeliveryTrasitInfo.setPickupDate(resultSet.getDate("pickup_date"));
        showRoomDeliveryTrasitInfo.setUpdatedByUserId(resultSet.getInt("updated_by"));
        showRoomDeliveryTrasitInfo.setUpdatedDate(resultSet.getDate("updated_date"));
        return showRoomDeliveryTrasitInfo;
    }
}
