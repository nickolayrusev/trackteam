package com.nrusev.repository;

import com.nrusev.domain.Season;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by nikolayrusev on 2/23/16.
 */
public interface SeasonRepository extends CrudRepository<Season,Long>{
    List<Season> findByTitle(String title);
}
