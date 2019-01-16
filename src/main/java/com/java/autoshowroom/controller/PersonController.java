package com.java.autoshowroom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.autoshowroom.model.Person;
import com.java.autoshowroom.service.PersonService;

@RestController
@RequestMapping(value="/person")
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ResponseEntity<Person> savePerson(@RequestBody Person person){
		Person personSaved = personService.save(person);
		
		return ResponseEntity.ok(personSaved);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Person> findPersonById(@PathVariable long id){
		Person person = personService.findById(id);
		
		return ResponseEntity.ok(person);
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public ResponseEntity<List<Person>> getPersons(){
		List<Person> persons = personService.findAll();
		
		return ResponseEntity.ok(persons);
	}

	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ResponseEntity<Person> updatePerson(@RequestBody Person person){
		Person personUpdated = personService.update(person);
		
		return ResponseEntity.ok(personUpdated);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletePerson(@RequestBody Person person){
		personService.delete(person);
		
		return ResponseEntity.ok().build();
	}
}
