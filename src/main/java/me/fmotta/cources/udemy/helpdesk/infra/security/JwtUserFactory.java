package me.fmotta.cources.udemy.helpdesk.infra.security;

import me.fmotta.cources.udemy.helpdesk.constants.Profile;
import me.fmotta.cources.udemy.helpdesk.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * Conversor de usuario para usuario do jwt
 *
 * @author Antonio Motta
 * @Copyright (C)2019 - help-desk | MIT License.
 */
public class JwtUserFactory {

    /**
     * Construtor
     */
    private JwtUserFactory() {
    }

    /**
     * Conversor de usuario para usuario do jwt
     *
     * @param user usuario
     * @return usuaro do jwt
     */
    public static JwtUser create(User user) {
        return new JwtUser(user.getId(), user.getEmail(), user.getPassword(), mapToGrantedAuthorities(user.getProfile()));
    }

    /**
     * Converter perfils em permissões autoritarias
     *
     * @param profile perfil
     * @return autoridades do jwt
     */
    private static List<GrantedAuthority> mapToGrantedAuthorities(Profile profile) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(profile.toString()));
        return authorities;
    }
}
