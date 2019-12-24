package me.fmotta.cources.udemy.helpdesk.model;

import me.fmotta.cources.udemy.helpdesk.constants.Profile;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * Modelo de entidade do usuário
 *
 * @author Antonio Motta
 * @Copyright (C)2019 - help-desk | MIT License.
 */
@Document
public class User implements Serializable {

    /**
     * Serialização padrão.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador unico
     */
    @Id
    private String id;

    /**
     * Email do usuário
     */
    @Indexed
    @NotBlank(message = "Email obrigatório")
    @Email(message = "Email inválido")
    private String email;

    /**
     * Senha do usuário
     */
    @NotNull(message = "Senha obrigatória")
    @Size(min = 6)
    private String password;

    /**
     * Perfil do usuário
     */
    @NotNull(message = "Perfil é obrigatório")
    private Profile profile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                profile == user.profile;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, profile);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", profile=" + profile +
                '}';
    }
}
