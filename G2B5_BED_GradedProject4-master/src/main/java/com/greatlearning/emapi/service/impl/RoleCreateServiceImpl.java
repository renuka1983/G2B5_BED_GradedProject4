package com.greatlearning.emapi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.greatlearning.emapi.dao.RoleRepository;
import com.greatlearning.emapi.dto.RoleDto;
import com.greatlearning.emapi.security.entity.Role;
import com.greatlearning.emapi.service.RoleCreateService;

@Service
public class RoleCreateServiceImpl implements RoleCreateService {

	private RoleRepository roleRepository;
	private ModelMapper mapper;

	public RoleCreateServiceImpl(RoleRepository roleRepository, ModelMapper mapper) {
		this.roleRepository = roleRepository;
		this.mapper = mapper;
	}

	private Role mapDtoToEntity(RoleDto roleDto) {
		Role role = mapper.map(roleDto, Role.class);
		return role;
	}

	private RoleDto mapEntityToDto(Role role) {
		RoleDto roleDto = mapper.map(role, RoleDto.class);
		return roleDto;
	}

	@Override
	public RoleDto createRole(RoleDto roleDto) {
		Role role = mapDtoToEntity(roleDto);
		Role createdRole = roleRepository.save(role);
		return mapEntityToDto(createdRole);
	}

}
