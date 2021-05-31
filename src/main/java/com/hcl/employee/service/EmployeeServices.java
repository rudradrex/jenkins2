package com.hcl.employee.service;

import java.util.List;

import com.hcl.employeemanagement.dao.EmployeeDao;
import com.hcl.employeemanagement.model.Employee;
import com.hcl.employeemangementexception.IdNotFoundException;

public class EmployeeServices implements IEmployeeServices {
	EmployeeDao employeeDao = new EmployeeDao();

	@Override
	public List<Employee> displayEmployee() {
		System.out.println("==================servicelayer================");
		// TODO Auto-generated method stub
		return employeeDao.displayEmployee();
	}
	// public void addEmployee(Employee employee){
	// employeeDao.addEmployee(employee);
	// } 

	// public void addEmployee()
	// {
	// employeeDao.addEmployee();
	// }

	public Employee addEmployee(Employee employee) {
		return employeeDao.addEmployee(employee);

	}

	public Employee addEmployees(Employee employee) throws IdNotFoundException{
		return employeeDao.addEmployees(employee);

	}

	@Override
	public void addEmployee() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteEmployee(int id) {
		employeeDao.deleteEmployee(id);

	}

	public void updateEmployee(String name, int id) {
		employeeDao.updateEmployee(name, id);
	}
	public boolean searchEmployeeById(int employeeid){
		return employeeDao.searchEmployeeById(employeeid);
		
	}

}
