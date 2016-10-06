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
                "select id,Name1,Name2,Score1, Score2 from Matches", new MatchMapper());
        return matches;
    }
    private static final class MatchMapper implements RowMapper<Match>{

        @Override
        public Match mapRow(ResultSet rs, int i) throws SQLException {
            Match match = new Match();
            match.setId(rs.getLong("id"));
            match.setName1(rs.getString("Name1"));
            match.setName2(rs.getString("Name2"));
            match.setScore1(rs.getInt("Score1"));
            match.setScore2(rs.getInt("Score2"));
            return match;
        }
    }
    public List<Match> findAll(String country){
        return this.jdbcTemplate.query("select id, Name1, Name2,Score1, Score2 from Matches m where m.country = ?", new Object[]{country}, new MatchMapper());
    }

}
