package me.fmotta.cources.udemy.helpdesk.infra.security;

import me.fmotta.cources.udemy.helpdesk.model.User;
import me.fmotta.cources.udemy.helpdesk.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Implementação do serviço de usuário da jwt
 *
 * @author Antonio Motta
 * @Copyright (C)2019 - help-desk | MIT License.
 */
@Service
public class JwtUserDetailsServiceImpl implements Serializable {

    /**
     * Serviço de usuário
     */
    @Autowired
    private UserService userService;

    /**
     * Busca usuários por usuário
     *
     * @param username usuario
     * @return usuario do jwt
     */
    private UserDetails loadUserByUsername(String username) {
        User user = userService.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Usuario % não encontrado", username));
        }
        return JwtUserFactory.create(user);
    }
}
