package me.fmotta.cources.udemy.helpdesk.constants;

/**
 * Constante da prioridade do chamado
 *
 * @author Antonio Motta
 * @Copyright (C)2019 - help-desk | MIT License.
 */
public enum Priority {
    HIGH("Alta"),
    NORMAL("Normal"),
    LOW("Baixa");

    /**
     * Nome do status do chamado
     */
    private String name;

    Priority(String name) {
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

