package it.unicam.cs.ids.justmeet.backend.configuration.jwt;

import java.util.Date;
import java.util.UUID;

import io.jsonwebtoken.impl.DefaultClaims;
import it.unicam.cs.ids.justmeet.backend.configuration.service.IUserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.*;


@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${backend.app.jwtSecret}")
    private String jwtSecret;

    @Value("${backend.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {

        IUserDetailsImpl userPrincipal = (IUserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setIssuer("tester")
                .setClaims(new DefaultClaims())
                .setSubject((userPrincipal.getUsername()))
                .setAudience("io")
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS512, "1234")
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey("1234")
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            //Claims claims =
                    Jwts.parser()
                    .setSigningKey("1234")
                    .parseClaimsJws(authToken)
                    .getBody();
            /*System.out.println("----------------------------");
            System.out.println("ID: " + claims.getId());
            System.out.println("Subject: " + claims.getSubject());
            System.out.println("Issuer: " + claims.getIssuer());
            System.out.println("Expiration: " + claims.getExpiration());*/
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
