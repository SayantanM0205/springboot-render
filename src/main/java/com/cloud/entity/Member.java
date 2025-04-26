package com.cloud.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Members")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member {
	@Id
	private String memberId;
	private String memberName;
	private String memberAge;
	private String memberGender;
	private String memberStatus;
	private LocalDateTime createdDateTime;
	private LocalDateTime modifiedDateTime;

}
