package com.java.autoshowroom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.autoshowroom.data.CarRepository;
import com.java.autoshowroom.exception.ResourceNotFoundException;
import com.java.autoshowroom.model.Car;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;
	
	@Override
	public Car save(Car car) {
		return carRepository.save(car);
	}

	@Override
	public List<Car> findAll() {
		return carRepository.findAll();
	}

	@Override
	public Car findById(long id) {
		Car car = carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, "araç"));
		
		return car;
	}

	@Override
	public List<Car> findByModelName(String modelName) {
		return carRepository.findByModelName(modelName);
	}

	@Override
	public Car update(Car car) {
		
		carRepository.findById(car.getId()).orElseThrow(() -> new ResourceNotFoundException(car.getId(), "araç"));
		
		return carRepository.save(car);
	}

}
