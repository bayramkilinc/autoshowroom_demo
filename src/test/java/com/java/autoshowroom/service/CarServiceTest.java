package com.java.autoshowroom.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.java.autoshowroom.data.CarRepository;
import com.java.autoshowroom.exception.ResourceNotFoundException;
import com.java.autoshowroom.model.Car;

@RunWith(value=SpringRunner.class)
@SpringBootTest
public class CarServiceTest {

	@Autowired
	private CarService carService;

	@MockBean
	private CarRepository carRepository;

	@Test
	public void save() {
		Car car = new Car();
		car.setModelName("test");
		car.setDateOfConstruction("2000-01-01");

		Mockito.when(carRepository.save(Mockito.any(Car.class))).thenAnswer(new Answer<Car>() {
			@Override
			public Car answer(InvocationOnMock invocation) throws Throwable {
				Car car = (Car)invocation.getArguments()[0];
				Car savedInDb = new Car();
				savedInDb.setId(1L);
				savedInDb.setModelName(car.getModelName());
				savedInDb.setDateOfConstruction(car.getDateOfConstruction());
				return savedInDb;
			}
		});

		Car savedWithService = carService.save(car);

		assertThat(savedWithService.getId()).isEqualTo(1L);
		assertThat(savedWithService.getModelName()).isEqualTo(car.getModelName());
		assertThat(savedWithService.getDateOfConstruction()).isEqualTo(car.getDateOfConstruction());
	}

	@Test
	public void findAll() {
		Car car1 = new Car();
		car1.setModelName("test1");
		car1.setDateOfConstruction("2001-01-01");
		
		Car car2 = new Car();
		car2.setModelName("test2");
		car2.setDateOfConstruction("2002-01-01");

		List<Car> cars = new ArrayList<>();
		cars.add(car1);
		cars.add(car2);
		
		Mockito.when(carRepository.findAll()).thenReturn(cars);
		
		List<Car> getFromService = carService.findAll();
		
		assertThat(getFromService.size()).isEqualTo(2);
	}
	
	@Test
	public void findById() {
		Car car1 = new Car();
		car1.setId(1L);
		car1.setModelName("test1");
		car1.setDateOfConstruction("2001-01-01");

		Mockito.when(carRepository.findById(1L)).thenReturn(Optional.of(car1));
		
		Car getFromService = carService.findById(1L);
		
		assertThat(getFromService.getId()).isEqualTo(car1.getId());
		assertThat(getFromService.getModelName()).isEqualTo(car1.getModelName());
		assertThat(getFromService.getDateOfConstruction()).isEqualTo(car1.getDateOfConstruction());
	}
	
	@Test(expected=ResourceNotFoundException.class)
	public void findById_resourceNotFoundException() {
		carService.findById(-1L);
	}
	
	@Test
	public void update() {
		Car carOld = new Car();
		carOld.setId(1L);
		carOld.setModelName("test1");
		carOld.setPlate("11ABC22");
		carOld.setDateOfConstruction("2001-01-01");
		
		Mockito.when(carRepository.findById(carOld.getId())).thenReturn(Optional.of(carOld));
		
		Car carNew = new Car();
		carNew.setId(1L);
		carNew.setModelName("test1_updated");
		carNew.setPlate("11ABC99");
		carNew.setDateOfConstruction("2011-01-01");
		
		Mockito.when(carRepository.save(carNew)).thenReturn(carNew);
		
		Car carUpdated = carService.update(carNew);
		
		assertThat(carUpdated.getId()).isEqualTo(carNew.getId());
		assertThat(carUpdated.getModelName()).isEqualTo(carNew.getModelName());
		assertThat(carUpdated.getPlate()).isEqualTo(carNew.getPlate());
		assertThat(carUpdated.getDateOfConstruction()).isEqualTo(carNew.getDateOfConstruction());		
	}
	
	@Test(expected=ResourceNotFoundException.class)
	public void update_resourceNotFound() {
		Car car = new Car();
		car.setId(-1L);

		carService.update(car);
	}
	
	@Test
	public void findByModelName() {
		Car car1 = new Car();
		car1.setId(1L);
		car1.setModelName("test1");
		car1.setPlate("11ABC22");
		car1.setDateOfConstruction("2001-01-01");

		Car car2 = new Car();
		car2.setId(2L);
		car2.setModelName("test1");
		car2.setPlate("11ABC33");
		car2.setDateOfConstruction("2011-01-01");
		
		List<Car> cars = new ArrayList<>();
		cars.add(car1);
		cars.add(car2);
		 
		String modelName = "test1";
		Mockito.when(carRepository.findByModelName(modelName)).thenReturn(cars);
		
		List<Car> carResultList = carService.findByModelName(modelName);
		
		assertThat(carResultList.size()).isEqualTo(cars.size());
		assertThat(carResultList.stream().allMatch(x-> x.getModelName().equals(modelName))).isEqualTo(true);
		assertThat(carResultList.stream().anyMatch(x-> x.getPlate().equals(car1.getPlate()))).isEqualTo(true);
		assertThat(carResultList.stream().anyMatch(x-> x.getPlate().equals(car2.getPlate()))).isEqualTo(true);
	}

}
