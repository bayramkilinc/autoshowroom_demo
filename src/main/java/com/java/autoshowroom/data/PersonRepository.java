package com.java.autoshowroom.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.autoshowroom.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
