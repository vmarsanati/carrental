package br.com.carrental.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.carrental.exception.UserAlreadyExistsException;

import br.com.carrental.model.entity.User;
import br.com.carrental.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	
	@Autowired
	private AuthenticationManager authenicationManager;
	
	@Autowired
	private UserRepository repository;

	@Value("${api.security.tokenSecret}")
	private String tokenSecret;
	
	@Value("${api.security.expirationHours}")
	private int expirationHours;
	
	@Value("${api.security.expirationOffset}")
	private String expirationOffset;
	
	@Value("${api.security.issuer}")
	private String issuer;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByUsername(username);
	}

	public String login(String username, String password){
		Authentication auth = createAuthentication(username, password);
		return generateToken((User) auth.getPrincipal());
	}
	
	public User createUser(User user) throws UserAlreadyExistsException {
		if(repository.findByUsername(user.getUsername()) != null) {
			throw new UserAlreadyExistsException();
		}		
		return repository.save(user);
	}
	
	public String generateToken(User user) {
		return JWT.create()
				.withIssuer(issuer)
				.withSubject(user.getUsername())
				.withExpiresAt(createExpiresAt())
				.sign(createAlgorithm());
	}
	
	public String validateToken(String token) {
		return JWT.require(createAlgorithm())
				.withIssuer(issuer)
				.build()
				.verify(token)
				.getSubject();
	}
	
	private Algorithm createAlgorithm() {
		return Algorithm.HMAC256(tokenSecret);
	}
	
	private Authentication createAuthentication(String username, String password) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		return authenicationManager.authenticate(token);
	}
	
	private Instant createExpiresAt() {
		return LocalDateTime.now().plusHours(expirationHours).toInstant(ZoneOffset.of(expirationOffset));
	}
}
