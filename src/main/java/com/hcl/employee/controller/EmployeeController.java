package com.hcl.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

import com.hcl.employee.beans.Employee;
import com.hcl.employee.services.EmployeeServices;
import com.hcl.employee.util.UserInputException;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeServices es;
	@GetMapping("/displayAll")
	public ModelAndView displayEmployee() {
		List<Employee> list=es.displayEmployee();
		ModelAndView mv=new ModelAndView();
		mv.setViewName("hello");
		mv.addObject("list",list);
		return mv;
	}
	
	@PostMapping("/addEmployee")
	public ModelAndView addEmp(@RequestParam("emp_id") String emp_id,
			@RequestParam("emp_name") String emp_name,
			@RequestParam("emp_address") String emp_address,
			RedirectAttributes redirectAttribute){
		
			Employee e=new Employee();
			e.setEmp_id(Integer.parseInt(emp_id));
			e.setEmp_name(emp_name);
			e.setEmp_address(emp_address);
			
			try {
				es.addEmployee(e);
				ModelAndView mv=new ModelAndView("redirect:/displayAll");
				redirectAttribute.addFlashAttribute("success","Employee added successfully");
				
				return mv;
			} catch (UserInputException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				redirectAttribute.addFlashAttribute("error",e1.getMessage());
				ModelAndView mv=new ModelAndView("redirect:/displayAll");
			
				return mv;
			}	
	}
	
	@GetMapping("/deleteEmployee/{emp_id}")
	public ModelAndView deleteEmployee(@PathVariable("emp_id") int emp_id,RedirectAttributes redirectAttribute) {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("hello");
		try {
			es.deleteEmployee(emp_id);
			redirectAttribute.addFlashAttribute("success","Employee deleted successfully");
			mv=new ModelAndView("redirect:/displayAll");
		} catch (UserInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			redirectAttribute.addFlashAttribute("error",e.getMessage());
			mv=new ModelAndView("redirect:/displayAll");		
		}		
		return mv;
	}
	
	
	@GetMapping("/searchEmployee")
	public ModelAndView searchEmp(@RequestParam("emp_id") int emp_id){
			Employee e=new Employee();				
			try {
				List<Employee> list=es.searchEmployee(emp_id);
				//List<Employee> list=es.displayEmployee();
				ModelAndView mv=new ModelAndView();
				mv.setViewName("hello");
				mv.addObject("list",list);
				return mv;
			} catch (UserInputException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				List<Employee> list=es.displayEmployee();
				ModelAndView mv=new ModelAndView();
				mv.setViewName("hello");
				mv.addObject("list",list);
				mv.addObject("error",e1.getMessage());
				return mv;
			}	
	}
	
	@GetMapping("/updateEmployee/{emp_id}")
	public ModelAndView updateEmployee(@PathVariable("emp_id") int emp_id) {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("update-form");
		try {
			mv.addObject("object",es.searchEmployee(emp_id));
		} catch (UserInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}
	
	@GetMapping("/updateEmployee/saveEmployee")
	public ModelAndView updateEmployee(@RequestParam("emp_id") String emp_id,
			@RequestParam("emp_name") String emp_name,
			@RequestParam("emp_address") String emp_address,
			RedirectAttributes redirectAttribute){
		
			Employee e=new Employee();
			e.setEmp_id(Integer.parseInt(emp_id));
			e.setEmp_name(emp_name);
			e.setEmp_address(emp_address);
			
			try {
				es.updateEmployee(e);
				redirectAttribute.addFlashAttribute("success","Employee updated successfully");
				ModelAndView mv=new ModelAndView("redirect:/displayAll");	
				return mv;
			} catch (UserInputException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				redirectAttribute.addFlashAttribute("error",e1.getMessage());
				ModelAndView mv=new ModelAndView("redirect:/displayAll");
				return mv;
			}	
	}
	
	
}
