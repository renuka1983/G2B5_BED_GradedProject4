package com.greatlearning.emapi.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserAddDto {

	private UserDto userDto;
	private List<RoleDto> roleDto;

}
