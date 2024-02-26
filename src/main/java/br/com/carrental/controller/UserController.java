package br.com.carrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.carrental.dto.request.CreateUserRequestDTO;
import br.com.carrental.dto.request.LoginRequestDTO;
import br.com.carrental.dto.response.CreateUserResponseDTO;
import br.com.carrental.dto.response.LoginResponseDTO;
import br.com.carrental.exception.UserAlreadyExistsException;
import br.com.carrental.model.ServerStatus;
import br.com.carrental.model.UserRole;
import br.com.carrental.model.entity.User;
import br.com.carrental.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ServerStatus serverStatus;
	
	@GetMapping(value = "/status")
	public ResponseEntity<String> status() {
		BodyBuilder bb = serverStatus.isSuccess()? ResponseEntity.ok(): ResponseEntity.badRequest();
	    return bb.build(); 
	}

	@GetMapping(value = "/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto){
		String token = userService.login(dto.getUsername(), dto.getPassword());
		LoginResponseDTO response = new LoginResponseDTO();
		response.setToken(token);
		return ResponseEntity.ok(response);	
	}
	
	@PostMapping(value = "/create-user")
	public ResponseEntity<CreateUserResponseDTO> createUser(@RequestBody CreateUserRequestDTO requestDTO) throws UserAlreadyExistsException{
		User user = new User();
		user.setUsername(requestDTO.getUsername());
		user.setPassword(new BCryptPasswordEncoder().encode(requestDTO.getPassword()));
		user.setRole(UserRole.valueOf(requestDTO.getRole()));
		user = userService.createUser(user);
		CreateUserResponseDTO responseDTO = new CreateUserResponseDTO();
		responseDTO.setId(user.getId());
		return ResponseEntity.ok(responseDTO);
	}
}
