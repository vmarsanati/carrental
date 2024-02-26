package br.com.carrental.service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.carrental.model.entity.Car;
import br.com.carrental.repository.CarRepository;

@Service
public class CarService {

	@Autowired
	private CarRepository repository;
	
	private static final int KM_FOR_SALE = 40000;
	
	public Car create(Car car) {
		return repository.save(car);
	}
	
	public Car update(Car car) throws Exception {
		Car registredCar = repository.findById(car.getId()).get();
		if(registredCar.getKilometerage() > car.getKilometerage()) {
			throw new Exception("Kilometerage cannot e lower");
		}
		registredCar.setKilometerage(car.getKilometerage());
		return repository.save(registredCar);
	}
	
	public Car findByPlate(String plate) {
		return repository.findByPlate(plate);
	}
	
	public List<Car> findAvailableForSale() {
		Calendar cal = GregorianCalendar.getInstance();
		return repository.findByYearLessThanOrKilometerageGreaterThan(cal.get(Calendar.YEAR), KM_FOR_SALE);
	}
	
	public List<Car> findAll() {
		return repository.findAll(Sort.by(Sort.Direction.ASC, "year"));
	}

}

