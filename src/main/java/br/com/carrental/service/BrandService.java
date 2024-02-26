package br.com.carrental.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.carrental.model.entity.Brand;
import br.com.carrental.repository.BrandRepository;

@Service
public class BrandService {
	
	@Autowired
	private BrandRepository repository;
	
	public Brand create(Brand brand) {
		return repository.save(brand);
	}
	
	public List<Brand> findAll() {
		return repository.findAll();
	}
}
