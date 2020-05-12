package com.credr.cc.bean.logistics;

import java.util.List;

public class AssignRunnerResponse {
    private List<String> selectModes;
    private List<User> selectRunners;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getSelectModes() {
        return selectModes;
    }

    public void setSelectModes(List<String> selectModes) {
        this.selectModes = selectModes;
    }

    public List<User> getSelectRunners() {
        return selectRunners;
    }

    public void setSelectRunners(List<User> selectRunners) {
        this.selectRunners = selectRunners;
    }
}
