package com.java.autoshowroom.service;

import java.util.List;

import com.java.autoshowroom.model.Person;

public interface PersonService {

	Person save(Person person);
	Person findById(long id);
	List<Person> findAll();
	Person update(Person person);
	void delete(Person person);
}
