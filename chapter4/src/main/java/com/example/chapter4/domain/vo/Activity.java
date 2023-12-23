package com.example.chapter4.domain.vo;

public class Activity {

    private final String srcHost;
    private final String dstHost;

    public Activity(String srcHost, String dstHost) {
        this.srcHost = srcHost;
        this.dstHost = dstHost;
    }

    public String retrieveSrcHost() {
        return this.srcHost;
    }
}
