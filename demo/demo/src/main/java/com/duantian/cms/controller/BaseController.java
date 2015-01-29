package com.duantian.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.duantian.cms.core.spring.Message;

public class BaseController {

	@Autowired
	protected HttpServletRequest request;

	protected String message(String key, Object... args) {
		return Message.get(key, args);
	}

}
