package br.com.carrental.service;


import static org.mockito.ArgumentMatchers.anyString;

import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.carrental.exception.UserAlreadyExistsException;
import br.com.carrental.model.UserRole;
import br.com.carrental.model.entity.User;
import br.com.carrental.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepository repository;

	@InjectMocks
	private UserService service;

	private User user;
	
	@BeforeEach
	public void setup() {
		user = new User();
		user.setUsername("testUsername");
		user.setPassword("testPassword");
		user.setRole(UserRole.SUPERADMIN);
	}
	
	@DisplayName("Save User Success")
	@Test
	void createUserSuccess() throws UserAlreadyExistsException {
		given(repository.findByUsername(anyString())).willReturn(null);
		given(repository.save(user)).willReturn(user);
		service.createUser(user);
	}

	@DisplayName("Save User Fail")
	@Test
	void createUserFail() throws UserAlreadyExistsException {
		given(repository.findByUsername(anyString())).willReturn(user);
		given(repository.save(user)).willReturn(user);
		service.createUser(user);
	}
}
