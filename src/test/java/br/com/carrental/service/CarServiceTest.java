package br.com.carrental.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import br.com.carrental.model.entity.Brand;
import br.com.carrental.model.entity.Car;
import br.com.carrental.model.entity.CarVariant;
import br.com.carrental.repository.BrandRepository;
import br.com.carrental.repository.CarRepository;
import br.com.carrental.repository.CarVariantRepository;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {
	@Mock
	private CarRepository repository;

	@Mock
	private BrandRepository brandRepository;

	@Mock
	private CarVariantRepository carVariantRepository;
	

	@InjectMocks
	private CarService service;
	
	@Test
	@DisplayName("Prevent update lower kilometerage")
	public void testUpdate() {
		Exception e = assertThrows(Exception.class, () -> {
			Brand brand = new Brand();
			brand.setDescription("Test brand");
			brand = brandRepository.save(brand);
			
			CarVariant carVariant = new CarVariant();
			carVariant.setDescription("Test variant ");
			carVariant.setBrand(brand);
			carVariant = carVariantRepository.save(carVariant);
			
			Car car = new Car();
			car.setColor("Test Color");
			car.setPlate("abs-1234");
			car.setKilometerage(30000);
			car.setYear(2000);
			car.setCarVariant(carVariant);		
			
			given(repository.findById(null)).willReturn(Optional.of(car));
			given(repository.save(car)).willReturn(car);
			car = repository.save(car);
			
			
			Car entityToUpdate = new Car();
			entityToUpdate.setKilometerage(car.getKilometerage() - 100);
			service.update(entityToUpdate);	
		});
		assertEquals("Kilometerage cannot e lower", e.getMessage());
	}	
	
}
