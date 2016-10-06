package com.nrusev.repository.migration;

import com.nrusev.migration.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nikolay Rusev on 5.10.2016 г..
 */
@Repository
public class MatchesRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("secondaryDataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int countAll(){
        return this.jdbcTemplate.queryForObject("select count(*) from Matches", Integer.class);
    }

    public List<Match> findAll(){
        List<Match> matches = this.jdbcTemplate.query(
                "select id,Name1,Name2 from Matches",
                new RowMapper<Match>() {
                    public Match mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Match match = new Match();
                        match.setId(rs.getLong("id"));
                        match.setName1(rs.getString("Name1"));
                        match.setName2(rs.getString("Name2"));
                        return match;
                    }
                });
        return matches;
    }

}
