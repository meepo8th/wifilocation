package com.leanit.subway.common.bean;

/**
 * Created by admin on 2016/11/3.
 */
public class WifiInfo {
    private String ssid;
    private String bssid;
    private float level;

    public WifiInfo(String ssid, String bssid, float level) {
        this.ssid = ssid;
        this.bssid = bssid;
        this.level = level;
    }

    public WifiInfo() {
    }

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

    public float getLevel() {
        return level;
    }

    public void setLevel(float level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "WifiInfo{" +
                "ssid='" + ssid + '\'' +
                ", bssid='" + bssid + '\'' +
                ", level=" + level +
                '}';
    }
}
