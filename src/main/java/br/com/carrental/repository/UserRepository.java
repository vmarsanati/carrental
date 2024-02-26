package br.com.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.carrental.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	UserDetails findByUsername(String username);
	
}
