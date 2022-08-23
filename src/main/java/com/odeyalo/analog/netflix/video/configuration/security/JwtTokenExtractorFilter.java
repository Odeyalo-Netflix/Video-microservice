package com.odeyalo.analog.netflix.video.configuration.security;

import com.odeyalo.analog.netflix.video.support.JwtTokenAuthenticatedUserInformationResolver;
import com.odeyalo.netflix.jwt.JwtTokenValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenExtractorFilter extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(JwtTokenExtractorFilter.class);
    private static final String BEARER_JWT_PREFIX = "Bearer ";
    private static final Integer BEARER_JWT_PREFIX_LENGTH = BEARER_JWT_PREFIX.length();
    private final JwtTokenAuthenticatedUserInformationResolver informationResolver;
    private final JwtTokenValidator jwtTokenValidator;

    @Autowired
    public JwtTokenExtractorFilter(JwtTokenAuthenticatedUserInformationResolver informationResolver, JwtTokenValidator jwtTokenValidator) {
        this.informationResolver = informationResolver;
        this.jwtTokenValidator = jwtTokenValidator;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = extractAuthHeader(authHeader);
        if (token != null && jwtTokenValidator.isTokenValid(token)) {
            AuthenticatedUserInformation info = informationResolver.getInfo(token);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(info, null, info.getAuthorities()));
            this.logger.info("Successful set authentication with data: {}", info);
        }
        filterChain.doFilter(request, response);
    }

    protected String extractAuthHeader(String header) {
        if (header == null || !header.startsWith(BEARER_JWT_PREFIX))
            return null;
        return header.substring(BEARER_JWT_PREFIX_LENGTH);
    }
}
