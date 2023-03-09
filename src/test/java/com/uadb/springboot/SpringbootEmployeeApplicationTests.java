package com.uadb.springboot;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.example.exception.ResourceNotFoundException;
import com.uadb.springboot.model.Employee;
import com.uadb.springboot.repository.EmployeeRepository;

@SpringBootTest
class SpringbootEmployeeApplicationTests {
@Autowired
EmployeeRepository employeeRepository;
	@Test
	void createEmployee() {
		
		Employee employee=new Employee();
		employee.setEmailId("lamine7777");
		employee.setFirstName("test");
		employee.setLastName("lasttest");
		Employee emp=employeeRepository.save(employee);
		assertNotNull(emp);
	}
	@Test
	void findById() {
		
		assertNotNull(employeeRepository.findById(2L));
	}
	@Test 
	void findAll() {
		assertNotNull(employeeRepository.findAll());
	}
	@Test 
	void update() {
		Employee employee = employeeRepository.findById(6L).orElseThrow(()->new ResourceNotFoundException("Employee does not exist"));
		employee.setFirstName("lamine");
		employee.setLastName("Traore");
		
		employee.setEmailId("lamine785260388@gmail.com");
		Employee EmployeeUpdated = employeeRepository.save(employee);
		assertNotNull(EmployeeUpdated);
	}
	@Test 
	void Destroy() {
		Employee employee=employeeRepository.findById(7L).orElseThrow(()->new ResourceNotFoundException("Employee does not exist"));
		employeeRepository.delete(employee);
		Map<String,Boolean> response=new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		assertNotNull(response);
		
		
	}
	
	
}
