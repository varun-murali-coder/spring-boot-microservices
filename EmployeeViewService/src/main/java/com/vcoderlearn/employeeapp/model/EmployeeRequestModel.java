package com.vcoderlearn.employeeapp.model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EmployeeRequestModel {
	@NotBlank(message="Employee name is required")
	private String empFirstName;
	@NotBlank(message="Employee name is required")
	private String empLastName;
	private String address;
	private String phone;
	@NotBlank(message="Date of joining is required")
	private String dateOfJoining;
	private String techStack;
	@NotBlank(message="Experience is required")
	@Size(min=1,message="Min 0 years to be entered")
	private String exp;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public String getTechStack() {
		return techStack;
	}
	public void setTechStack(String techStack) {
		this.techStack = techStack;
	}
	public String getEmpFirstName() {
		return empFirstName;
	}
	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}
	public String getEmpLastName() {
		return empLastName;
	}
	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	
	

}
