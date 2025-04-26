package com.cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloud.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>{

}
