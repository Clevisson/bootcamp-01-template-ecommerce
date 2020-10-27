package com.zup.mercadolivre.jwt;

import com.zup.mercadolivre.security.ImplementsUserDetails;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    @Value("${mercadolivre.app.jwtSecret}")
    private String jwtSecret;

    @Value("${mercadolivre.app.jwtExpirationMs}")
    private String jwtExpirationsMs;

    public String generateJwtToken(Authentication authentication) {
        ImplementsUserDetails userOwner = (ImplementsUserDetails) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userOwner.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationsMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Assinatura JWT inválida:\n{}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Token JWT inválido:\n {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("O token JWT expirou:\n{}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("O token JWT não é compatível:\n {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("A string de reivindicações JWT está vazia: {}", e.getMessage());
        }

        return false;
    }
}
