package com.sistema.blog.sistema_blog_spring_boot_api_rest.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.sistema.blog.sistema_blog_spring_boot_api_rest.Exceptions.BlogAppException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;    

    public String generarToken(Authentication authentication) {
        // Implementación para generar el token JWT
        String username = authentication.getName();
        Date fechaActual = new Date();
        Date fechaExpiracion = new Date(fechaActual.getTime() + jwtExpirationInMs);
        
        String token = Jwts.builder()
                                    .subject(username)
                                    .issuedAt(fechaActual)
                                    .expiration(fechaExpiracion)
                                    .signWith(getSignInKey(), Jwts.SIG.HS512).compact();
        return token;
    }

    public String obtenerUsernameDelJWT(String token) {

        Claims claims = Jwts.parser()
                        .verifyWith(getSignInKey())
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();
        return claims.getSubject();
    }

    public boolean validarToken(String token) {
        try 
        {
            Jwts.parser()
            .verifyWith(getSignInKey())
            .build()
            .parseSignedClaims(token);

            return true;
        } 
        catch (SignatureException ex) 
        {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");
        }
        catch (MalformedJwtException ex) 
        {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        }
        catch (ExpiredJwtException ex) 
        {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "JWT token expired");
        }
        catch (UnsupportedJwtException ex) 
        {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "JWT token not compatible");
        }
        catch (IllegalArgumentException ex) 
        {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "JWT Claims empty");
        }                    
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }    
}
