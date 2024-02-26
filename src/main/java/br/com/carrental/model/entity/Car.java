package br.com.carrental.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "car")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;		
	private String color;	
	private String plate;
	private int year;
	private int kilometerage;
	
	@OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "car_variant_id", referencedColumnName = "id")
	private CarVariant carVariant;

	public Long getId() {
		return id;
	}

	public int getYear() {
		return year;
	}

	public CarVariant getCarVariant() {
		return carVariant;
	}

	public int getKilometerage() {
		return kilometerage;
	}

	public String getColor() {
		return color;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setCarVariant(CarVariant carVariant) {
		this.carVariant = carVariant;
	}

	public void setKilometerage(int kilometerage) {
		this.kilometerage = kilometerage;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
	
}
