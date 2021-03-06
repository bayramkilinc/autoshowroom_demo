package com.java.autoshowroom.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.autoshowroom.model.Car;
import com.java.autoshowroom.model.Person;
import com.java.autoshowroom.service.CarService;

@RestController
public class DummyValues {

	@Autowired
	private CarService carService;
	
	@RequestMapping(value="/setDummyValues", method= RequestMethod.GET)
	public ResponseEntity<List<Car>> setDummyValues(){

		Person ozgur = new Person();
		ozgur.setFirstName("Mehmet Ozgur");
		ozgur.setLastName("Kutlu");
		
		Person bayram = new Person();
		bayram.setFirstName("Bayram");
		bayram.setLastName("Kilinc");
		
		Person serkan = new Person();
		serkan.setFirstName("Serkan");
		serkan.setLastName("Himmetoglu");

		
		List<Car> cars = new ArrayList<>();

		Car car1 = new Car();
		car1.setModelName("118i");
		car1.setPlate("42CCC55");
		car1.setDateOfConstruction("2015-11-01");
		car1.setPerson(ozgur);		
		carService.save(car1);
		cars.add(car1);

		Car car4 = new Car();
		car4.setModelName("118i");
		car4.setPlate("34ABC55");
		car4.setDateOfConstruction("2011-11-01");
		car4.setPerson(ozgur);		
		carService.save(car4);
		cars.add(car4);
		
		Car car2 = new Car();
		car2.setModelName("Jetta");
		car2.setPlate("46ABC55");
		car2.setDateOfConstruction("2014-11-01");
		car2.setPerson(bayram);		
		carService.save(car2);
		cars.add(car2);
		
		Car car3 = new Car();
		car3.setModelName("Golf");
		car3.setPlate("81ABC55");
		car3.setDateOfConstruction("2014-09-01");
		car3.setPerson(serkan);		
		carService.save(car3);
		cars.add(car3);
				
		return ResponseEntity.ok(cars);
	}
}
