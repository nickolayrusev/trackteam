package com.nrusev.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;

/**
 * Created by Nikolay Rusev on 5.10.2016 г..
 */
@Repository
public class MatchesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void findAllMatches(){
        Integer query = this.jdbcTemplate.queryForObject("select count(*) from Matches", Integer.class);
        System.out.println(query);
    }
}
