package com.morris.stunes.model;

public class ClickStreamRecord {
    private String userId;
    private String deviceType;
    private String clickEvent;
    private String clientTimestamp;

    public ClickStreamRecord() {}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getClickEvent() {
        return clickEvent;
    }

    public void setClickEvent(String clickEvent) {
        this.clickEvent = clickEvent;
    }

    public String getClientTimestamp() {
        return clientTimestamp;
    }

    public void setClientTimestamp(String clientTimestamp) {
        this.clientTimestamp = clientTimestamp;
    }

    @Override
    public String toString() {
        return "{" + this.userId + ", " + this.deviceType + ", " + this.clickEvent + ", " + this.clientTimestamp + "}";
    }
}
