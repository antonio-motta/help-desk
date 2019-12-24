package me.fmotta.cources.udemy.helpdesk.infra.security;

import me.fmotta.cources.udemy.helpdesk.model.User;

import java.io.Serializable;
import java.util.Objects;

/**
 * Modelo do Usuario atual
 *
 * @author Antonio Motta
 * @Copyright (C)2019 - help-desk | MIT License.
 */
public class JwtCurrentUser implements Serializable {

    /**
     * Serialização padrão.
     */
    private static final long serialVersionUID = 1L;


    /**
     * Token
     */
    private String token;

    /**
     * Usuario
     */
    private User user;

    /**
     * Construtor
     *
     * @param token
     * @param user
     */
    public JwtCurrentUser(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JwtCurrentUser that = (JwtCurrentUser) o;
        return Objects.equals(token, that.token) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, user);
    }

    @Override
    public String toString() {
        return "JwtCurrentUser{" +
                "token='" + token + '\'' +
                ", user=" + user +
                '}';
    }
}
