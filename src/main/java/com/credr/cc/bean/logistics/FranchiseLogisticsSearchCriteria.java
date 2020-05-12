package com.credr.cc.bean.logistics;


public class FranchiseLogisticsSearchCriteria {
    private TabName tabName;
    private String dateType;
    private String city;
    private String showRoom;
    private String runner;

    public TabName getTabName() {
        return tabName;
    }

    public void setTabName(TabName tabName) {
        this.tabName = tabName;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getShowRoom() {
        return showRoom;
    }

    public void setShowRoom(String showRoom) {
        this.showRoom = showRoom;
    }

    //todo: need to have a Runner type here.
    public String getRunner() {
        return runner;
    }

    public void setRunner(String runner) {
        this.runner = runner;
    }

    @Override
    public String toString() {
        return "FranchiseLogisticsSearchCriteria{" +
                "tabName=" + tabName +
                ", dateType='" + dateType + '\'' +
                ", city='" + city + '\'' +
                ", showRoom='" + showRoom + '\'' +
                ", runner='" + runner + '\'' +
                '}';
    }
}
