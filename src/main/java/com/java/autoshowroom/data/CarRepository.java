package com.java.autoshowroom.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.autoshowroom.model.Car;

public interface CarRepository extends JpaRepository<Car, Long>{
	List<Car> findByModelName(String modelName);
}
