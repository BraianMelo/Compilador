package com.braian.lexico;

public class Token {

    private final TipoToken tipo;
    private final String lexema;
    private final Object literal;
    private final int linha;
    private final int coluna;

    public Token(TipoToken tipo, String lexema, Object literal, int linha, int coluna) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.literal = literal;
        this.linha = linha;
        this.coluna = coluna;
    }

    public TipoToken getTipo() {
        return tipo;
    }

    public String getLexema() {
        return lexema;
    }

    public Object getLiteral() {
        return literal;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    @Override
    public String toString() {
        return String.format("[%s, '%s', %s, %d, %d]",
            tipo, lexema, literal, linha, coluna);
    }
}
