package me.fmotta.cources.udemy.helpdesk.repository;

import me.fmotta.cources.udemy.helpdesk.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório de entidade do usuário (Spring-Data)
 *
 * @author Antonio Motta
 * @Copyright (C)2019 - help-desk | MIT License.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Busca de usuarios por email
     * @param email
     * @return Usuarios
     */
    User findByEmail(String email);
}
