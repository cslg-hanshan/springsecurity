package com.h2sj.springsecurity.repository;

import com.h2sj.springsecurity.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long>, JpaSpecificationExecutor<Member> {
}
