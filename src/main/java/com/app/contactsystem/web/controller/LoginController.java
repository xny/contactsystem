package com.app.contactsystem.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.contactsystem.model.Account;
import com.app.contactsystem.service.LoginService;

@Controller
public class LoginController extends CommonController {

	@Autowired
	private LoginService loginService;

	
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(@RequestParam(value = "userName", defaultValue = "") String username,
			@RequestParam(value = "password", defaultValue = "") String password,
			HttpServletRequest request) {
		if (!this.verifyParams(username, password)) {
			return this.generateResponse(false, null);
		}
		Account account = loginService.login(username, password);
		if (null == account) {
			return this.generateResponse(false, null);
		}
		HttpSession session = request.getSession();
		session.setAttribute("account", account);
		return this.generateResponse(true, null);
	}
	
	@RequestMapping("/index")
	public String jumpToIndex(HttpServletRequest request) {
		if (this.verifySession(request)) {
			HttpSession session = request.getSession();
			Account account = (Account) session.getAttribute("account");
			request.setAttribute("userName", account.getUserName());
			request.setAttribute("role", account.getRole());
			return "index";
		}
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.invalidate();
		return "login";
	}
}
