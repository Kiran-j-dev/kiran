package com.example.test.Controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.Dao.CompanyDAO;
import com.example.test.Dao.DepartmentDAO;
import com.example.test.Dao.SkillDAO;
import com.example.test.Model.Company;
import com.example.test.Model.Departments;
import com.example.test.Model.Employees;
import com.example.test.Model.Skills;
import com.example.test.RequestClass.EmployeeRequest;
import com.example.test.ResponseClass.FinalResponse;
import com.example.test.ResponseClass.ListResponse;
import com.example.test.Service.EmployeeService;

@CrossOrigin(origins = "*")
@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	DepartmentDAO departmentDAO;

	@Autowired
	SkillDAO skillDAO;
	
	@Autowired
	CompanyDAO companyDAO;

	@PostMapping("createEmployee")
	public Map<String, Object> addEmployee(@RequestBody EmployeeRequest request) throws HibernateException, Exception {
		Map<String, Object> response = new LinkedHashMap<>();
		employeeService.createEmployee(request);
		response.put("Success", "Employee Created Successfully");
		return response;
	}

	@GetMapping("getEmployees")
	public Map<String, Object> getAllCompanies() {
		Departments department = new Departments();

		FinalResponse resp = new FinalResponse();
		Skills skillData = new Skills();
		String skills = null;
		String dept = null;
		String Id = null;
		int deptId = 0;
		String skill = null;
		String sk = null;
		String departmentName = null;
		Map<String, Object> response = new LinkedHashMap<>();
		List<Employees> records = employeeService.getAllEmployees();
		List<String> deptName = new ArrayList<>();

		List<ListResponse> lt = new ArrayList<>();
		for (Employees emp : records) {
			Company company = companyDAO.getCompanyByCompanyId(emp.getCompanyId());
			String companyName = company.getCompanyName();
			ListResponse res = new ListResponse();
			dept = emp.getDepartments();
			String a[] = dept.split(",");
			for (int i = 0; i < a.length; i++) {
				Id = a[i];
				deptId = Integer.parseInt(Id);
				department = departmentDAO.getDepartmentName(deptId);
				departmentName = department.getDepartmentName();
				deptName.add(departmentName);
			}
			List<String> skillName = new ArrayList<>();
			skill = emp.getSkill();
			String b[] = skill.split(",");
			for (int j = 0; j < b.length; j++) {
				sk = b[j];
				skillData = skillDAO.getSkillBySkillId(sk);
				skills = skillData.getSkillName();
				skillName.add(skills);
			}
			res.setCompanyId(emp.getCompanyId());
			res.setCompanyName(companyName);
			res.setEmployeeId(emp.getEmployeeId());
			res.setDesignation(emp.getDesignation());
			res.setEmployeeName(emp.getEmployeeName());
			res.setSalary(emp.getSalary());
			res.setDepartments(departmentName);
			res.setSkill(skillName);
			lt.add(res);
			resp.setResponse(lt);

		}

		response.put("status", "ok");
		response.put("Success Code", 200);
		response.put("ListData", lt);
		return response;
	}

	@PostMapping("getEmployeeById")
	public Map<String, Object> getEmployeeById(@RequestBody EmployeeRequest request) {
		Map<String, Object> responseData = new LinkedHashMap<>();
		Employees employee = employeeService.getEmployeeById(request);
		if (employee != null) {
			responseData.put("status", "ok");
			responseData.put("Success Code", 200);
			responseData.put("ResponseData", employee);
		} else {
			responseData.put("status", "Failed");
			responseData.put("Success Code", 400);
			responseData.put("ResponseData", "No employee Found");
		}
		return responseData;
	}

	@PostMapping("updateEmployee")
	public Map<String, Object> updateEmployee(@RequestBody EmployeeRequest request)
			throws HibernateException, Exception {
		Map<String, Object> response = new LinkedHashMap<>();
		Employees employee = employeeService.updateEmployee(request);
		if (employee != null) {
			response.put("status", "Success");
			response.put("SuccessCode", 200);
			response.put("response", "Employee Updated Successfully");
		} else {
			response.put("status", "Failed");
			response.put("SuccessCode", 400);
			response.put("response", "Employee Data Not Found");
		}
		return response;
	}

	@PostMapping("deleteEmployee")
	public Map<String, Object> deleteEmployee(@RequestBody EmployeeRequest request) {
		Map<String, Object> responseData = new LinkedHashMap<>();
		Employees employee = employeeService.deleteEmployee(request);
		if (employee != null) {
			responseData.put("status", "ok");
			responseData.put("Success Code", 200);
			responseData.put("ResponseData", "Employee Deleted Successfully");
		} else {
			responseData.put("status", "error");
			responseData.put("Failed Code", 400);
			responseData.put("ResponseData", "Delete Failed");
		}

		return responseData;

	}

}
