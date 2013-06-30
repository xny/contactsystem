package com.app.contactsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.contactsystem.dao.SubCompanyDao;
import com.app.contactsystem.model.SubCompany;
import com.app.contactsystem.service.SubCompanyManageService;

@Service("subCompanyManageService")
public class SubCompanyManageServiceImpl implements SubCompanyManageService {

	@Autowired
	private SubCompanyDao subCompanyDao;
	
	@Override
	public List<SubCompany> getAllSubCompany() {
		return subCompanyDao.getAll();
	}

}
