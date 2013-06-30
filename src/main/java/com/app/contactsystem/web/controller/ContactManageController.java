package com.app.contactsystem.web.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.contactsystem.Constant;
import com.app.contactsystem.model.Account;
import com.app.contactsystem.model.CompanyType;
import com.app.contactsystem.model.Contact;
import com.app.contactsystem.model.SubCompany;
import com.app.contactsystem.service.ContactManageService;

@Controller
public class ContactManageController extends CommonController {

	@Autowired
	private ContactManageService contactManageService;
	
	@RequestMapping("/addContact")
	@ResponseBody
	public Map<String, Object> addContact(Contact contact,
			@RequestParam(value = "companyTypeId", required = false) String companyTypeId,
			@RequestParam(value = "subCompanyId", required = false) String subCompanyId,
			HttpServletRequest request) {
		if (this.verifySession(request)) {
			Long accountId = ((Account) request.getSession().getAttribute("account")).getId();
			Account account = new Account();
			account.setId(accountId);
			contact.setAccount(account);
			if (!StringUtils.isEmpty(companyTypeId) && StringUtils.isNumeric(companyTypeId)) {
				CompanyType companyType = new CompanyType();
				companyType.setId(Long.valueOf(companyTypeId));
				contact.setCompanyType(companyType);
			}
			if (!StringUtils.isEmpty(subCompanyId) && StringUtils.isNumeric(subCompanyId)) {
				SubCompany subCompany = new SubCompany();
				subCompany.setId(Long.valueOf(subCompanyId));
				contact.setSubCompany(subCompany);
			}
			contact.setCreateTime(new Timestamp(System.currentTimeMillis()));
			contact = contactManageService.addContact(contact);
			return this.generateResponse(true, contact);
		}
		return this.generateResponse(false, Constant.INVALID_SESSION);
	}
	
	@RequestMapping("/editContact")
	@ResponseBody
	public Map<String, Object> editContact(Contact contact,
			@RequestParam(value = "companyTypeId", required = false) String companyTypeId,
			@RequestParam(value = "subCompanyId", required = false) String subCompanyId,
			HttpServletRequest request) {
		if (this.verifySession(request)) {
			Long accountId = ((Account) request.getSession().getAttribute("account")).getId();
			Account account = new Account();
			account.setId(accountId);
			contact.setAccount(account);
			if (!StringUtils.isEmpty(companyTypeId) && StringUtils.isNumeric(companyTypeId)) {
				CompanyType companyType = new CompanyType();
				companyType.setId(Long.valueOf(companyTypeId));
				contact.setCompanyType(companyType);
			}
			if (!StringUtils.isEmpty(subCompanyId) && StringUtils.isNumeric(subCompanyId)) {
				SubCompany subCompany = new SubCompany();
				subCompany.setId(Long.valueOf(subCompanyId));
				contact.setSubCompany(subCompany);
			}
			contact.setCreateTime(new Timestamp(System.currentTimeMillis()));
			contact = contactManageService.editContact(contact);
			return this.generateResponse(true, contact);
		}
		return this.generateResponse(false, Constant.INVALID_SESSION);
	}
	
	@RequestMapping("/deleteContact")
	@ResponseBody
	public Map<String, Object> deleteContact(
			@RequestParam(value = "id", defaultValue = "") Long id,
			HttpServletRequest request) {
		if (this.verifySession(request)) {
			contactManageService.deleteContact(id);
			return this.generateResponse(true, null);
		}
		return this.generateResponse(false, Constant.INVALID_SESSION);
	}
	

//	/**
//	 * get the contact list with page limit.
//	 * @param limit		the number of contacts in every page.
//	 * @param start		the start number of contact.
//	 * @param page		current page.
//	 * @param request	
//	 * @return
//	 */
//	@RequestMapping("/getContactList")
//	@ResponseBody
//	public Map<String, Object> getContactList(
//			@RequestParam(value = "limit", defaultValue = "10") Integer limit,
//			@RequestParam(value = "start", defaultValue = "0") Integer start,
//			@RequestParam(value = "page", defaultValue = "1") Integer page,
//			HttpServletRequest request) {
//		if (this.verifySession(request)) {
//			List<Contact> contactList = contactManageService.getContactList(null, limit, start);
//			int contactsCount = contactManageService.getContactsCount(null);
//			Map<String, Object> result = this.generateResponse(true, contactList);
//			result.put("total", contactsCount);
//			return result;
//		}
//		return this.generateResponse(false, Constant.INVALID_SESSION);
//	}
	
	
	@RequestMapping("/getContactList")
	@ResponseBody
	public Map<String, Object> search(
			@RequestParam(value = "limit", defaultValue = "10") Integer limit,
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "companyTypeId", defaultValue = "") String companyTypeId,
			@RequestParam(value = "subCompanyId", defaultValue = "") String subCompanyId,
			@RequestParam(value = "frequency", defaultValue = "") String frequency,
			HttpServletRequest request) {
		if (this.verifySession(request)) {
			List<String> condition = new ArrayList<String>();
			if (!StringUtils.isEmpty(name)) {
				condition.add("name like '%" + name + "%'");
			}
			if (!StringUtils.isEmpty(companyTypeId)) {
				condition.add("company_type_id = " + companyTypeId);
			}
			if (!StringUtils.isEmpty(subCompanyId)) {
				condition.add("sub_company_id = " + subCompanyId);
			}
			if (!StringUtils.isEmpty(frequency)) {
				condition.add("frequency = " + frequency);
			}
			List<Contact> contactList = contactManageService.getContactList(condition, limit, start);
			int contactsCount = contactManageService.getContactsCount(condition);
			Map<String, Object> result = this.generateResponse(true, contactList);
			result.put("total", contactsCount);
			return result;
		}
		return this.generateResponse(false, Constant.INVALID_SESSION);
		
	}
	
}
