package com.nrusev.service;

import com.nrusev.domain.Pool;
import com.nrusev.domain.Team;
import com.nrusev.repository.PoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nikolayrusev on 2/25/16.
 */
@Service
public class PoolService {
    private final PoolRepository poolRepository;

    @Autowired
    public PoolService(PoolRepository poolRepository){
        this.poolRepository = poolRepository;
    }

}
