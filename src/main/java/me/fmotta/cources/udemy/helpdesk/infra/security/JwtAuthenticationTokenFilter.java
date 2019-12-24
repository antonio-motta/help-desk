package me.fmotta.cources.udemy.helpdesk.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import sun.plugin.liveconnect.SecurityContextHelper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filtro de autenticação jwt
 *
 * @author Antonio Motta
 * @Copyright (C)2019 - help-desk | MIT License.
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    /**
     * Serviço de usuarios jwt
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Utilitários de usuarios do jwt
     */
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * Implementação do filtro jwt
     *
     * @param httpServletRequest  request servlet
     * @param httpServletResponse response servlet
     * @param filterChain         filtro
     * @throws ServletException exceção do servlet
     * @throws IOException      exceção de entrada
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authToken = httpServletRequest.getHeader("Authorization");
        String username = jwtTokenUtil.getUsernameFromToken(authToken);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                logger.info("Usuario " + username + " autenticado, setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
