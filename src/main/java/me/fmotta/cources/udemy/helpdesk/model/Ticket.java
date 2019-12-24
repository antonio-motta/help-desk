package me.fmotta.cources.udemy.helpdesk.model;

import me.fmotta.cources.udemy.helpdesk.constants.Priority;
import me.fmotta.cources.udemy.helpdesk.constants.Status;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Modelo de entidade de chamados
 *
 * @author Antonio Motta
 * @Copyright (C)2019 - help-desk | MIT License.
 */
@Document
public class Ticket implements Serializable {

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
     * Usuário do sistema
     */
    @DBRef(lazy = true)
    private User user;

    /**
     * Data do chamado
     */
    @NotNull(message = "Data é obrigatória")
    private Date date;

    /**
     * Titulo do chamado
     */
    @NotNull(message = "Titulo é obrigatório")
    private String title;

    /**
     * Numero do chamado
     */
    @NotNull(message = "Numero do chamado")
    private Integer number;

    /**
     * Usuário responsavel pelo sistema
     */
    @DBRef(lazy = true)
    private User assignedUser;

    /**
     * Status do chamado
     */
    @NotNull(message = "Estado é obrigatório")
    private Status status;

    /**
     * Prioridade do chamado
     */
    @NotNull(message = "Prioridade é obrigatória")
    private Priority priority;

    /**
     * Descrição do chamado
     */
    private String description;

    /**
     * Imagem do chamado
     */
    private String image;

    /**
     * Movimentações do chamado
     */
    @Transient
    private List<ChangeStatus> changes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<ChangeStatus> getChanges() {
        return changes;
    }

    public void setChanges(List<ChangeStatus> changes) {
        this.changes = changes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) &&
                Objects.equals(user, ticket.user) &&
                Objects.equals(date, ticket.date) &&
                Objects.equals(title, ticket.title) &&
                Objects.equals(number, ticket.number) &&
                Objects.equals(assignedUser, ticket.assignedUser) &&
                status == ticket.status &&
                priority == ticket.priority &&
                Objects.equals(description, ticket.description) &&
                Objects.equals(image, ticket.image) &&
                Objects.equals(changes, ticket.changes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, date, title, number, assignedUser, status, priority, description, image, changes);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", number=" + number +
                ", assignedUser=" + assignedUser +
                ", status=" + status +
                ", priority=" + priority +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", changes=" + changes +
                '}';
    }
}
