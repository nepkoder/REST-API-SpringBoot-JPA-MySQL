package com.springbootrest.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.springbootrest.api.entity.Person;
import com.springbootrest.api.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public List<Person> findAll() {
		return personRepository.findAll();
	}

	public Person save(Person person) {
		return personRepository.save(person);
	}

	public Person getPersonById(int personId) {
		return personRepository.getPersonById(personId);
	}

	public void delete(int id) {

		personRepository.deleteById(id);

	}

	public Person update(Person person) {

		// person = getPersonById(id);
		return personRepository.save(person);
	}

	public void deleteAllPersons() {
		personRepository.deleteAll();
	}
}
