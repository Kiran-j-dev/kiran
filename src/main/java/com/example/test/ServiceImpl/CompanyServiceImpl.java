package com.example.test.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.Dao.CompanyDAO;
import com.example.test.Model.Company;
import com.example.test.RequestClass.CompanyRequest;
import com.example.test.Service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyDAO companyDAO;

	@Override
	public String createCompany(CompanyRequest request) {

		Company company = new Company();
		company.setCompanyId(request.getCompanyId());
		company.setCompanyName(request.getCompanyName());
		company = companyDAO.saveCompany(company);
		if (company != null) {
			return "success";
		} else {
			return "error";
		}
	}

	@Override
	public List<Company> getAllCompanies() {
		List<Company> list = null;
		list = companyDAO.getAllCompanies();
		return list;
	}

	@Override
	public Company getCompanyByCompanyId(CompanyRequest request) {
		Company company = new Company();
		company = companyDAO.getCompanyByCompanyId(request);
		return company;
	}

	@Override
	public Company updateCompany(CompanyRequest request) {
		Company company = new Company();
		company = companyDAO.getCompanyByCompanyId(request);
		company.setCompanyName(request.getCompanyName());
		company.setDepartments(request.getDepartments());
		company.setEmployees(request.getEmployees());
		companyDAO.updateCompany(company);
		return company;
	}

	@Override
	public Company deleteCompany(CompanyRequest request) {
		Company company = new Company();
		company = companyDAO.getCompanyByCompanyId(request);
		if (company != null) {
			companyDAO.deleteCompany(company);
		}
		return company;
	}

}
