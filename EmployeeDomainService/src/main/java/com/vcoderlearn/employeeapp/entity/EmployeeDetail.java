package com.vcoderlearn.employeeapp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee_detail")
public class EmployeeDetail implements Serializable{

	
public EmployeeDetail() {}

	public EmployeeDetail(String empRole, String exp, String projectName) {
		this.empRole = empRole;
		this.exp = exp;
		this.projectName = projectName;
	}
	private static final long serialVersionUID = -7731498637669754269L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="detail_id")
	private int id;
	@Column(name="role")
	private String empRole;
	@Column(name="exp")
	private String exp;
	@Column(name="project_name")
	private String projectName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmpRole() {
		return empRole;
	}
	public void setEmpRole(String empRole) {
		this.empRole = empRole;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}
