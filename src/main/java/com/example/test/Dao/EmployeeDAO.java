package com.example.test.Dao;

import java.util.List;

import com.example.test.Model.Employees;
import com.example.test.RequestClass.EmployeeRequest;

public interface EmployeeDAO {

	Employees saveEmployee(Employees employee);

	List<Employees> getAllEmployees();

	Employees getEmployeeById(EmployeeRequest request);

	Employees updateEmployee(Employees employee);

	Employees deleteEmployee(Employees employee);

}
