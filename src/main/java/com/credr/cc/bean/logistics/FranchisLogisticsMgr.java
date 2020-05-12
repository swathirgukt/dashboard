package com.credr.cc.bean.logistics;

import java.util.Map;

public interface FranchisLogisticsMgr {
    FranchiseLogisticsDashBoardResponse findActiveRecords(Map<String, String> headers);

    FranchiseLogisticsDashBoardResponse findActiveRecords(Map<String, String> headers, FranchiseLogisticsSearchCriteria franchiseLogisticsSearchCriteria);

    AssignRunnerResponse findActiveRunners(String orderId, Map<String, String> headers);

    AssignRunnerResponse updateTransitInfoRequest(AssignRunnerRequest assignRunnerRequest);
}
