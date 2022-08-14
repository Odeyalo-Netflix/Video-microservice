package com.odeyalo.analog.netflix.video.support;

import io.jsonwebtoken.Claims;

public interface JwtTokenParser extends JwtTokenValidator {

    Claims getBody(String token);
}
