package me.fmotta.cources.udemy.helpdesk.services;

import me.fmotta.cources.udemy.helpdesk.model.User;
import org.springframework.data.domain.Page;

/**
 * Interface do serviço de usuário
 *
 * @author Antonio Motta
 * @Copyright (C)2019 - help-desk | MIT License.
 */
public interface UserService {

    /**
     * Busca de usuário por email
     *
     * @param email email
     * @return usuário
     */
    User findByEmail(String email);

    /**
     * Cria ou atualiza usuário
     *
     * @param user usuario.
     * @return usuario criado ou atualizado
     */
    User createOrUpdate(User user);

    /**
     * Busca de usuario por id.
     *
     * @param id id do usuario
     * @return usuario
     */
    User findById(String id);

    /**
     * Excluir usuário pelo id
     *
     * @param id id do usuário
     */
    void delete(String id);

    /**
     * Buscar todos usuarios paginados.
     *
     * @param page pagina
     * @param count quantidade por pagina
     * @return todos os usuarios paginados
     */
    Page<User> findAll(int page, int count);
}
