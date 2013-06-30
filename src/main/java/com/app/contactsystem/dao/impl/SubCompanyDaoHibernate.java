package com.app.contactsystem.dao.impl;

import org.springframework.stereotype.Repository;

import com.app.contactsystem.dao.SubCompanyDao;
import com.app.contactsystem.model.SubCompany;

@Repository("subCompanyDao")
public class SubCompanyDaoHibernate extends GenericDaoHibernate<SubCompany, Long> implements SubCompanyDao {

	public SubCompanyDaoHibernate() {
		super(SubCompany.class);
	}

}
