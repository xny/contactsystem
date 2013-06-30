package com.app.contactsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.contactsystem.dao.AccountDao;
import com.app.contactsystem.model.Account;
import com.app.contactsystem.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private AccountDao accountDao;
	
	@Override
	public Account login(String username, String password) {
		Account account = accountDao.findAccount(username, password);
		return account;
	}

}
