package com.duantian.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duantian.cms.entity.Member;
import com.duantian.cms.repository.MemberRepository;
import com.duantian.cms.service.MemberService;

@Service
@CacheConfig(cacheNames = "default")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;

	@Transactional
	public Member save(Member entity) {
		return memberRepository.save(entity);
	}

	@Transactional
	public void delete(Integer id) {
		memberRepository.delete(id);
	}

	@Transactional(readOnly = true)
	public boolean isExistEmail(String email) {
		List<Member> list = memberRepository.findByEmail(email);
		if (list == null || list.size() == 0)
			return false;
		return true;
	}

	@Transactional
	@Cacheable
	public Page<Member> findPage(PageRequest pageRequest) {
		return memberRepository.findAll(pageRequest);
	}

}
