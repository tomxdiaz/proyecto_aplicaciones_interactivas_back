package com.uade.grupo5.api_trabajo_practico.services;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    @Value("${application.security.jwt.secretKey}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    // ** SIRVE **
    public String generateToken(UserDetails userDetails) throws Exception {
      try{
        return Jwts
          .builder()
          .subject(userDetails.getUsername())
          .issuedAt(new Date(System.currentTimeMillis()))
          .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
          .signWith(getSecretKey())
          .compact();
      }catch(Exception error){
        throw new Exception("[JwtService.generateToken] -> " + error.getMessage());
      }
    }

    // ** SIRVE **
    public boolean isTokenValid(String token, UserDetails userDetails) throws Exception{
      try{
        final String username = extractClaim(token, Claims::getSubject);
        return (username.equals(userDetails.getUsername()));
      }catch(Exception error){
        throw new Exception("[JwtService.isTokenValid] -> " + error.getMessage());
      }
    }

    // ** SIRVE **
    public boolean isTokenExpired(String token) throws Exception{
      try{
        return extractClaim(token, Claims::getExpiration).before(new Date());
      }catch(Exception error){
        throw new Exception("[JwtService.isTokenValid] -> " + error.getMessage());
      }
    }

    // ** SIRVE **
    public String extractUsername(String token) throws Exception{
      try{
        return extractClaim(token, (n) -> n.getSubject());
      }catch(Exception error){
        throw new Exception("[JwtService.extractUsername] -> " + error.getMessage());
      }
    }

    // ** SIRVE **
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws Exception{
      try{
        final Claims claims = Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claimsResolver.apply(claims);
      }catch(Exception error){
        throw new Exception("[JwtService.extractClaim] -> " + error.getMessage());
      }
    }

    // ** SIRVE **
    private SecretKey getSecretKey() throws Exception{
      try{
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
      }catch(Exception error){
        throw new Exception("[JwtService.getSecretKey] -> " + error.getMessage());
      }
    }
}
