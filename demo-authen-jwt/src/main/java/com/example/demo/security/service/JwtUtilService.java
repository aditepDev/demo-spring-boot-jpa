package com.example.demo.security.service;

import com.example.demo.security.exception.JwtExceptions;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
@Setter
public class JwtUtilService {

    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expirationUnit}")
    private String expireUnit;
    @Value("${jwt.expiration}")
    private int expireTime;
    @Value("${jwt.refreshExpirationUnit}")
    private String refreshExpireUnit;
    @Value("${jwt.refreshExpiration}")
    private int refreshExpireTime;

    public String extractUserUuid(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);

    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();

    }

    public String generateToken(String uuid) throws JwtExceptions {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, uuid, expireUnit, expireTime);
    }

    public String generateRefreshToken(String uuid) throws JwtExceptions {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, uuid, refreshExpireUnit, refreshExpireTime);
    }

    private String createToken(Map<String, Object> claims, String subject, String unit, int time) throws JwtExceptions {


        Calendar timeExpire = Calendar.getInstance();

        switch (unit) {
            case "S":
                timeExpire.add(Calendar.SECOND, time);
                break;
            case "MN":
                timeExpire.add(Calendar.MINUTE, time);
                break;
            case "H":
                timeExpire.add(Calendar.HOUR, time);
                break;
            case "D":
                timeExpire.add(Calendar.DAY_OF_WEEK, time);
                break;
            case "M":
                timeExpire.add(Calendar.MONTH, time);
                break;
            case "Y":
                timeExpire.add(Calendar.YEAR, time);
                break;
            default:
                throw JwtExceptions.jwtUnitMissing();
        }

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(timeExpire.getTime()).signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
            throw ex;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
            throw ex;
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
            throw ex;
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
            throw ex;
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
            throw ex;
        }
    }

}
