package com.hms.anikdv.code.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * This is Jwt Authentication Filter
 *
 * @author anikdv
 *
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwtToken = authorizationHeader.substring(7);
            try {
                username = this.jwtUtils.getUsernameFromToken(jwtToken);
                System.out.println("JwtAuthenticationFilter class Username : "+ username);
            } catch (Exception e) {
                // Handle token extraction/validation errors
                logger.error("Error extracting username from token: " + e.getMessage());
            }
        } else {
            logger.error("Invalid Authorization Header Value!");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            // do authenticate user
            if (this.jwtUtils.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } else {
                logger.error("Validation Fails!");
            }
        }
        filterChain.doFilter(request, response);
    }
}
