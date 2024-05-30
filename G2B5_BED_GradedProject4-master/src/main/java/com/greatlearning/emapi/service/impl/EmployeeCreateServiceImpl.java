package com.greatlearning.emapi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.greatlearning.emapi.dao.EmployeeRepository;
import com.greatlearning.emapi.dto.EmployeeDto;
import com.greatlearning.emapi.entity.Employee;
import com.greatlearning.emapi.service.EmployeeCreateService;

@Service
public class EmployeeCreateServiceImpl implements EmployeeCreateService {

	private EmployeeRepository employeeRepository;
	private ModelMapper mapper;

	public EmployeeCreateServiceImpl(EmployeeRepository employeeRepository, ModelMapper mapper) {
		this.employeeRepository = employeeRepository;
		this.mapper = mapper;
	}

	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		Employee employee = mapDtoToEntity(employeeDto);
		Employee createdEmployee = employeeRepository.save(employee);
		return mapEntityToDto(createdEmployee);
	}

	private Employee mapDtoToEntity(EmployeeDto employeeDto) {
		Employee employee = mapper.map(employeeDto, Employee.class);
		return employee;
	}

	private EmployeeDto mapEntityToDto(Employee employee) {
		EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
		return employeeDto;
	}

}
