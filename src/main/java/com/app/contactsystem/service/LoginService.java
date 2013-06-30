package com.app.contactsystem.service;

import com.app.contactsystem.model.Account;

public interface LoginService {
	Account login(String username, String password);
}
