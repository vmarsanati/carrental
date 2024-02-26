package br.com.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carrental.model.entity.CarVariant;

public interface CarVariantRepository extends JpaRepository<CarVariant, Long>{
	
}
