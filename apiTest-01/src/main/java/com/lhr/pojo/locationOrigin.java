package com.lhr.pojo;

public class locationOrigin {
    private String log;
    private String lat;

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public locationOrigin(String log, String lat) {
        this.log = log;
        this.lat = lat;
    }

    public String toStringUrl() {
        return log+","+lat;
    }
}
