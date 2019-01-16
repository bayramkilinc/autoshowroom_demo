package com.java.autoshowroom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.autoshowroom.data.PersonRepository;
import com.java.autoshowroom.exception.ResourceNotFoundException;
import com.java.autoshowroom.model.Person;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository; 
	
	@Override
	public Person save(Person person) {
		return personRepository.save(person);
	}

	@Override
	public Person findById(long id) {
		return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id,"kişi"));
	}

	@Override
	public List<Person> findAll() {
		return personRepository.findAll();
	}

	@Override
	public Person update(Person person) {
		personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException(person.getId(),"kişi"));
		
		return personRepository.save(person);
	}

	@Override
	public void delete(Person person) {
		personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException(person.getId(),"kişi"));

		personRepository.delete(person);
	}

}
