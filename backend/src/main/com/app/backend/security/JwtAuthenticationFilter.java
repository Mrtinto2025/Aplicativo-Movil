package com.app.backend.security;

import jakarta.servlet.Filter.filterChain;
import jakarta.servlet.servletExecption;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springfrmaework.security.authentication.usernamePasswordAuthenticationToken;
import org.springframwork.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframwork.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.rmi.server.ServerCloneException;

@Component


public class JwtAuthenticationFilter extends OncePerRequestFilter{

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse, filterChain)
    throws ServeletException, IOException{
        try {
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                String username = tokenProvider.getUsernmaeFromJWT(jwt);

                UserDetails userDetails = userDetailsService.loadUserByusername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, credentials: null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtfromRequest(HttpServerletRequest request) {
        String bearerToken = request.getHeader(name:"Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.starWith("Bearer")){
            return bearerToken.substring(7);
        }
        return null;
    }

}
