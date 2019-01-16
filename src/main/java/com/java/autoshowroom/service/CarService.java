package com.java.autoshowroom.service;

import java.util.List;

import com.java.autoshowroom.model.Car;

public interface CarService {

	Car save(Car car);
	Car update(Car car);
	List<Car> findAll();
	Car findById(long id);
	List<Car> findByModelName(String modelName);
}
