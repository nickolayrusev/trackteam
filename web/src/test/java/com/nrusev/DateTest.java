package com.nrusev;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
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

    @Test
    public void testSeasonString(){
        String[] strings = "2016/2017".split("/");

        String reduce = Arrays.stream(strings).reduce("", (s, t) -> s+ t.substring(2,4));
        System.out.println(reduce);
    }

    @Test
    public void testSeasonString2(){
        String seasonString = Arrays.stream("2016/2017".split("/")).reduce("", (s, t) -> {
            if(!s.isEmpty())
                return s + "/" + t.substring(2,4)  ;
            else
                return s+t;
        });
        System.out.println(seasonString);
    }
}
