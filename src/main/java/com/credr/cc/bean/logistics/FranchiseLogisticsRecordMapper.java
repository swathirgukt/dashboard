package com.credr.cc.bean.logistics;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FranchiseLogisticsRecordMapper implements RowMapper<FranchiseLogisticsRecord> {

    @Override
    public FranchiseLogisticsRecord mapRow(ResultSet rs, int count) throws SQLException {
        FranchiseLogisticsRecord franchiseLogisticsRecord = new FranchiseLogisticsRecord();

        franchiseLogisticsRecord.setOrderId(rs.getString("order_id"));
        franchiseLogisticsRecord.setRunner(rs.getString("runner_id"));
        franchiseLogisticsRecord.setAssignedDate(formatDateFromString(rs.getString("assigned_date")));
        franchiseLogisticsRecord.setDeliveryDate(formatDateFromString(rs.getString("delivery_date")));
        franchiseLogisticsRecord.setDeliveryExpectedDate(formatDateFromString(rs.getString("expected_delivery_date")));
        franchiseLogisticsRecord.setSalesExecutive(rs.getString("sales_executive_id"));
        franchiseLogisticsRecord.setPickupDate(formatDateFromString(rs.getString("pickup_date")));
        franchiseLogisticsRecord.setDeliveryStatus(RequestStatus.valueOf(rs.getString("delivery_status")));
        //franchiseLogisticsRecord.setDeliveryTAT(rs.getString("delivery_TAT"));
        franchiseLogisticsRecord.setRegistrationNumber(rs.getString("registration_number"));
	/*	franchiseLogisticsRecord.setBikeMake(rs.getString("bm_name"));
		franchiseLogisticsRecord.setBikeModel(rs.getString("bmodel_name"));
	*/
        franchiseLogisticsRecord.setBikeVariant(rs.getString("bv_vehicle_name"));

        franchiseLogisticsRecord.setWareHouseCity(rs.getString("city_name"));
        franchiseLogisticsRecord.setDisputeReason(rs.getString("dispute_reason"));

        return franchiseLogisticsRecord;
    }

    private static Date formatDateFromString(String date) {
        if (date == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

