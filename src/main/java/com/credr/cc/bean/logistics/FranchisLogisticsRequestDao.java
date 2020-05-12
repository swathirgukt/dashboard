package com.credr.cc.bean.logistics;

import com.credr.cc.bean.logistics.FranchiseLogisticsRecord;
import com.credr.cc.bean.logistics.FranchiseLogisticsSearchCriteria;
import com.credr.cc.bean.logistics.User;

import java.util.List;

/**
 * This is an interface to fetch the records present in the database
 *
 * @author Swathi
 * date: 8/5/2020
 * @since 2.13 PM
 */
public interface FranchisLogisticsRequestDao {

    /**
     * This method is used to find all the active records with filters present in the criteria
     *
     * @param franchiseLogisticsSearchCriteria, the franchiseLogisticsSearchCriteria
     * @return
     */
    List<FranchiseLogisticsRecord> findAllActiveRecords(FranchiseLogisticsSearchCriteria franchiseLogisticsSearchCriteria);
    List<String> findAllActiveCities();
    List<User> findAllActiveRunners(FranchiseLogisticsSearchCriteria franchiseLogisticsSearchCriteria);
    List<String> findAllActiveShowRooms(FranchiseLogisticsSearchCriteria franchiseLogisticsSearchCriteria);

    List<User> findAllActiveRunnersForOrder(String orderId);

    boolean updateTransitRequest(AssignRunnerRequest assignRunnerRequest);
}
