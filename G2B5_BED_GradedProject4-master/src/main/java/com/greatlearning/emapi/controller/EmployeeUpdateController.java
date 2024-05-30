package com.greatlearning.emapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.emapi.dto.EmployeeDto;
import com.greatlearning.emapi.service.EmployeeUpdateService;

@RestController
public class EmployeeUpdateController {

	private EmployeeUpdateService employeeUpdateservice;

	public EmployeeUpdateController(EmployeeUpdateService employeeUpdateservice) {
		this.employeeUpdateservice = employeeUpdateservice;
	}

	@PutMapping("api/v1/employee/{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto,
			@PathVariable(name = "id") int id) {
		EmployeeDto employeeUpdateResponse = employeeUpdateservice.doUpdateemployee(employeeDto, id);
		return new ResponseEntity<>(employeeUpdateResponse, HttpStatus.OK);
	}
}
