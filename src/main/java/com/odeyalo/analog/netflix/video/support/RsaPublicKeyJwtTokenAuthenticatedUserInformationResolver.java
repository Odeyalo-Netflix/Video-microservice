package com.odeyalo.analog.netflix.video.support;

import com.odeyalo.analog.netflix.video.configuration.security.AuthenticatedUserInformation;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RsaPublicKeyJwtTokenAuthenticatedUserInformationResolver implements JwtTokenAuthenticatedUserInformationResolver {
    private final Logger logger = LoggerFactory.getLogger(RsaPublicKeyJwtTokenAuthenticatedUserInformationResolver.class);
    private final JwtTokenParser parser;
    private static final String USER_ID_CLAIMS_KEY = "id";
    private static final String USER_NICKNAME_CLAIMS_KEY = "nickname";
    private static final String USER_ROLES_CLAIMS_KEY = "roles";

    @Autowired
    public RsaPublicKeyJwtTokenAuthenticatedUserInformationResolver(@Qualifier("rsaPublicKeyJwtTokenParser") JwtTokenParser parser) {
        this.parser = parser;
    }

    @Override
    public AuthenticatedUserInformation getInfo(String token) {
        Claims body = this.parser.getBody(token);
        Integer id = body.get(USER_ID_CLAIMS_KEY, Integer.class);
        String nickname = body.get(USER_NICKNAME_CLAIMS_KEY, String.class);
        List<GrantedAuthority> roles = convertToGrantedAuthority((List<String>) body.get(USER_ROLES_CLAIMS_KEY));
        return new AuthenticatedUserInformation(id, nickname, roles);
    }


    protected List<GrantedAuthority> convertToGrantedAuthority(List<String> roles) {
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
