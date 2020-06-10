package com.vcoderlearn.employeeapp.dao;

import org.springframework.data.repository.CrudRepository;

import com.vcoderlearn.employeeapp.entity.EmployeeEntity;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {
	EmployeeEntity findByEmail(String email);
	

}

