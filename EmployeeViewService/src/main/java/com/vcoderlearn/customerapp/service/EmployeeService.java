package com.vcoderlearn.customerapp.service;

import java.util.List;

import com.vcoderlearn.employeeapp.dto.EmployeeDto;
import com.vcoderlearn.employeeapp.model.EmployeesAll;

public interface EmployeeService {

	EmployeeDto createEmployee(EmployeeDto employeeDto);

	EmployeeDto updateEmployee(EmployeeDto employeeDto, int empId);

	EmployeeDto getEmployee(String email);

	EmployeesAll findAllEmployee();

}
