package com.app.contactsystem.dao;

import java.util.List;

import com.app.contactsystem.model.Contact;

public interface ContactDao extends GenericDao<Contact, Long> {
	List<Contact> getContactList(List<String> condition, int limit, int start);
	int getContactsCount(List<String> condition);
}
