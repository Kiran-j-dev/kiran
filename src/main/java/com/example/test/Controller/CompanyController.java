package com.example.test.Controller;

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

import com.example.test.Model.Company;
import com.example.test.RequestClass.CompanyRequest;
import com.example.test.Service.CompanyService;

@CrossOrigin(origins = "*")
@RestController
public class CompanyController {

	@Autowired
	CompanyService companyService;

	@PostMapping("createCompany")
	public Map<String, Object> addCompany(@RequestBody CompanyRequest request) throws HibernateException, Exception {
		Map<String, Object> response = new LinkedHashMap<>();
		String status = companyService.createCompany(request);
		if (status.equals("success")) {
			response.put("Success", "Company Created Successfully");
		} else {
			response.put("Error", "Company Not Created");
		}
		return response;
	}
	
	@GetMapping("getCompanies")
	public Map<String, Object> getAllCompanies() {
		Map<String, Object> response = new LinkedHashMap<>();
		List<Company> records = companyService.getAllCompanies();
		response.put("status", "ok");
		response.put("Success Code", 200);
		response.put("ListData", records);
		return response;
	}
	
	@PostMapping("getCompanyByCompanyId")
	public Map<String, Object> getCompanyByCompanyId(@RequestBody CompanyRequest request) {
		Map<String, Object> responseData = new LinkedHashMap<>();
		Company companyData = companyService.getCompanyByCompanyId(request);
		if (companyData != null) {
			responseData.put("status", "ok");
			responseData.put("Success Code", 200);
			responseData.put("ResponseData", companyData);
		} else {
			responseData.put("status", "Failed");
			responseData.put("Success Code", 400);
			responseData.put("ResponseData", "No Company Found");
		}
		return responseData;
	}

	@PostMapping("updateCompany")
	public Map<String, Object> updateCompany(@RequestBody CompanyRequest request) throws HibernateException, Exception {
		Map<String, Object> response = new LinkedHashMap<>();
		Company company = companyService.updateCompany(request);
		if (company != null) {
			response.put("status", "Success");
			response.put("SuccessCode", 200);
			response.put("response", "Company Updated Successfully");
		} else {
			response.put("status", "Failed");
			response.put("SuccessCode", 400);
			response.put("response", "Company Data Not Found");
		}
		return response;
	}
	
	@PostMapping("deleteCompany")
	public Map<String, Object> deleteCompany(@RequestBody CompanyRequest request) {
		Map<String, Object> responseData = new LinkedHashMap<>();
		Company company = companyService.deleteCompany(request);
		if (company != null) {
			responseData.put("status", "ok");
			responseData.put("Success Code", 200);
			responseData.put("ResponseData", "Company Deleted Successfully");
		} else {
			responseData.put("status", "error");
			responseData.put("Failed Code", 400);
			responseData.put("ResponseData", "Delete Failed");
		}

		return responseData;

	}

}
