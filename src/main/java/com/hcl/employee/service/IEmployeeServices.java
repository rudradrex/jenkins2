package com.hcl.employee.service;

import java.util.List;

import com.hcl.employeemanagement.model.Employee;
import com.hcl.employeemangementexception.IdNotFoundException;

public interface IEmployeeServices {

	List<Employee> displayEmployee();

	void addEmployee();

	Employee addEmployee(Employee employee);

	Employee addEmployees(Employee employee) throws IdNotFoundException ;

	void deleteEmployee(int id);

	void updateEmployee(String name, int id);

	boolean searchEmployeeById(int employeeid);

}
