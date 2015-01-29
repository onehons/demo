package com.duantian.cms.repository;

import java.util.List;

import com.duantian.cms.entity.Member;

public interface MemberRepository extends BaseRepository<Member, Integer> {

	List<Member> findByEmail(String email);

}
