package com.greatlearning.emapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.emapi.dto.EmployeeDto;
import com.greatlearning.emapi.service.EmployeeCreateService;

@RestController
public class EmployeeCreateController {

	private EmployeeCreateService employeeCreateservice;

	public EmployeeCreateController(EmployeeCreateService employeeCreateservice) {
		this.employeeCreateservice = employeeCreateservice;
	}

	@PostMapping("api/v1/employee/add")
	public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {
		return new ResponseEntity<>(employeeCreateservice.createEmployee(employeeDto), HttpStatus.CREATED);
	}
}
