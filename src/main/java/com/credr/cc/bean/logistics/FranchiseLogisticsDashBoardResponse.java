package com.credr.cc.bean.logistics;

import java.util.List;
import java.util.Map;

public class FranchiseLogisticsDashBoardResponse {

    private List<FranchiseLogisticsRecord> franchiseLogisticsRecords;
    private List<String> cities;
    private List<String> showRooms;
    private List<User> runners;
    private Map<TabName, Integer> tabwiseCount;
    private Map<String, Integer> TATWiseCount;
    private boolean isValid = true;
    private String message;

    public List<FranchiseLogisticsRecord> getFranchiseLogisticsRecords() {
        return franchiseLogisticsRecords;
    }

    public void setFranchiseLogisticsRecords(List<FranchiseLogisticsRecord> franchiseLogisticsRecords) {
        this.franchiseLogisticsRecords = franchiseLogisticsRecords;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public List<String> getShowRooms() {
        return showRooms;
    }

    public void setShowRooms(List<String> showRooms) {
        this.showRooms = showRooms;
    }

    public List<User> getRunners() {
        return runners;
    }

    public void setRunners(List<User> runners) {
        this.runners = runners;
    }

    public Map<TabName, Integer> getTabwiseCount() {
        return tabwiseCount;
    }

    public void setTabwiseCount(Map<TabName, Integer> tabwiseCount) {
        this.tabwiseCount = tabwiseCount;
    }

    public Map<String, Integer> getTATWiseCount() {
        return TATWiseCount;
    }

    public void setTATWiseCount(Map<String, Integer> TATWiseCount) {
        this.TATWiseCount = TATWiseCount;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
