package com.duantian.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController {

	@RequestMapping("/test")
	public String test() {
		request.setAttribute("param", "index param");
		return "test";
	}

}
