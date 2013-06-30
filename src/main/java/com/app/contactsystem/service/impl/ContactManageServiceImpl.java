package com.app.contactsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.contactsystem.dao.ContactDao;
import com.app.contactsystem.model.Contact;
import com.app.contactsystem.service.ContactManageService;

@Service("contactManageService")
public class ContactManageServiceImpl implements ContactManageService {

	@Autowired
	private ContactDao contactDao;
	
	@Override
	public Contact addContact(Contact contact) {
		return contactDao.save(contact);
	}

	@Override
	public Contact editContact(Contact contact) {
		return contactDao.save(contact);
	}

	@Override
	public void deleteContact(Long id) {
		contactDao.remove(id);
	}

	@Override
	public int getContactsCount(List<String> condition) {
		return contactDao.getContactsCount(condition);
	}

	@Override
	public List<Contact> getContactList(List<String> condition, int limit, int start) {
		return contactDao.getContactList(condition, limit, start);
	}

}
