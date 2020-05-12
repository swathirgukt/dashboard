package com.credr.cc.bean.logistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FranchisLogisticsMgrImpl implements FranchisLogisticsMgr {
    @Autowired
    FranchisLogisticsRequestDao showRoomDeliveryRequestDao;


    @Override
    public FranchiseLogisticsDashBoardResponse findActiveRecords(Map<String, String> headers) {
        return findActiveRecords(headers, null);
    }

    @Override
    public FranchiseLogisticsDashBoardResponse findActiveRecords(Map<String, String> headers, FranchiseLogisticsSearchCriteria franchiseLogisticsSearchCriteria) {
        FranchiseLogisticsDashBoardResponse franchiseLogisticsDashBoardResponse = new FranchiseLogisticsDashBoardResponse();
        List<FranchiseLogisticsRecord> allActiveRecords = showRoomDeliveryRequestDao.findAllActiveRecords(franchiseLogisticsSearchCriteria);
        if (allActiveRecords != null && !allActiveRecords.isEmpty()) {
            franchiseLogisticsDashBoardResponse.setFranchiseLogisticsRecords(allActiveRecords);
            Map<TabName, Integer> tabWiseCount = getTabNameCountMap(allActiveRecords);
            franchiseLogisticsDashBoardResponse.setTabwiseCount(tabWiseCount);
        } else {
            franchiseLogisticsDashBoardResponse.setMessage("No active requests yet!");
            franchiseLogisticsDashBoardResponse.setValid(false);
        }
        if (franchiseLogisticsSearchCriteria != null) {
            franchiseLogisticsDashBoardResponse.setCities(showRoomDeliveryRequestDao.findAllActiveCities());
            franchiseLogisticsDashBoardResponse.setRunners(showRoomDeliveryRequestDao.findAllActiveRunners(franchiseLogisticsSearchCriteria));
            franchiseLogisticsDashBoardResponse.setShowRooms(showRoomDeliveryRequestDao.findAllActiveShowRooms(franchiseLogisticsSearchCriteria));
        }

        return franchiseLogisticsDashBoardResponse;
    }

    private static Map<TabName, Integer> getTabNameCountMap(List<FranchiseLogisticsRecord> allActiveRecords) {
        Map<TabName, Integer> tabWiseCount = new HashMap<>();
        long allCount = 0;
        for (RequestStatus requestStatus : RequestStatus.values()) {
            long count = allActiveRecords.stream().filter(it -> requestStatus.equals(it.getDeliveryStatus())).count();
            allCount += count;
            tabWiseCount.put(TabName.valueOf(requestStatus.name()), (int) count);
        }
        tabWiseCount.put(TabName.ALL, (int) allCount);
        return tabWiseCount;
    }

    @Override
    public AssignRunnerResponse findActiveRunners(String orderId, Map<String, String> headers) {
        List<User> allActiveRunners = showRoomDeliveryRequestDao.findAllActiveRunnersForOrder(orderId);
        AssignRunnerResponse assignRunnerResponse = new AssignRunnerResponse();
        assignRunnerResponse.setSelectModes(Arrays.asList("Tempo", "Individual"));
        assignRunnerResponse.setSelectRunners(allActiveRunners);
        return assignRunnerResponse;
    }

    @Override
    public AssignRunnerResponse updateTransitInfoRequest(AssignRunnerRequest assignRunnerRequest) {
        //todo: get userid from headers
        boolean updated = showRoomDeliveryRequestDao.updateTransitRequest(assignRunnerRequest);
        System.out.println("##########updated:"+updated);
        AssignRunnerResponse assignRunnerResponse = new AssignRunnerResponse();
        String message = updated ? "update success!" : "update failed";
        assignRunnerResponse.setMessage(message);
        return assignRunnerResponse;
    }

}
