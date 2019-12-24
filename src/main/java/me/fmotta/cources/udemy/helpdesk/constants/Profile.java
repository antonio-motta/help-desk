package me.fmotta.cources.udemy.helpdesk.constants;

/**
 * Constante de perfil
 *
 * @author Antonio Motta
 * @Copyright (C)2019 - help-desk | MIT License.
 */
public enum Profile {

    ROLE_ADM("Administrador"),

    ROLE_COSTUMER("Cliente"),

    ROLE_TECHNCIAN("Técnico");

    /**
     * Nome do perfil
     */
    private String name;

    Profile(String name) {
        this.name = name;
    }

    /**
     * Busca de nome
     *
     * @return nome do perfil
     */
    public String getName() {
        return name;
    }
}
