package br.com.carrental.repository;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.carrental.model.UserRole;
import br.com.carrental.model.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class UserRepositoryTest {

	@Autowired
	private UserRepository repository;

	private static String testUsername = "testUsername";

	@Test
	@Order(1)
	@DisplayName("Create User")
	void createUser() {
		User user = saveUser();
		assertTrue(user.getId() > 0);
	}
	
	@DisplayName("Login success")
	@Test
	@Order(2)
	void loginSuccess() {
		saveUser();
		UserDetails user = repository.findByUsername(testUsername);
		assertNotNull(user);
	}

	@DisplayName("Login fail")
	@Test
	void loginFail() {
		saveUser();
		UserDetails user = repository.findByUsername(testUsername + "123");
		assertNull(user);
	}
	
	private User saveUser() {
		User user = new User();
		user.setUsername(testUsername);
		user.setPassword("123123");
		user.setRole(UserRole.SUPERADMIN);
		return repository.save(user);
		
	}

}
 