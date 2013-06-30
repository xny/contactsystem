package com.app.contactsystem.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.contactsystem.Constant;
import com.app.contactsystem.model.SubCompany;
import com.app.contactsystem.service.SubCompanyManageService;

@Controller
public class SubCompanyManageController extends CommonController {

	@Autowired
	private SubCompanyManageService subCompanyManageService;
	
	@RequestMapping("/subCompanyList")
	@ResponseBody
	public Map<String, Object> getSubCompanyList(HttpServletRequest request) {
		if (this.verifySession(request)) {
			List<SubCompany> subCompanyList = subCompanyManageService.getAllSubCompany();
			return this.generateResponse(true, subCompanyList);
		}
		return this.generateResponse(false, Constant.INVALID_SESSION);
	}
}
