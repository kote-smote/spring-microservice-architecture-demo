package com.martin.dummyappwithdb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private JwtUtil jwtUtil;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String authorizationHeader = request.getHeader(jwtUtil.getHeaderString());
        if (authorizationHeader == null || !authorizationHeader.startsWith(jwtUtil.getTokenPrefix())) {
            doFilter(request, response, chain);
            return;
        }
    }

    private Authentication getAuthentication(String authorizationHeader) {
       String token = jwtUtil.clearPrefix(authorizationHeader);
       Map<String, Object> tokenClaims = jwtUtil.parseToken(token);
       if (tokenClaims != null) {
            String username = (String) tokenClaims.get("sub");
            if (username != null) {
//                GrantedAuthority[] roles =  (GrantedAuthority[]) tokenClaims.get("roles");
                return new UsernamePasswordAuthenticationToken(username, null, null);
            }
       }
       return null;
    }
}
