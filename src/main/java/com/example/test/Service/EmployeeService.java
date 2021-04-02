package com.example.test.Service;

import java.util.List;

import com.example.test.Model.Employees;
import com.example.test.RequestClass.EmployeeRequest;

public interface EmployeeService {

	void createEmployee(EmployeeRequest request);

	List<Employees> getAllEmployees();

	Employees getEmployeeById(EmployeeRequest request);

	Employees updateEmployee(EmployeeRequest request);

	Employees deleteEmployee(EmployeeRequest request);

}
