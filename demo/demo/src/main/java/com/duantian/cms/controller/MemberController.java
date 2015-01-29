package com.duantian.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duantian.cms.core.util.Pagination;
import com.duantian.cms.entity.Member;
import com.duantian.cms.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController extends BaseController {

	@Autowired
	private MemberService memberService;

	@RequestMapping("add")
	public String add() {
		return "front/member/add";
	}

	@RequestMapping("list")
	public String list(Pagination pagination) {
		PageRequest pageRequest = pagination.getPageRequest();
		Page<Member> page = memberService.findPage(pageRequest);
		request.setAttribute("page", page);
		return "front/member/list";
	}

	@RequestMapping("save")
	@ResponseBody
	public String save(Member entity) {
		memberService.save(entity);
		return message("execute.success");
	}

	@RequestMapping("check")
	@ResponseBody
	public String check() {
		return message("application.name");
	}

}
