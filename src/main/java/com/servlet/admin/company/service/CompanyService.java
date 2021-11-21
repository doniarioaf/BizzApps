package com.servlet.admin.company.service;

import java.util.List;

import com.servlet.admin.company.entity.BodyCompany;
import com.servlet.admin.company.entity.Company;
import com.servlet.admin.company.entity.CompanyData;
import com.servlet.admin.company.entity.CompanyDataDetail;
import com.servlet.shared.ReturnData;

public interface CompanyService {
	List<Company> getListCompanyActive();
	List<Company> getAllListCompany();
	Company getCompanyByID(long id);
	CompanyData saveCompany(BodyCompany company);
	CompanyData updateCompany(long id,BodyCompany company);
	CompanyDataDetail getCompanyAndCompanyBranchByID(long id);
	ReturnData activatedCompany(long id);
	ReturnData unActivatedCompany(long id);
	ReturnData deleteCompany(long id);
}
