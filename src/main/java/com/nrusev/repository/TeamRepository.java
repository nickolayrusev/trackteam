package com.nrusev.repository;

import com.nrusev.domain.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by nikolayrusev on 2/22/16.
 */
public interface TeamRepository  extends CrudRepository<Team,Long>{
    List<Team> findByTitle(String title);
    List<Team> findAll();
}
