package com.nrusev.repository;

import com.nrusev.domain.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by nikolayrusev on 3/21/16.
 */
public interface CountryRepository extends CrudRepository<Country,Long>{
 	@Query("select distinct c from Country c inner join fetch c.leagues order by c.name")
	List<Country> findAvailableCountries();

	List<Country> findByName(String name);
}
