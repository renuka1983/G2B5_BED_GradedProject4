package com.greatlearning.emapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.emapi.dto.JWTAuthResponse;
import com.greatlearning.emapi.dto.UserAddDto;
import com.greatlearning.emapi.dto.UserDto;
import com.greatlearning.emapi.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	private AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	// Build Login REST API
	@PostMapping(value = { "/login", "/signin" })
	public ResponseEntity<JWTAuthResponse> login(@RequestBody UserDto userDto) {
		String token = authService.login(userDto);

		JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
		jwtAuthResponse.setAccessToken(token);

		System.out.println("UserDto in login service" + userDto.toString());
		return ResponseEntity.ok(jwtAuthResponse);
	}

	// Build Register REST API
	@PostMapping(value = { "/register", "/signup" })
	public ResponseEntity<String> register(@RequestBody UserAddDto userAddDto) {
		String response = authService.register(userAddDto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
