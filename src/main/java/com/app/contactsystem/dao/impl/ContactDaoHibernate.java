package com.app.contactsystem.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.app.contactsystem.dao.ContactDao;
import com.app.contactsystem.model.Contact;

@Repository("contactDao")
public class ContactDaoHibernate extends GenericDaoHibernate<Contact, Long> implements ContactDao {

	public ContactDaoHibernate() {
		super(Contact.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getContactList(List<String> condition, final int limit, final int start) {
		final StringBuilder hql = new StringBuilder("from Contact");
		if (null != condition && condition.size() > 0) {
			hql.append(" where");
			for (int i = 0; i < condition.size(); i++) {
				if (i == 0) {
					hql.append(" " + condition.get(i));
				} else {
					hql.append(" and " + condition.get(i));
				}
			}
		}

		List<Contact> contactList = super.getHibernateTemplate().executeFind(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) {
				try {
					final Query query = session.createQuery(hql.toString());
					query.setFirstResult(start);
					query.setMaxResults(limit);
					return query.list();
				} catch (HibernateException e) {
					return null;
				}
				
			}
			
		});
		return contactList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getContactsCount(List<String> condition) {
		final StringBuilder hql = new StringBuilder("from Contact");
		if (null != condition && condition.size() > 0) {
			hql.append(" where");
			for (int i = 0; i < condition.size(); i++) {
				if (i == 0) {
					hql.append(" " + condition.get(i));
				} else {
					hql.append(" and " + condition.get(i));
				}
			}
		}
		
		List<Contact> contactList = super.getHibernateTemplate().executeFind(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) {
				try {
					final Query query = session.createQuery(hql.toString());
					return query.list();
				} catch (HibernateException e) {
					return null;
				}
				
			}
			
		});
		
		return null == contactList ? 0 : contactList.size();
	}

}
