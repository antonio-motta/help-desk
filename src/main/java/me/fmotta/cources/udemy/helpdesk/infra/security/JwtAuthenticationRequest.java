package me.fmotta.cources.udemy.helpdesk.infra.security;

import java.io.Serializable;
import java.util.Objects;

public class JwtAuthenticationRequest implements Serializable {

    /**
     * Serialização padrão
     */
    private static final long serialVersionUID = 1L;

    /**
     * Email
     */
    private String email;

    /**
     * Senha
     */
    private String password;

    /**
     * Contrutor de autenticação request
     *
     * @param email    email
     * @param password senha
     */
    JwtAuthenticationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JwtAuthenticationRequest that = (JwtAuthenticationRequest) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public String toString() {
        return "JwtAuthenticationRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
