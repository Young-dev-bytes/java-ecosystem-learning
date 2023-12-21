package com.in28minutes.database.springdata;

import com.in28minutes.database.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository
public interface PersonSpringDataRepository extends JpaRepository<Person, Integer> {
}
