package me.fmotta.cources.udemy.helpdesk.services.impl;

import me.fmotta.cources.udemy.helpdesk.model.User;
import me.fmotta.cources.udemy.helpdesk.repository.UserRepository;
import me.fmotta.cources.udemy.helpdesk.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Implemtação de serviço do usuário
 *
 * @author Antonio Motta
 * @Copyright (C)2019 - help-desk | MIT License.
 */
@Service
public class UserServicesImpl implements UserService {

    /**
     * Repositorio do usuario;
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Serviço de busca de usuários por email
     *
     * @param email email
     * @return usuário
     */
    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    /**
     * Serviço de criar ou atualizar usuário
     *
     * @param user usuario.
     * @return usuário criado ou atualizado.
     */
    @Override
    public User createOrUpdate(User user) {
        return this.userRepository.save(user);
    }

    /**
     * Serviço de busca de usuário por id
     *
     * @param id id do usuario
     * @return usuario.
     */
    @Override
    public User findById(String id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.isPresent() ? user.get() : null;
    }

    /**
     * Serviço de exclusão por usuario
     *
     * @param id id do usuário
     */
    @Override
    public void delete(String id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public Page<User> findAll(int page, int count) {
        Pageable pages = PageRequest.of(page, count);
        return this.userRepository.findAll(pages);
    }
}
