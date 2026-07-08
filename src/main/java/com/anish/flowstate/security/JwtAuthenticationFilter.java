package com.anish.flowstate.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            UserDetailsService userDetailsService) {

        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authHeader = request.getHeader("Authorization");
//        System.out.println("========== JWT FILTER HIT ==========");
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String jwt = authHeader.substring(7);
//        String username =  jwtService.extractUsername(jwt);
//        System.out.println("Header = " + authHeader);
//
//        if (username != null &&
//                SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//            System.out.println("Loading UserDetails...");
//            if (jwtService.isTokenValid(jwt, userDetails)) {
//
//                System.out.println("JWT VALID");
//                UsernamePasswordAuthenticationToken authToken =
//                        new UsernamePasswordAuthenticationToken(
//                                userDetails,
//                                null,
//                                userDetails.getAuthorities()
//                        );
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//                System.out.println("Authentication Stored");
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("\n========== JWT FILTER HIT ==========");

        String authHeader = request.getHeader("Authorization");
        System.out.println("Header: " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("No Bearer token found. Continuing filter chain...");
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring(7);
        System.out.println("JWT: " + jwt);

        String username = jwtService.extractUsername(jwt);
        System.out.println("Extracted Username: " + username);

        if (username != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            System.out.println("Loading UserDetails...");

            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(username);

            System.out.println("Loaded User: " + userDetails.getUsername());

            boolean valid = jwtService.isTokenValid(jwt, userDetails);
            System.out.println("Token Valid: " + valid);

            if (valid) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authToken);

                System.out.println("Authentication Stored in SecurityContext");
            }
        } else {
            System.out.println("Username is null OR Authentication already exists.");
        }

        System.out.println("Continuing Filter Chain...");
        filterChain.doFilter(request, response);
    }
}
