package br.com.carrental.controller;


import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.BDDMockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.carrental.model.ServerStatus;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@Mock
	private ServerStatus serverStatus;
	
	@InjectMocks
	UserController controller;
	
	@Test
	void testServerStatus_whenSuccess_shouldBeOK() {
		given(serverStatus.isSuccess()).willReturn(true);
		ResponseEntity<String> status = controller.status();
		assertThat(status.getStatusCode(), is(HttpStatus.OK));
	}
	
	@Test
	void testServerStatus_whenFail_shouldBeBadRequest() {
		given(serverStatus.isSuccess()).willReturn(false);
		ResponseEntity<String> status = controller.status();
		assertThat(status.getStatusCode(), is(HttpStatus.BAD_REQUEST));
	}
}
