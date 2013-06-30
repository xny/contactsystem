package com.app.contactsystem.service;

import java.util.List;

import com.app.contactsystem.model.CompanyType;

public interface CompanyTypeManageService {
	CompanyType addCompanyType(CompanyType companyType);
	CompanyType editCompanyType(CompanyType companyType);
	void deleteCompanyType(Long id);
	List<CompanyType> getAllCompanyType();
}
