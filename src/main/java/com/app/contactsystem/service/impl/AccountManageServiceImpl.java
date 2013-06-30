package com.app.contactsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.contactsystem.dao.AccountDao;
import com.app.contactsystem.model.Account;
import com.app.contactsystem.service.AccountManageService;

@Service("accountManageService")
public class AccountManageServiceImpl implements AccountManageService {

	@Autowired
	private AccountDao accountDao;
	
	@Override
	public Account addAccount(Account account) {
		return accountDao.save(account);
	}

	@Override
	public Account editAccount(Account account) {
		return accountDao.save(account);
	}

	@Override
	public void deleteAccount(Long id) {
		accountDao.remove(id);
	}

	@Override
	public List<Account> getAllAccount() {
		return accountDao.getAll();
	}

}
