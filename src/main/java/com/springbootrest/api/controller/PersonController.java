package com.springbootrest.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springbootrest.api.UserNotFoundException;
import com.springbootrest.api.entity.Person;
import com.springbootrest.api.service.PersonService;

@RestController
@RequestMapping("/api")
 @CrossOrigin(origins="http://localhost:4200")
public class PersonController {

	// just for log
	public static final Logger logger = LoggerFactory.getLogger(PersonController.class);// getting log details for
																						// current class

	@Autowired
	private PersonService personService;

	// get single person
	@GetMapping("/persons/{id}")
	public ResponseEntity<Person> getPersonId(@PathVariable int id) {
		// System.out.println("======================================");
		logger.info("Getting person id {}", id);

		Person person = personService.getPersonById(id);
		if (person == null) {
			throw new UserNotFoundException("person Id " + id + " is not found error.");
		}

		return new ResponseEntity<Person>(personService.getPersonById(id), HttpStatus.OK);

	}

	// this getmapping is for to list all the persons
	@GetMapping("/persons")
	public ResponseEntity<List<Person>> getAllList() {

		List<Person> getAll = personService.findAll();

		if (getAll.isEmpty()) {
			return new ResponseEntity<List<Person>>(HttpStatus.NO_CONTENT);
		}

		// if (getAll == null) {
		// return new ResponseEntity<List<Person>>(HttpStatus.NOT_FOUND);
		//
		// }
		// List<Person> lists = personService.findAll(); // findAll() returns Object so,
		// need type casting

		return new ResponseEntity<List<Person>>(getAll, HttpStatus.OK);
	}

	// create a person that means, post mapping
	@PostMapping("/persons")
	public ResponseEntity<Person> createPerson(@RequestBody Person person) {

		return new ResponseEntity<Person>(personService.save(person), HttpStatus.CREATED);
	}

	// for update, use putmapping
	// @PutMapping("/persons/{id}")
	@RequestMapping(value = "/persons/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Person> updatePerson(@PathVariable("id") int id, @RequestBody Person person) {

		logger.info("Getting person id {}", id);
		Person updatedPerson = personService.getPersonById(id);

		if (updatedPerson == null) {
			logger.error("unable to update");
			throw new UserNotFoundException("update failed. user id " + id + " not found");
		}

		// updatedPerson.setName("Sujan Shrestha - updated");
		// updatedPerson.setAddress("Kathmandu -updated");
		// updatedPerson.setEmail("Sujansth@hotmail.com - updated");
		// updatedPerson.setActive(true);
		// updatedPerson.setPhone("1234454 - updated");

		updatedPerson.setName(person.getName());
		updatedPerson.setAddress(person.getAddress());
		updatedPerson.setEmail(person.getEmail());
		updatedPerson.setPhone(person.getPhone());
		updatedPerson.setActive(person.isActive());

		return new ResponseEntity<Person>(personService.update(updatedPerson), HttpStatus.OK);
	}

	// for delete, using deletemapping
	@DeleteMapping("/persons/{id}")
	// @ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Person> delete(@PathVariable int id) {
		personService.delete(id);
		return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
		// return type void gardaa feri reponse status diney !!
	}

	@DeleteMapping("/persons")
	public ResponseEntity<Person> deleteAll() {
		personService.deleteAllPersons();
		return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
	}
}
