package me.fmotta.cources.udemy.helpdesk.constants;

/**
 * Constante de status do chamado
 *
 * @author Antonio Motta
 * @Copyright (C)2019 - help-desk | MIT License.
 */
public enum Status {

    NEW("Novo"),
    ASSIGNED("Assinado"),
    RESOLVED("RESOLVIDO"),
    APPROVED("Aprovado"),
    DISAPROVED("Reprovado"),
    CLOSED("Fechado");

    /**
     * Nome do status do chamado
     */
    private String name;

    Status(String name) {
        this.name = name;
    }

    /**
     * Busca de nome
     *
     * @return nome do status do chamado
     */
    public String getName() {
        return name;
    }
}
