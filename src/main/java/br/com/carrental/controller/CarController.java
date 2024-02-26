package br.com.carrental.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.carrental.model.entity.Car;
import br.com.carrental.service.CarService;

@RestController
@RequestMapping("/car")
public class CarController {
	
	@Autowired
	private CarService carService;
	
	@GetMapping(value = "/{plate}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Car findByPlate(@PathVariable(value = "plate") String plate) {
		return carService.findByPlate(plate);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Car> findAll() {
		return carService.findAll();
	}
	
	@GetMapping(value = "/available-for-sale", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Car> findAvailableForSale() {
		return carService.findAvailableForSale();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Car create(@RequestBody Car data) {
		return carService.create(data);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Car update(@RequestBody Car data) throws Exception {
		return carService.update(data);
	}
}
