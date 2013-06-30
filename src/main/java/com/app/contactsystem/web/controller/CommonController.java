package com.app.contactsystem.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;


public class CommonController {

	public Map<String, Object> generateResponse(boolean isSuccess, Object data) {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("success", isSuccess);
		response.put("data", data);
		return response;
	}
	
	public boolean verifySession(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		return null != session.getAttribute("account");
	}
	
	public boolean verifyParams(String... params) {
		for (String param : params) {
			if (StringUtils.isEmpty(param)) {
				return false;
			}
		}
		return true;
	}
}
