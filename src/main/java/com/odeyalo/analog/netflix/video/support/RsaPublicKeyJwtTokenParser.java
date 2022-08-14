package com.odeyalo.analog.netflix.video.support;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.PublicKey;

@Component
public class RsaPublicKeyJwtTokenParser implements JwtTokenParser {

    private final Logger logger = LoggerFactory.getLogger(RsaPublicKeyJwtTokenParser.class);

    @Override
    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(RsaTokenHolder.getPublicKey()).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            this.logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            this.logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            this.logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            this.logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            this.logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public Claims getBody(String token) {
        PublicKey publicKey = RsaTokenHolder.getPublicKey();
        if (publicKey == null) {
            throw new IllegalArgumentException("Public key cannot be null!");
        }
        return (Claims) Jwts.parser().setSigningKey(publicKey).parse(token).getBody();
    }
}
