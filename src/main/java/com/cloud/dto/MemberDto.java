package com.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDto {
	private String memberId;
	private String memberName;
	private String memberAge;
	private String memberGender;
	private String memberStatus;
}
