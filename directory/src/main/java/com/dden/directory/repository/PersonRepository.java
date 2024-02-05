package com.dden.directory.repository;

import com.dden.directory.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Denys Parshutkin
 * @version 1.0.0
 */
public interface PersonRepository extends CrudRepository<Person, String> {
    public Person findByEmailIgnoreCase(@Param("email") String email);
}
