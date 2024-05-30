package com.greatlearning.emapi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.greatlearning.emapi.dao.UserRepository;
import com.greatlearning.emapi.dto.RoleDto;
import com.greatlearning.emapi.dto.UserAddDto;
import com.greatlearning.emapi.dto.UserDto;
import com.greatlearning.emapi.exception.EmployeeAPIException;
import com.greatlearning.emapi.security.JwtTokenProvider;
import com.greatlearning.emapi.security.entity.Role;
import com.greatlearning.emapi.security.entity.User;
import com.greatlearning.emapi.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	private AuthenticationManager authenticationManager;
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private JwtTokenProvider jwtTokenProvider;
	private ModelMapper mapper;

	public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
			PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, ModelMapper mapper) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
		this.mapper = mapper;
	}

	@Override
	public String login(UserDto userDto) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtTokenProvider.generateToken(authentication);

		return token;
	}

	private Role mapDtoToEntity(RoleDto roleDto) {
		Role role = mapper.map(roleDto, Role.class);
		return role;
	}

	@Override
	public String register(UserAddDto userAddDto) {

		System.out.println("UserAddDto" + userAddDto.toString());
		// add check for username exists in database
		if (userRepository.existsByUsername(userAddDto.getUserDto().getUsername())) {
			throw new EmployeeAPIException(HttpStatus.BAD_REQUEST, "Username already exists!.");
		}

		User user = new User();
		user.setUsername(userAddDto.getUserDto().getUsername());
		user.setPassword(passwordEncoder.encode(userAddDto.getUserDto().getPassword()));

		List<Role> roles = new ArrayList<>();
		for (RoleDto roleDtos : userAddDto.getRoleDto()) {
			roles.add(mapDtoToEntity(roleDtos));
		}
		
		user.setRoles(roles);
		userRepository.save(user);

		return "User registered successfully!.";
	}
}
