package com.cloud.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDto implements Serializable{
	private String memberId;
	private String memberName;
	private String memberAge;
	private String memberGender;
	private String memberStatus;
}
