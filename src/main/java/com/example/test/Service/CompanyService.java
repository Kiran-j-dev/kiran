package com.example.test.Service;

import java.util.List;
import com.example.test.Model.Company;
import com.example.test.RequestClass.CompanyRequest;

public interface CompanyService {

	String createCompany(CompanyRequest request);

	List<Company> getAllCompanies();

	Company getCompanyByCompanyId(CompanyRequest request);

	Company updateCompany(CompanyRequest request);

	Company deleteCompany(CompanyRequest request);
	
}
