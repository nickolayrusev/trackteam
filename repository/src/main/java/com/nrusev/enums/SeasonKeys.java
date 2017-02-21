package com.nrusev.enums;

/**
 * Created by Nikolay Rusev on 20.2.2017 г..
 */
public enum SeasonKeys {
    SEASON_2016_2017("2016/2017"),
    SEASON_2015_2016("2015/2016"),
    SEASON_2014_2015("2014/2015"),
    SEASON_2013_2014("2013/2014");

    private final String key;

    SeasonKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
