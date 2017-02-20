package com.nrusev.enums;

/**
 * Created by Nikolay Rusev on 20.2.2017 г..
 */
public enum SeasonKeys {
    SEASON_2016_2017("2016/17"),
    SEASON_2015_2016("2015/16"),
    SEASON_2014_2015("2014/15"),
    SEASON_2013_2014("2013/14");

    private final String key;

    SeasonKeys(String key) {
        this.key = key;
    }
}
