package com.example.gintire.member.service;

import org.springframework.security.oauth2.common.util.JsonParserFactory;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JwtUserService {
    public boolean isValidUser (String accessToken, String userEmail) {
        return getTokenEmail(accessToken).equals(userEmail);
    }
    private String getTokenEmail(String accessToken) {
        Jwt jwt = getParedToken(accessToken);
        Map claim = JsonParserFactory.create().parseMap(jwt.getClaims());
        return (String) claim.get("user_email");
    }

    private Jwt getParedToken(String accessToken) {
        return JwtHelper.decode(accessToken.split(" ")[1]);
    }

}
