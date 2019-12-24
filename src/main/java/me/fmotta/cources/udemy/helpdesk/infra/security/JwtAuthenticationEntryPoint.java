package me.fmotta.cources.udemy.helpdesk.infra.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * Implementação de autenticação jwt
 *
 * @author Antonio Motta
 * @Copyright (C)2019 - help-desk | MIT License.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    /**
     * Serialização padrão.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Inicialização do token
     *
     * @param httpServletRequest  requisição servlet
     * @param httpServletResponse response servlet
     * @param e                   exceção de autenticação
     * @throws IOException      exceção de entrada e saida
     * @throws ServletException execeção de servlet
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
