package com.greatlearning.emapi.service;

import com.greatlearning.emapi.dto.EmployeeDto;

public interface EmployeeUpdateService {

	EmployeeDto doUpdateemployee(EmployeeDto employeeDto, int id);
}
