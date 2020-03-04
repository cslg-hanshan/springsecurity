package com.h2sj.springsecurity.security.userdetails;

import com.h2sj.springsecurity.entity.Member;
import com.h2sj.springsecurity.entity.Permission;
import com.h2sj.springsecurity.entity.Role;
import com.h2sj.springsecurity.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Member member = memberService.getByMusername(username);
            if (member == null)
                throw new UsernameNotFoundException("username not found");

            //初始授权列表
            Collection<GrantedAuthority> authorities = new ArrayList<>();

            //获取角色
            Role role = memberService.getRoleByMusername(username);
            authorities.add(new SimpleGrantedAuthority(role.getRName()));
            //获取权限
            List<Permission> permissions = memberService.getPermissionsByMusername(username);
            for (Permission permission:permissions)
                authorities.add(new SimpleGrantedAuthority(permission.getPName()));

            UserDetails build = User.builder().username(username).password(member.getMPassword()).authorities(authorities).build();

            return build;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
