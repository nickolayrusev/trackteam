package com.nrusev;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

/**
 * Created by Nikolay Rusev on 10.2.2017 г..
 */
public class DateTest {
    @Test
    public void testDate(){
        Gson gson = new GsonBuilder().create();
        Date now=new Date();
        System.out.println(gson.toJson(now));

    }
}
