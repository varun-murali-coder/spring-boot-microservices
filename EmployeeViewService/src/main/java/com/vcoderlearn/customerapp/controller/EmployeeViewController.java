package com.vcoderlearn.customerapp.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vcoderlearn.customerapp.exception.EmployeeException;
import com.vcoderlearn.customerapp.service.EmployeeService;
import com.vcoderlearn.employeeapp.dto.EmployeeDto;
import com.vcoderlearn.employeeapp.model.EmployeeRequestModel;
import com.vcoderlearn.employeeapp.model.EmployeeRequestUpdateModel;
import com.vcoderlearn.employeeapp.model.EmployeeResponseModel;
import com.vcoderlearn.employeeapp.model.EmployeesAll;
import com.vcoderlearn.employeeapp.util.EmployeeUtil;

@RestController
@RequestMapping("/viewService")
public class EmployeeViewController {
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/getAllEmployees")
	public ResponseEntity<EmployeesAll>  getAllEmployees() {
		EmployeesAll allEmployees=employeeService.findAllEmployee();
		return ResponseEntity.status(HttpStatus.OK).body(allEmployees);
    
	}
	/*
	 * This method does validation as well as custom exception handling
	 */
	
	@PostMapping("/createEmployee")
	public ResponseEntity<EmployeeResponseModel> createEmployee(@Valid @RequestBody EmployeeRequestModel employeeDetails,BindingResult binder) {
		if(binder.hasErrors())
			throw new EmployeeException("Some required input parameters missing");
		EmployeeDto employeeDto=(EmployeeDto) EmployeeUtil.modelMapping(employeeDetails);
		EmployeeDto returnEmpDetails=employeeService.createEmployee(employeeDto);
		EmployeeResponseModel returnResult=EmployeeUtil.modelMappingResponse(returnEmpDetails);
		return ResponseEntity.status(HttpStatus.CREATED).body(returnResult) ;

	}
	
	@PutMapping("/updateEmployee/{empId}")
	public ResponseEntity<EmployeeResponseModel> updateEmployee( @RequestBody EmployeeRequestUpdateModel employeeDetails,BindingResult binder,@PathVariable("empId")int empId) {
		//Conversion to DTO
		EmployeeDto employeeDto=(EmployeeDto) EmployeeUtil.modelMapping(employeeDetails);
		EmployeeDto returnEmpDetails=employeeService.updateEmployee(employeeDto,empId);
		EmployeeResponseModel returnResult=EmployeeUtil.modelMappingResponse(returnEmpDetails);
		return ResponseEntity.status(HttpStatus.OK).body(returnResult) ;
	}
	@GetMapping("/getEmployee")
	public ResponseEntity<EmployeeResponseModel>  getEmployee(@RequestParam("email") String email) {
		EmployeeDto returnEmpDetails=employeeService.getEmployee(email);
		EmployeeResponseModel returnResult=EmployeeUtil.modelMappingResponse(returnEmpDetails);
		return ResponseEntity.status(HttpStatus.OK).body(returnResult) ;
    
	}
	
}
