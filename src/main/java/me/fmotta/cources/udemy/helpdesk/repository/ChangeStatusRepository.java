package me.fmotta.cources.udemy.helpdesk.repository;

import me.fmotta.cources.udemy.helpdesk.model.ChangeStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repositório de entidade de movimentação (Spring-Data)
 *
 * @author Antonio Motta
 * @Copyright (C)2019 - help-desk | MIT License.
 */
public interface ChangeStatusRepository extends MongoRepository<ChangeStatus, String> {

    /**
     * Busca de iterações de movimentação do ticket.
     *
     * @param ticketId id do ticket
     * @return iterações de movimentações.
     */
    Iterable<ChangeStatus> findByTicketIdOrderByDateDesc(String ticketId);
}
