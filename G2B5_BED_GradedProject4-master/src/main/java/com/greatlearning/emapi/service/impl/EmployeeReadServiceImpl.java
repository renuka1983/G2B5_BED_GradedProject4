package com.greatlearning.emapi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greatlearning.emapi.dao.EmployeeRepository;
import com.greatlearning.emapi.dto.EmployeeDto;
import com.greatlearning.emapi.entity.Employee;
import com.greatlearning.emapi.exception.ResourceNotFoundException;
import com.greatlearning.emapi.service.EmployeeReadService;

@Service
public class EmployeeReadServiceImpl implements EmployeeReadService {

	private EmployeeRepository employeeRepository;
	private ModelMapper mapper;

	public EmployeeReadServiceImpl(EmployeeRepository employeeRepository, ModelMapper mapper) {
		this.employeeRepository = employeeRepository;
		this.mapper = mapper;
	}

	@Override
	public List<EmployeeDto> getAllemployee(String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		List<Employee> employees = employeeRepository.findAll(sort);
		return employees.stream().map(employee -> mapToDto(employee)).collect(Collectors.toList());
	}

	@Override
	public EmployeeDto getEmployeeById(int id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
		return mapToDto(employee);
	}

	@Override
	public List<EmployeeDto> getEmployeeByfirstName(String firstName) {
		List<Employee> employees = employeeRepository.findAllByfirstName(firstName);
		List<EmployeeDto> employeeDto = new ArrayList<EmployeeDto>();
		for (Employee employee : employees) {
			employeeDto.add(mapToDto(employee));
		}
		return employeeDto;
	}

	private EmployeeDto mapToDto(Employee employee) {
		EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
		return employeeDto;
	}

}
