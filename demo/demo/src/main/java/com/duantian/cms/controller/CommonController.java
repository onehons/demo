package com.duantian.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController extends BaseController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

}
