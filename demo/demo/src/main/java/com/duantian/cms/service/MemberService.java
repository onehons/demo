package com.duantian.cms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.duantian.cms.entity.Member;

public interface MemberService {

	Member save(Member entity);

	void delete(Integer id);

	boolean isExistEmail(String email);

	Page<Member> findPage(PageRequest pageRequest);

}
