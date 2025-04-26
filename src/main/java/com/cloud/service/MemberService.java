package com.cloud.service;

import java.util.List;

import com.cloud.dto.MemberDto;

public interface MemberService {

	String createMember(MemberDto member);
	
	MemberDto getMember(String memberId);
	
	List<MemberDto> getAllMembers();
	
	void deleteMember(String memberId);
}
