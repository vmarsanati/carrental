package br.com.carrental.repository;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import br.com.carrental.model.entity.Brand;
import br.com.carrental.model.entity.Car;
import br.com.carrental.model.entity.CarVariant;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CarRepositoryTest {

	@Autowired
	private CarRepository repository;

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private CarVariantRepository carVariantRepository;
	
	@Test
	@DisplayName("Find by plate")
	public void testFindByPlate() {
		CarVariant carVariant = createCarVariant();
		saveCar(carVariant, 1, 1);
		Car car = repository.findByPlate("abs-1234");
		assertNotNull(car);
	}	
		
	@Test
	@DisplayName("Find availabe for sale")
	void testFindByYearLessThanOrKilometerageGreaterThan() {
		
		CarVariant carVariant = createCarVariant();
		
		int maxYear = 2000;
		int minKm	= 40000;
		
		int yearFalse = maxYear + 1;
		int yearTrue = maxYear - 1;
		int kmFalse = minKm - 1;
		int kmTrue = minKm + 1;
		
		Car yearFalse_kmFalse 	= saveCar(carVariant, yearFalse, kmFalse);
		Car yearFalse_kmTrue 	= saveCar(carVariant, yearFalse, kmTrue);
		Car yearTrue_kmFalse 	= saveCar(carVariant, yearTrue, kmFalse);
		Car yearTrue_kmTrue 	= saveCar(carVariant, yearTrue, kmTrue);
		
		boolean has_yearFalse_kmFalse 	= false;
		boolean has_yearFalse_kmTrue 	= false;
		boolean has_yearTrue_kmFalse 	= false;
		boolean has_yearTrue_kmTrue 	= false;
		
		List<Car> cars = repository.findByYearLessThanOrKilometerageGreaterThan(maxYear, minKm);
		for(Car car: cars) {
			if(car.getId() == yearFalse_kmFalse.getId()) {
				has_yearFalse_kmFalse = true;
				continue;
			}
			
			if(car.getId() == yearFalse_kmTrue.getId()) {
				has_yearFalse_kmTrue = true;
				continue;
			}
			
			if(car.getId() == yearTrue_kmFalse.getId()) {
				has_yearTrue_kmFalse = true;
				continue;
			}
			
			if(car.getId() == yearTrue_kmTrue.getId()) {
				has_yearTrue_kmTrue = true;
				continue;
			}
		}
		
		assertFalse(has_yearFalse_kmFalse);
		assertTrue(has_yearFalse_kmTrue);
		assertTrue(has_yearTrue_kmFalse);
		assertTrue(has_yearTrue_kmTrue);
	}
	
	private CarVariant createCarVariant() {
		Brand brand = new Brand();
		brand.setDescription("Test brand");
		brand = brandRepository.save(brand);
		
		CarVariant carVariant = new CarVariant();
		carVariant.setDescription("Test variant ");
		carVariant.setBrand(brand);
		return carVariantRepository.save(carVariant);
			
	}
	
	private Car saveCar(CarVariant carVariant, int year, int km) {
		Car car = new Car();
		car.setColor("Test Color");
		car.setPlate("abs-1234");
		car.setKilometerage(km);
		car.setYear(year);
		car.setCarVariant(carVariant);		
		return repository.save(car);	
	}

}
 