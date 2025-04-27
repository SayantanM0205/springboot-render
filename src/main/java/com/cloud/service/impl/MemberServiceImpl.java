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
import com.cloud.service.RedisService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{
	
	private MemberRepository memberRepository;
	
	private RedisService redisService;
	

	@Override
	public String createMember(MemberDto member) {
		member.setMemberId(UUID.randomUUID().toString());
		member.setMemberStatus("Active");
		log.info("Creating member with Member Id: {}",member.getMemberId());
		Member createMember = MemberMapper.mapmMemberDtoToMember(member);
		createMember.setCreatedDateTime(LocalDateTime.now());
		createMember.setModifiedDateTime(LocalDateTime.now());
		memberRepository.save(createMember);
		log.info("Saving data in redis.........");
		redisService.set(createMember.getMemberId(), member, 3000L);
		return createMember.getMemberId();
	}

	@Override
	public MemberDto getMember(@RequestParam String memberId) {
		MemberDto memberDto = redisService.get(memberId, MemberDto.class);
		if(memberDto != null) {
			log.info("Data retrived from redis for member with Id: {}", memberId);
			return memberDto;
		}
		Member member = memberRepository.findById(memberId).orElseThrow(()->new RuntimeException("Member does not exist with Member Id: "+memberId));
		MemberDto existingMember = MemberMapper.mapMemberToMemberDto(member);
		log.info("Member found in db: {}",existingMember);
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
