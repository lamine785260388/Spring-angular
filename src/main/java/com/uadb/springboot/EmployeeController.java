package com.uadb.springboot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.exception.ResourceNotFoundException;
import com.uadb.springboot.model.Employee;
import com.uadb.springboot.repository.EmployeeRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping( "/api/v1")
public class EmployeeController {
@Autowired
private EmployeeRepository employeRepository;

@GetMapping(value="/employees")
public List<Employee> getAllEmployee(){
	return employeRepository.findAll();
	
}
@PostMapping("/employees")
public Employee createEmployee(@RequestBody Employee employee){
	return employeRepository.save(employee);
}
@GetMapping("/employees/{id}")
public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
	Employee employee=employeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Sorry,employee does not exist"));
	return ResponseEntity.ok().body(employee);
}
@PutMapping("/employees/{id}")
public ResponseEntity<Employee> updateEmployee(@PathVariable Long id , @RequestBody Employee employeeDetail){
	Employee employee = employeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee does not exist"));
	employee.setFirstName(employeeDetail.getFirstName());
	employee.setLastName(employeeDetail.getLastName());
	
	employee.setEmailId(employeeDetail.getEmailId());
	Employee EmployeeUpdated = employeRepository.save(employee);
	return ResponseEntity.ok(EmployeeUpdated);
	
}
@DeleteMapping("/employees/{id}")
public ResponseEntity<Map<String ,Boolean>>deleteEmployee(@PathVariable Long id){
	Employee employee=employeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee does not exist"));
	employeRepository.delete(employee);
	Map<String,Boolean> response=new HashMap<>();
	response.put("deleted", Boolean.TRUE);
	return ResponseEntity.ok(response);
}

}
