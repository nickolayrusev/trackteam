package com.nrusev.repository;

import com.nrusev.domain.Event;
import com.nrusev.domain.Round;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Nikolay Rusev on 17.2.2017 г..
 */
public interface RoundRepository extends CrudRepository<Round,Long>{
    List<Round> findByEventAndPos(Event event, Long pos);
}
