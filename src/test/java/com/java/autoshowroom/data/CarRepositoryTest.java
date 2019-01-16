package com.java.autoshowroom.data;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.java.autoshowroom.model.Car;

@RunWith(value=SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class CarRepositoryTest {

	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private CarRepository carRepository;
	
	@Test
	public void findById() {
		Car car = new Car();
		car.setModelName("test");
		car.setDateOfConstruction("2000-01-01");
		
		Car savedInDb = testEntityManager.persist(car);
		
		Optional<Car> getFromDb = carRepository.findById(savedInDb.getId());
		
		assertThat(getFromDb.isPresent()).isEqualTo(true);
		assertThat(getFromDb.get().getModelName()).isEqualTo("test");
		assertThat(getFromDb.get().getDateOfConstruction()).isEqualTo("2000-01-01");
	}

	@Test
	public void findById_notFound() {
		Optional<Car> getFromDb = carRepository.findById(1L);
		
		assertThat(getFromDb.isPresent()).isEqualTo(false);
	}

	
	@Test
	public void save() {
		Car car = new Car();
		car.setModelName("test");
		car.setDateOfConstruction("2000-01-01");
		
		Car savedInDb = carRepository.save(car);
		
		Car getFromDb = testEntityManager.find(Car.class, savedInDb.getId());
		
		assertThat(getFromDb.getId()).isEqualTo(savedInDb.getId());
		assertThat(getFromDb.getModelName()).isEqualTo(car.getModelName());
		assertThat(getFromDb.getDateOfConstruction()).isEqualTo(car.getDateOfConstruction());
	}
	
	@Test
	public void findAll() {
		Car car1 = new Car();
		car1.setModelName("test1");
		car1.setDateOfConstruction("2001-01-01");
		testEntityManager.persist(car1);
		
		Car car2 = new Car();
		car2.setModelName("test2");
		car2.setDateOfConstruction("2002-01-01");
		testEntityManager.persist(car2);

		List<Car> cars = carRepository.findAll();
		
		assertThat(cars.size()).isEqualTo(2);
	}
}
