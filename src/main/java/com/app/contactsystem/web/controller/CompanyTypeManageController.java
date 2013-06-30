package com.app.contactsystem.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.contactsystem.Constant;
import com.app.contactsystem.model.CompanyType;
import com.app.contactsystem.service.CompanyTypeManageService;

@Controller
public class CompanyTypeManageController extends CommonController {

	@Autowired
	private CompanyTypeManageService companyTypeManageService;
	
	@RequestMapping("/addCompanyType")
	@ResponseBody
	public Map<String, Object> addCompanyType(@RequestParam(value = "type", defaultValue = "") String type,
			HttpServletRequest request) {
		if (this.verifySession(request)) {
			if (!this.verifyParams(type)) {
				return this.generateResponse(false, Constant.INVALID_PARAM);
			}
			CompanyType companyType = new CompanyType();
			companyType.setType(type);
			companyType = companyTypeManageService.addCompanyType(companyType);
			return this.generateResponse(true, companyType);
		}
		return this.generateResponse(false, Constant.INVALID_SESSION);
	}
	
	@RequestMapping("/editCompanyType")
	@ResponseBody
	public Map<String, Object> editCompanyType(
			@RequestParam(value = "id", defaultValue = "") Long id,
			@RequestParam(value = "type", defaultValue = "") String type,
			HttpServletRequest request) {
		if (this.verifySession(request)) {
			if (!this.verifyParams(String.valueOf(id), type)) {
				return this.generateResponse(false, Constant.INVALID_PARAM);
			}
			CompanyType companyType = new CompanyType();
			companyType.setId(id);
			companyType.setType(type);
			companyType = companyTypeManageService.editCompanyType(companyType);
			return this.generateResponse(true, companyType);
		}
		return this.generateResponse(false, Constant.INVALID_SESSION);
	}
	
	@RequestMapping("/deleteCompanyType")
	@ResponseBody
	public Map<String, Object> deleteCompanyType(@RequestParam(value = "id", defaultValue = "") Long id,
			HttpServletRequest request) {
		if (this.verifySession(request)) {
			companyTypeManageService.deleteCompanyType(id);
			return this.generateResponse(true, null);
		}
		return this.generateResponse(false, Constant.INVALID_SESSION);
	}
	
	@RequestMapping("/companyTypeList")
	@ResponseBody
	public Map<String, Object> getCompanyTypeList(HttpServletRequest request) {
		if (this.verifySession(request)) {
			List<CompanyType> companyTypeList = companyTypeManageService.getAllCompanyType();
			return this.generateResponse(true, companyTypeList);
		}
		return this.generateResponse(false, Constant.INVALID_SESSION);
	}
	
}
