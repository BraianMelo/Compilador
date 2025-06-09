package com.braian.sintatico;

import java.util.ArrayList;
import java.util.List;

import com.braian.lexico.Token;

public class NoArvoreSintatica {
    private String tipo;
    private String valor;
    private List<NoArvoreSintatica> filhos;
    private Token token;

    public NoArvoreSintatica(String tipo) {
        this.tipo = tipo;
        this.filhos = new ArrayList<>();
    }

    public NoArvoreSintatica(String tipo, String valor) {
        this(tipo);
        this.valor = valor;
    }

    public NoArvoreSintatica(String tipo, Token token) {
        this(tipo);
        this.token = token;
        this.valor = token != null ? token.getLexema() : null;
    }

    public void adicionarFilho(NoArvoreSintatica filho) {
        filhos.add(filho);
    }

    public String getTipo() {
        return tipo;
    }

    public String getValor() {
        return valor;
    }

    public List<NoArvoreSintatica> getFilhos() {
        return filhos;
    }

    public Token getToken() {
        return token;
    }

    @Override
    public String toString() {
        return toString(0);
    }

    private String toString(int nivel) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nivel; i++) {
            sb.append("  ");
        }
        sb.append(tipo);
        if (valor != null && !valor.isEmpty()) {
            sb.append(": ").append(valor);
        }
        sb.append("\n");

        for (NoArvoreSintatica filho : filhos) {
            sb.append(filho.toString(nivel + 1));
        }

        return sb.toString();
    }
}
