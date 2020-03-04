package com.h2sj.springsecurity.service;

import com.h2sj.springsecurity.entity.Member;
import com.h2sj.springsecurity.entity.Permission;
import com.h2sj.springsecurity.entity.Role;
import com.h2sj.springsecurity.entity.RoleToPermission;
import com.h2sj.springsecurity.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {


    @Autowired
    private MemberRepository memberRepository;


    public Member getByMusername(String un){
        Member member = new Member();
        member.setMUsername(un);
        Example<Member> memberExample = Example.of(member);
        Optional<Member> memberOptional = memberRepository.findOne(memberExample);
        return memberOptional.orElseGet(null);
    }

    @Transactional
    public Role getRoleByMusername(String un){
        Member member = getByMusername(un);
        return member.getRole();
    }

    @Transactional
    public List<Permission> getPermissionsByMusername(String un){
        Role role = getRoleByMusername(un);
        List<RoleToPermission> permissions = role.getPermissions();
        List<Permission> permissionList = new ArrayList<>();
        for (RoleToPermission roleToPermission:permissions){
            permissionList.add(roleToPermission.getPermission());
        }
        return permissionList;
    }
}
