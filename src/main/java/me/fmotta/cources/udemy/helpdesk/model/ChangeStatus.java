package me.fmotta.cources.udemy.helpdesk.model;

import me.fmotta.cources.udemy.helpdesk.constants.Status;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Movimentações do chamado
 *
 * @author Antonio Motta
 * @Copyright (C)2019 - help-desk | MIT License.
 */
@Document
public class ChangeStatus implements Serializable {

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
     * Ticket da movimentação
     */
    @DBRef(lazy = true)
    private Ticket ticket;

    /**
     * Usuário da movimentação
     */
    @DBRef(lazy = true)
    private User user;

    /**
     * Data da movimentação
     */
    private Date date;

    /**
     * Status da movimentação
     */
    private Status status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChangeStatus that = (ChangeStatus) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(ticket, that.ticket) &&
                Objects.equals(user, that.user) &&
                Objects.equals(date, that.date) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticket, user, date, status);
    }
}
