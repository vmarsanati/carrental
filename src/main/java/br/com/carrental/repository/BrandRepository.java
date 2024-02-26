package br.com.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carrental.model.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long>{

}
