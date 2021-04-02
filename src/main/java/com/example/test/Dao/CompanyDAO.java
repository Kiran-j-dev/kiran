package com.example.test.Dao;

import java.util.List;
import com.example.test.Model.Company;
import com.example.test.RequestClass.CompanyRequest;

public interface CompanyDAO {

	Company saveCompany(Company company);

	List<Company> getAllCompanies();

	Company getCompanyByCompanyId(CompanyRequest request);

	Company updateCompany(Company company);

	Company deleteCompany(Company company);

	Company getCompanyByCompanyId(int companyId);	

}
