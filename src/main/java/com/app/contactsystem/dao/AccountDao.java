package com.app.contactsystem.dao;

import com.app.contactsystem.model.Account;

public interface AccountDao extends GenericDao<Account, Long> {

	Account findAccount(String username, String password);
}
