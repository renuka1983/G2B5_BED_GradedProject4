package com.greatlearning.emapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.emapi.dto.RoleDto;
import com.greatlearning.emapi.service.RoleCreateService;

@RestController
public class RoleCreateController {

	private RoleCreateService roleCreateservice;

	public RoleCreateController(RoleCreateService roleCreateservice) {
		this.roleCreateservice = roleCreateservice;
	}

	@PostMapping("api/v1/role/add")
	public ResponseEntity<RoleDto> addEmployee(@RequestBody RoleDto roleDto) {
		System.out.println("In roles add");
		System.out.println("RoleDto" + roleDto);
		return new ResponseEntity<>(roleCreateservice.createRole(roleDto), HttpStatus.CREATED);
	}
}
