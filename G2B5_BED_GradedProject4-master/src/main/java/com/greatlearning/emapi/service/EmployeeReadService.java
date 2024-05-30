package com.greatlearning.emapi.service;

import java.util.List;

import com.greatlearning.emapi.dto.EmployeeDto;

public interface EmployeeReadService {

	List<EmployeeDto> getAllemployee(String sortBy, String sortDir);

	EmployeeDto getEmployeeById(int id);

	List<EmployeeDto> getEmployeeByfirstName(String firstName);

}
