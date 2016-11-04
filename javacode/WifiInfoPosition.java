package com.leanit.subway.common.bean;

/**
 * Created by admin on 2016/11/3.
 */
public class WifiInfoPosition {
    private String ssid;
    private String bssid;
    private Float level;
    Double x;
    Double y;
    String sense;

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public Float getLevel() {
        return level;
    }

    public void setLevel(Float level) {
        this.level = level;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public String getSense() {
        return sense;
    }

    public void setSense(String sense) {
        this.sense = sense;
    }

    @Override
    public String toString() {
        return "WifiInfoPosition{" +
                "ssid='" + ssid + '\'' +
                ", bssid='" + bssid + '\'' +
                ", level=" + level +
                ", x=" + x +
                ", y=" + y +
                ", sense='" + sense + '\'' +
                '}';
    }
}
