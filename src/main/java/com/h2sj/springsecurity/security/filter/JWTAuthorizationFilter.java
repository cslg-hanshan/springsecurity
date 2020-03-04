package com.h2sj.springsecurity.security.filter;

import com.alibaba.fastjson.JSON;
import com.h2sj.springsecurity.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private UserDetailsService userDetailsService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //获取请求头token
        String header = request.getHeader(JwtUtils.TOKEN_HEADER);

        if (header == null || !header.startsWith(JwtUtils.TOKEN_PREFIX)){
            chain.doFilter(request,response);
            return;
        }
        try {
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(header));
        } catch (Exception e) {
            e.printStackTrace();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);

            Map<String,Object> ret = new HashMap<>();
            ret.put("code",403);
            ret.put("msg",e.getMessage());
            String jsonString = JSON.toJSONString(ret);

            response.getWriter().write(jsonString);
            response.getWriter().flush();
            return;
        }
        super.doFilterInternal(request, response, chain);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) throws Exception {
        String token = tokenHeader.replace(JwtUtils.TOKEN_PREFIX, "");
        boolean expiration = JwtUtils.isExpiration(token);
        if (expiration){
            throw new Exception("token超时");
        }
        // 设置UsernamePasswordAuthenticationToken
        String username = JwtUtils.getUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken AuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

        return AuthenticationToken;
    }
}
