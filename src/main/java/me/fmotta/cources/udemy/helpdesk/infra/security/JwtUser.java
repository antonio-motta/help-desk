package me.fmotta.cources.udemy.helpdesk.infra.security;

import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Usuario do JWT
 *
 * @author Antonio Motta
 * @Copyright (C)2019 - help-desk | MIT License.
 */

public class JwtUser implements UserDetails {

    /**
     * Serialização padrão.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Id do usuario
     */
    private final String id;

    /**
     * Usuario
     */
    private final String username;

    /**
     * Senha
     */
    private final String password;

    /**
     * Permissões
     */
    private final Collection<? extends GrantedAuthority> authorities;

    /**
     * Construtor da classe
     *
     * @param id          id do token
     * @param username    usuario do token
     * @param password    senha do token
     * @param authorities permissões
     */
    public JwtUser(String id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @JsonIgnore
    public String getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
