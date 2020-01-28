package com.cognizant.fms.loginservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.fms.loginservice.model.AuthRequest;
import com.cognizant.fms.loginservice.model.AuthResponse;
import com.cognizant.fms.loginservice.repository.UserRepository;
import com.cognizant.fms.loginservice.security.PBKDF2Encoder;
import com.cognizant.fms.loginservice.service.UserStubService;
import com.cognizant.fms.loginservice.util.JWTUtil;

import reactor.core.publisher.Mono;


@RestController
public class AuthController {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private PBKDF2Encoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Mono<ResponseEntity<?>> login(@RequestBody AuthRequest ar) {
		return userRepository.findById(ar.getUsername()).map((userDetails) -> {
			if (passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword())) {
				return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails)));
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	

}