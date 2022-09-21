package br.univille.apidacs2022.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTRequestFilter extends OncePerRequestFilter{


    @Autowired
    private JWTUtil serviceJWT;
    @Autowired
    private UserDetailsService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
            String authorizationHeader = request.getHeader("Authorization");
            String username = null; 
            String token = null;

            if(authorizationHeader != null &&
            authorizationHeader.startsWith("Bearer ")) {
                token = authorizationHeader.substring(7);
                username = serviceJWT.extractUserName(token);
            }

            if(username != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userService.loadUserByUsername(username);
                if(serviceJWT.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken ut = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    ut.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(ut);
                }
            }


            filterChain.doFilter(request, response);

    }
    
}
