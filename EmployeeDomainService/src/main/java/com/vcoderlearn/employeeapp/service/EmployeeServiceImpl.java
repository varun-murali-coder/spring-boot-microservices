package com.vcoderlearn.employeeapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcoderlearn.employeeapp.contants.EmployeeConstants;
import com.vcoderlearn.employeeapp.dao.EmployeeRepository;
import com.vcoderlearn.employeeapp.dto.EmployeeDto;
import com.vcoderlearn.employeeapp.entity.EmployeeDetail;
import com.vcoderlearn.employeeapp.entity.EmployeeEntity;
import com.vcoderlearn.employeeapp.util.EmployeeUtil;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	Random r=new Random();
    private EmployeeRepository employeeRepository;
    int counter=0;
    private  List<EmployeeDto> emps=new ArrayList<>();
	
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
      this.employeeRepository=employeeRepository;
	}
	
	/*
	 * Method for creating an employee
	 */

	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDetails) {
		String email="";
		//Before moving the data to dto we have to create email,project name,role based on tech stack and exp
          String projectAssigned=createProject(employeeDetails);
          String role=createRole(employeeDetails);
          email=createEmail(employeeDetails,counter);
          counter=counterValue(email);
		//Here we need to do conversion to entity to move data to db.
          //Data left to be added email,other data in employeedetail entity.
          EmployeeEntity empEntity=(EmployeeEntity)EmployeeUtil.modelMapping(employeeDetails);
          if(counter!=0) 
        email=createEmail(employeeDetails,counter);  
          empEntity.setEmail(email);
          EmployeeDetail empDetails=new EmployeeDetail(role,employeeDetails.getExp(),projectAssigned);
          empEntity.setEmpDetails(empDetails);
          employeeRepository.save(empEntity);
          EmployeeDto empReturnDto=(EmployeeDto)EmployeeUtil.modelMapping(empEntity);
          empReturnDto.setProjectName(projectAssigned);
          empReturnDto.setEmpRole(role);
          return empReturnDto;
	}

	public int counterValue(String email) {
		EmployeeEntity ent=employeeRepository.findByEmail(email);
		if(ent==null)
		return 0;
		else
			return ++counter;
	}
	

	/**
	 * @param employeeDetails
	 */
	private String createEmail(EmployeeDto employeeDetails,int counter) {
		//Check for availability of mail id already in the database.
		if(counter==0)
		return employeeDetails.getEmpFirstName()+"."+employeeDetails.getEmpLastName()+"@diligent.com";
		else
			return employeeDetails.getEmpFirstName()+"."+employeeDetails.getEmpLastName()+counter+"@diligent.com";

	}

	/**
	 * Role to be assigned based on exp
	 */
	private String createRole(EmployeeDto employeeDetails){
		Integer experience=Integer.parseInt(employeeDetails.getExp());
		if(experience<1)
			return "Trainee";
		else if(experience>=1 && experience<4)
			return "system engineer";
		else if(experience>=4 && experience<7)
			return "Analyst";
		else
			return "Architect";
			
		
	}
	
	/*Project to be decided based on tech stack and exp
	 * If exp>5 and tech stack size>=2-->Banking development project
	 * if exp<5 and tech stack size<2-->Banking Support Team
	 * Any other tech stack random project
	 */
 
	private String createProject(EmployeeDto employeeDetails) {
		String [] techStack=employeeDetails.getTechStack().split(",");
		int techSize=techStack.length;
		if(Integer.parseInt(employeeDetails.getExp())>5 &&techSize>=2)
			return EmployeeConstants.projects[0];
		else if(Integer.parseInt(employeeDetails.getExp())<5 &&techSize<2)
			return EmployeeConstants.projects[1];
		else 
		 	return EmployeeConstants.projects[r.nextInt(EmployeeConstants.projects.length)];
	}

	
	/* 
	 * Method for fetching employee details
	 */
	@Override
	public EmployeeDto getEmployee(String email) {
		EmployeeEntity ent=employeeRepository.findByEmail(email);
        if(ent!=null) {
        	EmployeeDto empReturnDto=(EmployeeDto)EmployeeUtil.modelMapping(ent);
        	empReturnDto.setProjectName(ent.getEmpDetails().getProjectName());
        	empReturnDto.setEmpRole(ent.getEmpDetails().getEmpRole());
        	return empReturnDto;
        }
	
		return null;
	}
/* 
 * Method for updating employee details
 */
	@Override
	public EmployeeDto updateEmployee(EmployeeDto employeeDetails,int empId) {
		EmployeeEntity entity=employeeRepository.findById(empId).get();
		EmployeeDetail details=entity.getEmpDetails();
		entity.setEmpFirstName(employeeDetails.getEmpFirstName());
		entity.setEmpLastName(employeeDetails.getEmpLastName());
		entity.setAddress(employeeDetails.getAddress());
		entity.setPhone(employeeDetails.getPhone());
		employeeRepository.save(entity);
    	EmployeeDto empReturnDto=(EmployeeDto)EmployeeUtil.modelMapping(entity);
    	empReturnDto.setProjectName(details.getProjectName());
    	empReturnDto.setEmpRole(details.getEmpRole());
        return empReturnDto;
	}
	/*
	 * Method to get all employee details
	 */

	@Override
	public List<EmployeeDto> findAllEmployee() {
      Iterable<EmployeeEntity> employeeIterator=employeeRepository.findAll();
      List<EmployeeEntity> employees=EmployeeUtil.iterableToListConverter(employeeIterator);
      List<EmployeeDto> employeesDto=mapEntityToDto(employees);
		return employeesDto;
	}
	
	private List<EmployeeDto> mapEntityToDto( List<EmployeeEntity> empEntity){
		//
		for(EmployeeEntity empE:empEntity) {
	    	EmployeeDto empReturnDto=(EmployeeDto)EmployeeUtil.modelMapping(empE);
			EmployeeDetail details=empE.getEmpDetails();
			empReturnDto.setProjectName(details.getProjectName());
	    	empReturnDto.setEmpRole(details.getEmpRole());
	    	emps.add(empReturnDto);
		}
		return emps;
	}
	
	

}
