package com.app.contactsystem.web.controller;

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
import com.app.contactsystem.service.AccountManageService;

@Controller
public class AccountManageController extends CommonController {
	
	@Autowired
	private AccountManageService accountManageService;

	@RequestMapping("/addAccount")
	@ResponseBody
	public Map<String, Object> addAccount(
			@RequestParam(value = "userName", defaultValue = "") String username,
			@RequestParam(value = "password", defaultValue = "") String password,
			@RequestParam(value = "role", defaultValue = "0") String role,
			HttpServletRequest request) {
		if (this.verifySession(request)) {
			if (!this.verifyParams(username, password)) {
				return this.generateResponse(false, Constant.INVALID_PARAM);
			}
			Account account = new Account();
			account.setUserName(username);
			account.setPassword(password);
			account.setRole(Integer.parseInt(role));
			account = accountManageService.addAccount(account);
			return this.generateResponse(true, account);
		}
		return this.generateResponse(false, Constant.INVALID_SESSION);
	}
	
	@RequestMapping("/editAccount")
	@ResponseBody
	public Map<String, Object> editAccount(
			@RequestParam(value = "id", defaultValue = "") Long id,
			@RequestParam(value = "userName", defaultValue = "") String username,
			@RequestParam(value = "password", defaultValue = "") String password,
			@RequestParam(value = "role", defaultValue = "0") String role,
			HttpServletRequest request) {
		if (this.verifySession(request)) {
			if (!this.verifyParams(String.valueOf(id), username, password) || !StringUtils.isNumeric(role)) {
				return this.generateResponse(false, Constant.INVALID_PARAM);
			}
			Account account = new Account();
			account.setId(id);
			account.setUserName(username);
			account.setPassword(password);
			account.setRole(Integer.parseInt(role));
			account = accountManageService.editAccount(account);
			return this.generateResponse(true, account);
		}
		return this.generateResponse(false, Constant.INVALID_SESSION);
	}
	
	@RequestMapping("/deleteAccount")
	@ResponseBody
	public Map<String, Object> deleteAccount(@RequestParam(value = "id", defaultValue = "") Long id,
			HttpServletRequest request) {
		if (this.verifySession(request)) {
			accountManageService.deleteAccount(id);
			return this.generateResponse(true, null);
		}
		return this.generateResponse(false, Constant.INVALID_SESSION);
	}
	
	@RequestMapping("/accountList")
	@ResponseBody
	public Map<String, Object> getAccountList(HttpServletRequest request) {
		if (this.verifySession(request)) {
			List<Account> accountList = accountManageService.getAllAccount();
			return this.generateResponse(true, accountList);
		}
		return this.generateResponse(false, Constant.INVALID_SESSION);
	}
}
