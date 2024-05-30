package com.greatlearning.emapi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.emapi.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findAllByfirstName(String firstName);

}
