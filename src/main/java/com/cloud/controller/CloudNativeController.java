package com.cloud.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.dto.MemberDto;
import com.cloud.service.MemberService;

@RestController
@RequestMapping("/api/v1")
public class CloudNativeController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/status")
	public String checkStatus(@RequestParam String name) {
		return "Hello "+name+"! I'm working just fine";
	}
	
	@PostMapping("/member")
	public ResponseEntity<String> createMember(@RequestBody MemberDto member){
		if(member != null) {
			String memberId = memberService.createMember(member);
			if(memberId != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(memberId);
			}
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@GetMapping("/member")
	public ResponseEntity<MemberDto> getMember(@RequestParam String memberId){
		MemberDto member = memberService.getMember(memberId);
		if(member != null) {
			return ResponseEntity.status(HttpStatus.OK).body(member);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
