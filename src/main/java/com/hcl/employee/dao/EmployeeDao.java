package com.hcl.employee.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hcl.employee.utils.DBConnection;
import com.hcl.employeemanagement.model.Employee;
import com.hcl.employeemangementexception.IdNotFoundException;

public class EmployeeDao {
	Connection con = DBConnection.getConnection();
	Scanner sc = new Scanner(System.in);

	public List<Employee> displayEmployee() {
		System.out.println("===============dao==================");
		// TODO Auto-generated method stub
		ArrayList<Employee> employees = new ArrayList<>();
		ResultSet rs = null;
		Statement st = null;
		// Connection con = DBConnection.getConnection();

		try {
			st = con.createStatement();
			String sql = "select id,name,salary from Employee";
			rs = st.executeQuery(sql);
			while (rs.next()) {

				int id = rs.getInt(1);
				String name = rs.getString(2);
				int salary = rs.getInt(3);
				Employee employee = new Employee(id, name, salary);
				employees.add(employee);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return employees;
	}

	// public void addEmployee() {
	//
	// Statement st = null;
	// // Connection con = DBConnection.getConnection();
	// System.out.println(con);
	// try {
	// st = con.createStatement();
	// System.out.println("Enter employees deatails");
	// System.out.println("Enter employee id");
	// int id = sc.nextInt();
	// System.out.println("Enter employee name");
	// String name = sc.next();
	// System.out.println("Enter employee salary");
	// int salary = sc.nextInt();
	// System.out.println("===id====" + id);
	// System.out.println("===name====" + name);
	// System.out.println("===name====" + name);
	// String sql = "insert into Employee(id,name,salary) values(" + id + ",'" +
	// name + "'," + salary + ")";
	//
	// int m = st.executeUpdate(sql);
	// if (m == 1)
	// System.out.println("inserted successfully : " + sql);
	// else
	// System.out.println("insertion failed");
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } finally {
	// closeStatement(Statement st);
	// }
	//
	// }

	public Employee addEmployee(Employee employee) {

		Statement st = null;
		// Connection con = DBConnection.getConnection();
		System.out.println(con);
		try {
			st = con.createStatement();
			int id = employee.getId();
			String name = employee.getName();
			int salary = employee.getSalary();

			String sql = "insert into Employee(id,name,salary) values(" + id + ",'" + name + "'," + salary + ")";

			int m = st.executeUpdate(sql);
			if (m == 1)
				System.out.println("inserted successfully : " + sql);
			else
				System.out.println("insertion failed");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(st);
		}
		return employee;
	}

	// add employees using prepared statement
	public Employee addEmployees(Employee employee) throws IdNotFoundException {

		PreparedStatement pst = null;

		try {
			if (!searchEmployeeById(employee.getId())) {
				String sql = "insert into Employee values(?,?,?)";
				pst = con.prepareStatement(sql);
				pst.setInt(1, employee.getId());
				pst.setString(2, employee.getName());
				pst.setInt(3, employee.getSalary());
				int m = pst.executeUpdate();

				if (m == 1)
					System.out.println("inserted successfully : " + sql);
				else
					System.out.println("insertion failed");
			}else
			{
				throw new IdNotFoundException("employee id already exist");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(pst);
		}
		return employee;
	}

	public void deleteEmployee(int id) {

		PreparedStatement pst = null;

		try {
			String sql = "delete from employee where id=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
			System.out.println("=======" + pst.executeUpdate());
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			closePreparedStatement(pst);
		}
	}

	public void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void closePreparedStatement(Statement pst) {
		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void updateEmployee(String name, int id) {
		PreparedStatement pst = null;

		try {
			String sql1 = "update Employee set name=? whetre id=?";
			pst = con.prepareStatement(sql1);
			pst.setString(2, name);
			pst.setInt(1, id);
			pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closePreparedStatement(pst);
		}

	}

	public boolean searchEmployeeById(int employeeid) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		int count = 0;
		boolean b = false;
		try {
			String sql1 = "select count(*) from Employee where id=?";
			pst = con.prepareStatement(sql1);
			pst.setInt(1, employeeid);
			rs = pst.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count == 0)
				b = false;
			else
				b = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(pst);
			closeResultSet(rs);
		}
		return b;

	}

}

