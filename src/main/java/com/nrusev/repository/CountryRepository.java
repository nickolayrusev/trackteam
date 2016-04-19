package com.nrusev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nrusev.domain.Country;

/**
 * Created by nikolayrusev on 3/21/16.
 */
public interface CountryRepository extends CrudRepository<Country,Long>{
 	@Query("select distinct c from Country c inner join fetch c.leagues order by c.name")
	List<Country> findAvailableCountries();
}
