package com.greatlearning.emapi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.greatlearning.emapi.dao.EmployeeRepository;
import com.greatlearning.emapi.dto.EmployeeDto;
import com.greatlearning.emapi.entity.Employee;
import com.greatlearning.emapi.exception.ResourceNotFoundException;
import com.greatlearning.emapi.service.EmployeeUpdateService;

@Service
public class EmployeeUpdateServiceImpl implements EmployeeUpdateService {

	private EmployeeRepository employeeRepository;
	private ModelMapper mapper;

	public EmployeeUpdateServiceImpl(EmployeeRepository employeeRepository, ModelMapper mapper) {
		this.employeeRepository = employeeRepository;
		this.mapper = mapper;
	}

	@Override
	public EmployeeDto doUpdateemployee(EmployeeDto employeeDto, int id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
		employee.setEmail(employeeDto.getEmail());
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());

		Employee updateEmployee = employeeRepository.save(employee);
		return mapToDto(updateEmployee);
	}

	private EmployeeDto mapToDto(Employee employee) {
		EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
		return employeeDto;
	}

}
