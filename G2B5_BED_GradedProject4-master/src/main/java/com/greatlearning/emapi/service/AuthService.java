package com.greatlearning.emapi.service;

import com.greatlearning.emapi.dto.UserAddDto;
import com.greatlearning.emapi.dto.UserDto;

public interface AuthService {

	String login(UserDto userDto);

	String register(UserAddDto userAddDto);
}
