package br.com.carrental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carrental.model.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

	List<Car> findByYearLessThanOrKilometerageGreaterThan(int year, int kilometerage);
	Car findByPlate(String plate);
} 
