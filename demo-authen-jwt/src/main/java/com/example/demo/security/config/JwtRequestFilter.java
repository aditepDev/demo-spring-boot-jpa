package com.example.demo.security.config;

import com.example.demo.security.response.SendErrorResponseJson;
import com.example.demo.security.model.CustomUserDetails;
import com.example.demo.security.service.CustomUserDetailsService;
import com.example.demo.security.service.JwtUtilService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService userDetailsService;


    @Autowired
    private JwtUtilService jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        try {
            // Get authorization header and validate
            final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (!StringUtils.hasText(header) || !header.startsWith("Bearer ")) {
                chain.doFilter(request, response);
                return;
            }

            // Get jwt token and validate
            final String token = getBearerToken(header);
            if (!jwtUtil.validateToken(token)) {
                chain.doFilter(request, response);
                return;
            }

            CustomUserDetails userDetails = userDetailsService.loadUserByUuid(jwtUtil.extractUserUuid(token));

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getUser(), userDetails.getAuthorities());

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);


        } catch (SignatureException ex) {
            sendError(901, "Invalid JWT signature", response, request);
        } catch (MalformedJwtException ex) {
            sendError(902, "Invalid JWT token", response, request);
        } catch (ExpiredJwtException ex) {
            sendError(903, "Expired JWT token", response, request);
        } catch (UsernameNotFoundException ex) {
            sendError(904, "no user credentials", response, request);
        }

    }


    public void sendError(int statusCode, String message, HttpServletResponse response, HttpServletRequest request) throws IOException {


        SendErrorResponseJson json = new SendErrorResponseJson();
        ObjectMapper mapper = new ObjectMapper();
        json.setPath(request.getRequestURI());
        json.setStatus(statusCode);
        json.setMessage(message);

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        OutputStream out = response.getOutputStream();
        mapper.writeValue(out, json);
        out.flush();

    }


    public String getBearerToken(String authHeader) {
        if (authHeader.split(" ").length < 2) {
            return null;
        }

        return authHeader.split(" ")[1].trim();
    }


}