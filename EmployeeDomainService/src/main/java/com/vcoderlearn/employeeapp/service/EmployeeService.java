package com.vcoderlearn.employeeapp.service;

import java.util.List;

import com.vcoderlearn.employeeapp.dto.EmployeeDto;

public interface EmployeeService {
	
	EmployeeDto createEmployee(EmployeeDto employeeDetails);
	EmployeeDto getEmployee(String email);
	EmployeeDto updateEmployee(EmployeeDto employeeDetails,int empId);
	List<EmployeeDto> findAllEmployee();

	

}
