package com.cloud.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloud.dto.MemberDto;
import com.cloud.entity.Member;
import com.cloud.mapper.MemberMapper;
import com.cloud.repository.MemberRepository;
import com.cloud.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberRepository memberRepository;
	
	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Override
	public String createMember(MemberDto member) {
		member.setMemberId(UUID.randomUUID().toString());
		log.info("Creating member with Member Id: {}",member.getMemberId());
		Member createMember = MemberMapper.mapmMemberDtoToMember(member);
		createMember.setMemberStatus("Active");
		createMember.setCreatedDateTime(LocalDateTime.now());
		createMember.setModifiedDateTime(LocalDateTime.now());
		memberRepository.save(createMember);
		return createMember.getMemberId();
	}

	@Override
	public MemberDto getMember(@RequestParam String memberId) {
		Member member = memberRepository.findById(memberId).orElseThrow(()->new RuntimeException("Member does not exist with Member Id: "+memberId));
		MemberDto existingMember = MemberMapper.mapMemberToMemberDto(member);
		log.info("Member found: {}",existingMember);
		return existingMember;
	}

	@Override
	public List<MemberDto> getAllMembers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMember(String memberId) {
		// TODO Auto-generated method stub
		
	}
	
	

}
