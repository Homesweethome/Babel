package com.library.babel;

public class WifiPoints {
    private String ssid;
    private Double level;

    public WifiPoints(String ssid, Double level) {
        this.ssid = ssid;
        this.level = level;
    }

    public String getSsid() {
        return ssid;
    }

    public Double getLevel() {
        return level;
    }
}
