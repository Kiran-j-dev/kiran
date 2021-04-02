package com.example.test.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.Dao.DepartmentDAO;
import com.example.test.Dao.EmployeeDAO;
import com.example.test.Model.Employees;
import com.example.test.RequestClass.EmployeeRequest;
import com.example.test.Service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeDAO employeeDAO;
	
	@Autowired
	DepartmentDAO departmentDAO;
	
	@Override
	public void createEmployee(EmployeeRequest request) {
		Employees employee = new Employees();
		employee.setCompanyId(request.getCompanyId());
		employee.setDepartments(request.getDepartments());
		employee.setDesignation(request.getDesignation());
		employee.setEmployeeName(request.getEmployeeName());
		employee.setSalary(request.getSalary());
		employee.setEmployeeId(request.getEmployeeId());
		employee.setSkill(request.getSkill());
		employee = employeeDAO.saveEmployee(employee);
		
	}

	@Override
	public List<Employees> getAllEmployees() {
		List<Employees> list = null;
		list = employeeDAO.getAllEmployees();
		return list;
	}

	@Override
	public Employees getEmployeeById(EmployeeRequest request) {
		Employees employee = new Employees();
		employee = employeeDAO.getEmployeeById(request);
		return employee;
	}

	@Override
	public Employees updateEmployee(EmployeeRequest request) {
		Employees employee = new Employees();
		employee = employeeDAO.getEmployeeById(request);
		employee.setDepartments(request.getDepartments());
		employee.setDesignation(request.getDesignation());
		employee.setEmployeeName(request.getEmployeeName());
		employee.setEmployeeId(request.getEmployeeId());
		employee.setSalary(request.getSalary());
		employee.setSkill(request.getSkill());
		employeeDAO.updateEmployee(employee);
		return employee;
	}

	@Override
	public Employees deleteEmployee(EmployeeRequest request) {
		Employees employee = new Employees();
		employee = employeeDAO.getEmployeeById(request);
		if (employee != null) {
			employeeDAO.deleteEmployee(employee);
		}
		return employee;
	}
}
