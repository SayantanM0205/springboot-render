package com.cloud.mapper;

import com.cloud.dto.MemberDto;
import com.cloud.entity.Member;

public class MemberMapper {
	public static Member mapmMemberDtoToMember(MemberDto memberDto) {
		Member member = new Member();
		member.setMemberId(memberDto.getMemberId());
		member.setMemberName(memberDto.getMemberName());
		member.setMemberAge(memberDto.getMemberAge());
		member.setMemberGender(memberDto.getMemberGender());
		member.setMemberStatus(member.getMemberStatus());
		return member;
	}
	
	public static MemberDto mapMemberToMemberDto(Member member) {
		MemberDto memberDto = new MemberDto();
		memberDto.setMemberId(member.getMemberId());
		memberDto.setMemberName(member.getMemberName());
		memberDto.setMemberAge(member.getMemberAge());
		memberDto.setMemberGender(member.getMemberGender());
		memberDto.setMemberStatus(member.getMemberStatus());
		return memberDto;
		
	}

}
