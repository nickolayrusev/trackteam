package com.nrusev.repository;

import com.nrusev.domain.Event;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by nikolayrusev on 2/20/17.
 */
public interface EventRepository extends CrudRepository<Event, Long>{
}
