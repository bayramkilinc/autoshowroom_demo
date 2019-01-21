package com.java.autoshowroom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.autoshowroom.model.Car;
import com.java.autoshowroom.service.CarService;

@RestController
@RequestMapping(value="/car")
public class CarController {

	@Autowired
	private CarService carService; 
	
	@RequestMapping(value="/all", method= RequestMethod.GET)
	public ResponseEntity<List<Car>> getCars(){
		List<Car> cars = carService.findAll();
		return ResponseEntity.ok(cars);
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ResponseEntity<Car> saveCar(@RequestBody Car car){
		Car result = carService.save(car);
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Car> getCarById(@PathVariable("id") long id){
		Car car = carService.findById(id);

		return ResponseEntity.ok(car);
	}
	
	@RequestMapping(value="/model={modelName}", method=RequestMethod.GET)
	public ResponseEntity<List<Car>> getCarByModel(@PathVariable("modelName") String modelName){
		List<Car> cars = carService.findByModelName(modelName);

		return ResponseEntity.ok(cars);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ResponseEntity<Car> updateCar(@RequestBody Car car){
		Car carUpdated = carService.update(car);
		
		return ResponseEntity.ok(carUpdated);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCar(@RequestBody Car car){
		carService.delete(car);
		
		return ResponseEntity.ok().build();
	}
	
}
