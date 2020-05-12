package com.credr.cc.bean.logistics;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * This is an interface to fetch the records present in the database
 * author Swathi
 * date: 8/5/2020
 *
 * @since 2.13 PM
 */
@Transactional
@Repository
public class FranchiseLogisticsRequestDaoImpl implements FranchisLogisticsRequestDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(FranchiseLogisticsRequestDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * This method is used to find all the active records with filters present in the criteria
     *
     * @param franchiseLogisticsSearchCriteria, the franchiseLogisticsSearchCriteria
     * @return
     */
    public List<FranchiseLogisticsRecord> findAllActiveRecords(FranchiseLogisticsSearchCriteria franchiseLogisticsSearchCriteria) {
        StringBuffer whereClause = new StringBuffer();
        if (null != franchiseLogisticsSearchCriteria) {
            LOGGER.warn("###franchiseLogisticsSearchCriteria:" + franchiseLogisticsSearchCriteria);
            if (null != franchiseLogisticsSearchCriteria.getTabName() && !TabName.ALL.equals(franchiseLogisticsSearchCriteria.getTabName())) {
                whereClause.append("sdti.delivery_status='").append(franchiseLogisticsSearchCriteria.getTabName().name().toLowerCase()).append("'");
            }
            if (null != franchiseLogisticsSearchCriteria.getCity()) {
                if (StringUtils.isNotBlank(whereClause)) {
                    whereClause.append(" and ");
                }
                whereClause.append("c.city_name='").append(franchiseLogisticsSearchCriteria.getCity()).append("'");
            }
            if (null != franchiseLogisticsSearchCriteria.getDateType()) {
                if (StringUtils.isNotBlank(whereClause)) {
                    whereClause.append(" and ");
                }
                whereClause.append("sdti.assigned_date='").append(franchiseLogisticsSearchCriteria.getDateType()).append("'");
            }
            if (null != franchiseLogisticsSearchCriteria.getRunner()) {
                if (StringUtils.isNotBlank(whereClause)) {
                    whereClause.append(" and ");
                }
                whereClause.append("sdti.runner_id='").append(franchiseLogisticsSearchCriteria.getRunner()).append("'");
            }
            if (null != franchiseLogisticsSearchCriteria.getShowRoom()) {
                if (StringUtils.isNotBlank(whereClause)) {
                    whereClause.append(" and ");
                }
                whereClause.append("ibd.ibd_store_name='").append(franchiseLogisticsSearchCriteria.getShowRoom()).append("'");
            }
        }
        LOGGER.warn("@@@@@@@@LOGGER IN DAO criteria.." + whereClause);
        String query = "SELECT fi.order_id,sdti.delivery_status,fi.payment_status,fi.expected_delivery_date,fi.delivery_date,\n" +
                "sdti.assigned_date,sdti.runner_id,sdti.dispute_reason,sdti.sales_executive_id,sdti.pickup_date," +
                "fi.payment_date,fi.registration_number,ibd.ibd_vehicle_type,\n" +
                "bv.bv_vehicle_name, c.city_name,  \n" +
                "fh.status,fh.remarks\n" +
                "FROM franchise_inventory fi \n" +
                "INNER JOIN city c ON fi.city_id=c.city_id\n" +
                "INNER JOIN showroom_delivery_transit_info sdti ON fi.order_id=sdti.order_id\n" +
                "INNER JOIN inspected_bike_details ibd ON fi.order_id=ibd.franchise_order_id \n" +
                "LEFT JOIN bike_manufacturer bmr ON ibd.ibd_bm_id = bmr.bm_id\n" +
                "LEFT JOIN bike_variant bv ON ibd.ibd_bv_id = bv.bv_id\n" +
                "INNER JOIN (SELECT * FROM franchise_history fhl WHERE  fhl.order_id=order_id AND fhl.status='PAYMENT_APPROVED' ORDER BY id DESC LIMIT 1 )fh  \n" +
                "WHERE fi.payment_status ='APPROVED'";
        if (StringUtils.isNotBlank(whereClause)) {
            query = query + " AND " + whereClause;
            LOGGER.warn("@@@@query:" + query);
        }
        LOGGER.warn("@@@@query:" + query);
        return jdbcTemplate.query(query, new FranchiseLogisticsRecordMapper());
    }

    public List<String> findAllActiveCities() {
        String query = "select city_name from city where city_status='ACTIVE'";
        List<String> cities = jdbcTemplate.queryForList(query, String.class);
        LOGGER.warn("@@@@QUERY:" + query + " @@@ cities:" + cities);
        return cities;
    }

    @Override
    public List<User> findAllActiveRunners(FranchiseLogisticsSearchCriteria franchiseLogisticsSearchCriteria) {
        String query = "select user_id,user_first_name,user_last_name,user_mobile_number from user limit 1";
        if (franchiseLogisticsSearchCriteria != null) {
            //todo: can filter out users belong to that city from criteria and whose role is runner and have minimal number of active requests.
        }
        List<User> runners = jdbcTemplate.query(query, new FranchiseLogisticsUserRowMapper());
        LOGGER.warn("@@@@QUERY:" + query + " @@@ runners:" + runners);
        return runners;
    }

    @Override
    public List<String> findAllActiveShowRooms(FranchiseLogisticsSearchCriteria franchiseLogisticsSearchCriteria) {
        String query = "select store_name from store where website_store_status='ACTIVE' AND store_city_name='" + franchiseLogisticsSearchCriteria.getCity() + "' limit 1";
        List<String> cities = jdbcTemplate.queryForList(query, String.class);
        LOGGER.warn("@@@@QUERY:" + query + " @@@ cities:" + cities);
        return cities;
    }

    @Override
    public List<User> findAllActiveRunnersForOrder(String orderId) {
        return findAllActiveRunners(null);
    }

    @Override
    public boolean updateTransitRequest(AssignRunnerRequest assignRunnerRequest) {
        String orderId = assignRunnerRequest.getOrderId();
        String existingCount = "select count(*) from showroom_delivery_transit_info where order_id=" + orderId;
        Integer count = jdbcTemplate.queryForObject(existingCount, Integer.class);
        LOGGER.info("@@@@ count :" + count + " .. " + existingCount);
        if (count != null && count > 0) {
            return updateRequest(assignRunnerRequest);
        } else {
            LOGGER.info("###### create:");
            return createRequest(assignRunnerRequest);
        }
    }

    private boolean createRequest(AssignRunnerRequest assignRunnerRequest) {
        String query = "INSERT INTO showroom_delivery_transit_info(order_id,runner_id,updated_by,delivery_status,assigned_date,actual_delivery_date,dispute_reason) VALUES(?,?,?,?,?,?,?)";
        int update = 0;
        try {
            LOGGER.warn("@@@ createRequest query2:" + query);
            String status = assignRunnerRequest.getStatus() == null ? "ASSIGNED" : assignRunnerRequest.getStatus();
            update = jdbcTemplate.update(query, assignRunnerRequest.getOrderId(), assignRunnerRequest.getSelectRunner(), assignRunnerRequest.getUserId(), status, new Date(), assignRunnerRequest.getDeliveryDate(), assignRunnerRequest.getDisputeReason());
        } catch (DataAccessException e) {
        }
        return update > 0;
    }

    private boolean updateRequest(AssignRunnerRequest assignRunnerRequest) {
        LOGGER.warn("###########updateRequest:" + assignRunnerRequest);
        //keeping the existing record in audit table before upading the data.
        boolean update1 = updateTransitHistory(assignRunnerRequest.getOrderId());
        boolean update = updateTransitInfo(assignRunnerRequest);
        return update && update1;
    }

    private boolean updateTransitInfo(AssignRunnerRequest assignRunnerRequest) {
        int update = 0;
        if (assignRunnerRequest != null) {
            StringBuffer qry = new StringBuffer("");
            if (assignRunnerRequest.getStatus() != null) {
                qry.append(" delivery_status='").append(assignRunnerRequest.getStatus()).append("' ");
            }
            if (assignRunnerRequest.getSelectRunner() != null) {
                if (StringUtils.isNotBlank(qry)) {
                    qry.append(",");
                }
                qry.append(" runner_id='").append(assignRunnerRequest.getSelectRunner()).append("' ");
            }


            if (null != assignRunnerRequest.getUserId()) {
                if (StringUtils.isNotBlank(qry)) {
                    qry.append(",");
                }
                qry.append("updated_by='").append(assignRunnerRequest.getUserId()).append("' ");
            }
            if (null != assignRunnerRequest.getDeliveryDate()) {
                if (StringUtils.isNotBlank(qry)) {
                    qry.append(",");
                }
                qry.append("actual_delivery_date=? ");
            }
            if (assignRunnerRequest.getDisputeReason() != null) {
                if (StringUtils.isNotBlank(qry)) {
                    qry.append(",");
                }
                qry.append(" dispute_reason='").append(assignRunnerRequest.getDisputeReason()).append("' ");
            }
            String query = "update showroom_delivery_transit_info set " + qry + " where order_id='" + assignRunnerRequest.getOrderId() + "'";
            LOGGER.warn("@@@query FINAL :" + query);

            try {
                if (null == assignRunnerRequest.getDeliveryDate()) {
                    update = jdbcTemplate.update(query);
                } else {
                    update = jdbcTemplate.update(query, assignRunnerRequest.getDeliveryDate());

                }
            } catch (DataAccessException e) {
                LOGGER.warn("#### 2 Data access e:" + e, e);
            }
        }
        return update > 0;
    }

    private boolean updateTransitHistory(String orderId) {
        String queryForExistingRecord = "select * from showroom_delivery_transit_info where order_id=" + orderId;
        List<FranchisLogisticsTrasitInfo> existingRecords = jdbcTemplate.query(queryForExistingRecord, new FranchisLogisticsTrasitInfoRowMapper());
        if (!existingRecords.isEmpty()) {
            String query = "insert into audit_showroom_delivery_transit_info(order_id,sales_executive_id," +
                    "runner_id,assigned_date,updated_date,updated_by,expected_delivery_date,pickup_date," +
                    "dispute_reason,delivery_status,actual_delivery_date) values (?,?,?,?,?,?,?,?,?,?,?)";
            int update = 0;
            try {
                FranchisLogisticsTrasitInfo trasitInfo = existingRecords.get(0);
                LOGGER.warn("@@@ updateTransitHistory query:" + query);
                update = jdbcTemplate.update(query, trasitInfo.getOrderId(), trasitInfo.getSalesExecutiveId(), trasitInfo.getRunnerId(), trasitInfo.getAssignedDate(), trasitInfo.getUpdatedDate(), trasitInfo.getUpdatedByUserId(), trasitInfo.getExpectedDeliveryDate(), trasitInfo.getPickupDate(), trasitInfo.getDisputeReason(), trasitInfo.getDeliveryStatus(), trasitInfo.getActualDeliveryDate());
            } catch (DataAccessException e) {
                LOGGER.warn("#### 1 Data access e:" + e, e);
            }
            return update > 0;
        }
        return false;
    }
}
