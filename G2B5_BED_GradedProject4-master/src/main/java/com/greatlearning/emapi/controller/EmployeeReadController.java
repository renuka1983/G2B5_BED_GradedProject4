package com.greatlearning.emapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.emapi.dto.EmployeeDto;
import com.greatlearning.emapi.service.EmployeeReadService;
import com.greatlearning.emapi.util.AppConstants;

@RestController
public class EmployeeReadController {

	private EmployeeReadService employeeReadservice;

	public EmployeeReadController(EmployeeReadService employeeReadservice) {
		this.employeeReadservice = employeeReadservice;
	}

	@GetMapping("api/v1/employee/list")
	public List<EmployeeDto> listEmployee(
			@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
		System.out.println("In here employee list section");
		return employeeReadservice.getAllemployee(sortBy, sortDir);
	}

	@GetMapping("api/v1/employee/{id}")
	public EmployeeDto findEmployee(@PathVariable("id") int id) {
		return employeeReadservice.getEmployeeById(id);
	}

	@GetMapping("api/v1/employee/search/{firstName}")
	public List<EmployeeDto> findEmployee(@PathVariable("firstName") String firstName) {
		return employeeReadservice.getEmployeeByfirstName(firstName);
	}
}
