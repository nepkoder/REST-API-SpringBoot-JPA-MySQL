package com.springbootrest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootrest.api.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

	Person getPersonById(int id);

}
