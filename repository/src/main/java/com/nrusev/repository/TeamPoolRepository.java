package com.nrusev.repository;

import com.nrusev.domain.TeamPool;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Nikolay Rusev on 24.10.2016 г..
 */
public interface TeamPoolRepository extends CrudRepository<TeamPool,Long>{

    @Query("select p from TeamPool p inner join  p.user u where u.userName = :userName")
    List<TeamPool> findAllByUserName(@Param("userName") String userName);
}
