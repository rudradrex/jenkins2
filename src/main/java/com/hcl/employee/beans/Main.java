package com.hcl.employee.beans;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.hcl.employee.utils.DBConnection;
import com.hcl.employeemanagement.model.Employee;
import com.hcl.employeemanagement.service.EmployeeServices;
import com.hcl.employeemanagement.service.IEmployeeServices;
import com.hcl.employeemangementexception.IdNotFoundException;

public class Main {
	public static void main(String args[]) {
		System.out.println("============Main Class============");
		IEmployeeServices employeeServices = new EmployeeServices();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("enter case 1 to add new  employees in table" + "\nEnter case 2 show employees"
					+ "\n enter case 3 for add employee using prepared statement"
					+ " \nenter case 4 for remove employee from table" + "\n enetr case 5 for update employee in table"
					+ "enter case 6 for search employee through its employee Id"
					+ "\n enter case 7 for close connection");
			int option = sc.nextInt();
			switch (option) {
			case 1:
				System.out.println("enter employee deatails");
				Employee employee1 = new Employee(sc.nextInt(), sc.next(), sc.nextInt());
				Employee employee = employeeServices.addEmployee(employee1);
				System.out.println(employee);

				break;
			case 2:
				List<Employee> employees = employeeServices.displayEmployee();
				System.out.println("==============List of employees are=================" + employees);
				break;
			case 3:
				System.out.println("enter employee deatails");
				Employee employee2 = new Employee(sc.nextInt(), sc.next(), sc.nextInt());
				try {
					Employee employeed = employeeServices.addEmployees(employee2);
					System.out.println("===========k============" + employeed);
				} catch (IdNotFoundException e) {
					e.printStackTrace();
				}

				break;
			case 4:
				System.out.println("enter employee id");
				int id = sc.nextInt();

				employeeServices.deleteEmployee(id);
				break;
			case 5:
				System.out.println("enter employee name which you want to update");
				String name = sc.next();
				System.out.println("enter employee id");
				int eid = sc.nextInt();

				employeeServices.updateEmployee(name, eid);
				break;
			case 6:
				System.out.println("search employee using id");
				int employeeid = sc.nextInt();
				boolean b = employeeServices.searchEmployeeById(employeeid);
				System.out.println(b);
				break;
			case 7:
				Connection con = DBConnection.getConnection();
				try {
					con.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}

				System.exit(1);
				break;
			default:
				System.out.println("Invalid case");

			}
		}
	}
}
