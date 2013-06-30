package com.app.contactsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.contactsystem.dao.CompanyTypeDao;
import com.app.contactsystem.model.CompanyType;
import com.app.contactsystem.service.CompanyTypeManageService;

@Service("companyTypeManageService")
public class CompanyTypeManageServiceImpl implements CompanyTypeManageService {

	@Autowired
	private CompanyTypeDao companyTypeDao;
	
	@Override
	public CompanyType addCompanyType(CompanyType companyType) {
		return companyTypeDao.save(companyType);
	}

	@Override
	public CompanyType editCompanyType(CompanyType companyType) {
		return companyTypeDao.save(companyType);
	}

	@Override
	public void deleteCompanyType(Long id) {
		companyTypeDao.remove(id);
	}

	@Override
	public List<CompanyType> getAllCompanyType() {
		return companyTypeDao.getAll();
	}

}
