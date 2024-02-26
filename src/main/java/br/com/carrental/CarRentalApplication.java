package br.com.carrental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.carrental.model.ServerStatus;

@SpringBootApplication
public class CarRentalApplication {

	public static void main(String[] args) {
		new ServerStatus().setSuccess(true);
		SpringApplication.run(CarRentalApplication.class, args);
	}

}
