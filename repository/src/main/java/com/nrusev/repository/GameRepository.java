package com.nrusev.repository;

import com.nrusev.domain.Game;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Nikolay Rusev on 27.10.2016 г..
 */
public interface GameRepository extends CrudRepository<Game, Long> {
}
