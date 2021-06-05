package com.roger.flippy.security.util;

import com.roger.flippy.security.transfer.JwtUserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class JwtTokenValidator {
    @Value("${jwt.secret}")
    private String secret;

    public JwtUserDto parseToken(String token) {
        JwtUserDto u = null;

        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            u = new JwtUserDto();
            u.setUsername(body.getSubject());
            u.setId(Long.parseLong((String) body.get("userId")));
            u.setAddress((String) body.get("address"));
            u.setRole((String) body.get("role"));

        } catch (JwtException e) {
            log.error("Error occured while jwt parsing" + e.getMessage());
        }
        return u;
    }
}
