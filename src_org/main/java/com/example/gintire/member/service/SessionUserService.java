package com.example.gintire.member.service;

import com.example.gintire.member.domain.user.Member;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class SessionUserService {
    public String getAuthUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public void applyAuthToCtxHolder(Member member) {
        Authentication auth = new UsernamePasswordAuthenticationToken(member, null, member.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
