package com.greatlearning.emapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.emapi.service.EmployeeDeleteService;

@RestController
public class EmployeeDeleteController {

	private EmployeeDeleteService employeeDeleteservice;

	public EmployeeDeleteController(EmployeeDeleteService employeeDeleteservice) {
		this.employeeDeleteservice = employeeDeleteservice;
	}

	@DeleteMapping("api/v1/employee/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable(name = "id") int id) {
		employeeDeleteservice.deleteEmployeeById(id);
		return new ResponseEntity<>("Employee " + id + " is deleted", HttpStatus.OK);
	}
}
