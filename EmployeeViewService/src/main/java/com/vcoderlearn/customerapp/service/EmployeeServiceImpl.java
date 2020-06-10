package com.vcoderlearn.customerapp.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.vcoderlearn.employeeapp.dto.EmployeeDto;
import com.vcoderlearn.employeeapp.model.EmployeesAll;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	RestTemplate restTemplate;

	@Value("${domainService.createEmployee.url}")
	private String createEmpUrl;

	@Value("${domainService.getEmployee.url}")
	private String selectEmpUrl;

	@Value("${domainService.getAllEmployees.url}")
	private String selectAllEmpUrl;

	@Value("${domainService.updateEmployee.url}")
	private String updateEmpEmpUrl;

	/*
	 * Method to get data after employee creation
	 */
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<EmployeeDto> employeeDetails = new HttpEntity<EmployeeDto>(employeeDto, headers);

		return restTemplate.exchange(createEmpUrl, HttpMethod.POST, employeeDetails, EmployeeDto.class).getBody();
	}

	/*
	 * Method to update data after employee updation
	 */
	@Override
	public EmployeeDto updateEmployee(EmployeeDto employeeDto, int empId) {
		Map<String, Integer> urlParms = new HashMap<>();
		urlParms.put("empId", empId);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<EmployeeDto> employeeDetails = new HttpEntity<EmployeeDto>(employeeDto, headers);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(updateEmpEmpUrl);
		return restTemplate
				.exchange(builder.buildAndExpand(urlParms).toUri(), HttpMethod.PUT, employeeDetails, EmployeeDto.class)
				.getBody();

	}

	/*
	 * Method to get employee details based on email
	 */
	@Override
	public EmployeeDto getEmployee(String email) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> employeeDetails = new HttpEntity<String>(headers);
		// Query parameters
		// Add query parameter
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(selectEmpUrl)

				.queryParam("email", email);
		return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, employeeDetails, EmployeeDto.class)
				.getBody();
	}

	@Override
	public EmployeesAll findAllEmployee() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> employeeDetails = new HttpEntity<String>(headers);
		return restTemplate.exchange(selectAllEmpUrl, HttpMethod.GET, employeeDetails, EmployeesAll.class).getBody();
	}

}
