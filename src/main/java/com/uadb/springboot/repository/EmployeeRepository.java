package com.uadb.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.uadb.springboot.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	@Query("select s from Employee s where s.firstName= ?1")
	public List<Employee> getStudentsByFirstName(String firstName);
	public List<Employee> findByFirstName(String firstName);

}
