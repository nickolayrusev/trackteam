package com.nrusev.repository;

import com.nrusev.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Nikolay Rusev on 24.10.2016 г..
 */
public interface UserRepository extends CrudRepository<User,Long>{
    User findByUserName(String userName);
}
