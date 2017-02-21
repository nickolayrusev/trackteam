package com.nrusev;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
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

    @Test
    public void testLocalDate(){
        LocalDate date = LocalDate.now(ZoneOffset.UTC);
        System.out.println("local date " + date);

        LocalDateTime dateTime = LocalDateTime.now(ZoneOffset.UTC);
        System.out.println("local date time as java.util.Date " + Date.from(dateTime.toInstant(ZoneOffset.UTC)));
        System.out.println("local date time "+ dateTime);
    }
}
