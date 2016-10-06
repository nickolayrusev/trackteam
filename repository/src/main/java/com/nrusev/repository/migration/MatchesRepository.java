package com.nrusev.repository.migration;

import com.nrusev.domain.migration.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by Nikolay Rusev on 5.10.2016 г..
 */
public interface MatchesRepository extends CrudRepository<Match,Long> {

}
