package com.app.contactsystem.dao.impl;

import org.springframework.stereotype.Repository;

import com.app.contactsystem.dao.AccountDao;
import com.app.contactsystem.model.Account;

@Repository("accountDao")
public class AccountDaoHibernate extends GenericDaoHibernate<Account, Long> implements AccountDao {

	public AccountDaoHibernate() {
		super(Account.class);
	}

	@Override
	public Account findAccount(String username, String password) {
		String hql = "from Account where username = ? and password = ?";
		Object[] params = new Object[]{ username, password };
		return this.getItem(hql, params);
	}




}
