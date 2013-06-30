package com.app.contactsystem.service;

import java.util.List;

import com.app.contactsystem.model.Account;

public interface AccountManageService {
	Account addAccount(Account account);
	Account editAccount(Account account);
	void deleteAccount(Long id);
	List<Account> getAllAccount();
}
