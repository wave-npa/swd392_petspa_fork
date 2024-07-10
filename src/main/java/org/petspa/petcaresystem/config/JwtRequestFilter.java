package org.petspa.petcaresystem.config;

import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.petspa.petcaresystem.authenuser.service.AuthenUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenUserService authenUserService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String jwt = (session != null) ? (String) session.getAttribute("jwtToken") : null;

        if (jwt != null) {
            String email = jwtUtil.extractEmail(jwt);
            System.out.println("Email:---------------------------" + email + "---------------------------");
            String role = jwtUtil.extractRole(jwt);
            System.out.println("Role:---------------------------" + role + "---------------------------");

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = authenUserService.loadUserByEmail(email);
                System.out.println("User name:---------------------------" + userDetails.getUsername() + "---------------------------");
                if (jwtUtil.validateToken(jwt, userDetails)) {
                    List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));
                    System.out.println(authorities);
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
