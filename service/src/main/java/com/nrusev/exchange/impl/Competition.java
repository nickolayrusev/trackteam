package com.nrusev.exchange.impl;

/**
 * Created by Nikolay Rusev on 21.2.2017 г..
 */
public class Competition {
    private Long id;
    private String region;
    private String name;
    private boolean supported;

    public Competition() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSupported() {
        return supported;
    }

    public void setSupported(boolean supported) {
        this.supported = supported;
    }
}
