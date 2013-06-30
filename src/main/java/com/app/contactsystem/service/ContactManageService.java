package com.app.contactsystem.service;

import java.util.List;

import com.app.contactsystem.model.Contact;

public interface ContactManageService {
	Contact addContact(Contact contact);
	Contact editContact(Contact contact);
	void deleteContact(Long id);
	int getContactsCount(List<String> condition);
	List<Contact> getContactList(List<String> condition, int limit, int start);
}
