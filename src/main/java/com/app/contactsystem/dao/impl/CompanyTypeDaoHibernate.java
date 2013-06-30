package com.app.contactsystem.dao.impl;

import org.springframework.stereotype.Repository;

import com.app.contactsystem.dao.CompanyTypeDao;
import com.app.contactsystem.model.CompanyType;

@Repository("companyTypeDao")
public class CompanyTypeDaoHibernate extends GenericDaoHibernate<CompanyType, Long> implements CompanyTypeDao {

	public CompanyTypeDaoHibernate() {
		super(CompanyType.class);
	}

}
