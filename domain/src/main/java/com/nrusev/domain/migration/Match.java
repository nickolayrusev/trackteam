package com.nrusev.domain.migration;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Nikolay Rusev on 6.10.2016 г..
 */
@Entity(name = "Matches")
public class Match {
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
