package me.fmotta.cources.udemy.helpdesk.repository;

import me.fmotta.cources.udemy.helpdesk.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório de entidade do ticket (Spring-Data)
 *
 * @author Antonio Motta
 * @Copyright (C)2019 - help-desk | MIT License.
 */
@Repository
public interface TicketRepository extends MongoRepository<Ticket, String> {

    /**
     * Busca de ticket pelo id do usuário ordernado pela data.
     *
     * @param userId id do usuário
     * @param pages paginação da lista
     * @return tickets.
     */
    Page<Ticket> findByUserIdOrderByDateDesc(Pageable pages, String userId);

    /**
     * Busca de ticket pelo status, prioridade e title do usuário ordernado pela data.
     * @param pages paginação
     * @param status status
     * @param priority prioridade
     * @param title titulo
     * @return tickets
     */
    Page<Ticket> findByTitleIgnoreCaseContainingAndStatusAndPriorityOrderByDateDesc(
            Pageable pages, String status, String priority, String title, String userId);

    /**
     * Busca de ticket pelo status, prioridade, title e pelo id do usuário ordernado pela data.
     * @param pages paginação
     * @param status status
     * @param priority prioridade
     * @param title titulo
     * @param userId id do usuario
     * @return tickets
     */
    Page<Ticket> findByTitleIgnoreCaseContainingAndStatusAndPriorityAndUserIdOrderByDateDesc(
            Pageable pages, String status, String priority, String title, String userId);


    /**
     * Busca de ticket pelo status, prioridade, title e pelo id do usuário ordernado pela data.
     * @param pages paginação
     * @param status status
     * @param priority prioridade
     * @param title titulo
     * @param AssignedUserId id do usuario responsavél
     * @return tickets
     */
    Page<Ticket> findByTitleIgnoreCaseContainingAndStatusAndPriorityAndAssignedUserIdOrderByDateDesc(
            Pageable pages, String status, String priority, String title, String AssignedUserId);

    /**
     * Busca de ticket por numero.
     *
     * @param number numero do chamado
     * @return tickets.
     */
    Page<Ticket> findByNumber(Pageable pages, Integer number);

}
