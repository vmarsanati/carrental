package br.com.carrental.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.carrental.model.entity.CarVariant;
import br.com.carrental.repository.CarVariantRepository;

@Service
public class CarVariantService {

	@Autowired
	private CarVariantRepository repository;
	
	public CarVariant create(CarVariant brand) {
		return repository.save(brand);
	}
	
	public List<CarVariant> findAll() {
		return repository.findAll();
	}
	

}
