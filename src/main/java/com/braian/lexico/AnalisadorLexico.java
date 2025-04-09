package com.braian.lexico;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class AnalisadorLexico {

    private final String codigo;
    private final List<Token> tokens = new ArrayList<>();
    private int linha = 1;
    private int coluna = 1;
    private int posicao = 0;

    public AnalisadorLexico(String codigo) {
        this.codigo = codigo;
    }

    public void imprimirTokens() {
        while (!EOF()) {
            escanearToken();
        }

        tokens.add(new Token(TipoToken.EOF, "", null, linha, coluna));

        for (Token token : tokens) {
            System.out.println(token);
        }
    }

    private void escanearToken() {
        String restante = codigo.substring(posicao);

        for (ExpressoesRegulares.RegraToken regra : ExpressoesRegulares.REGRAS) {
            Matcher matcher = regra.padrao().matcher(restante);

            if (matcher.find()) {
                String lexema = matcher.group();

                // Se for nulo, ignorar (comentário, espaço, etc)
                if (regra.tipo() != null) {
                    Object literal = null;

                    if (regra.tipo() == TipoToken.NUMERO) {
                        literal = Integer.parseInt(lexema);
                    }

                    Token token = new Token(regra.tipo(), lexema, literal, linha, coluna);
                    tokens.add(token);
                }

                // Atualiza posição, linha e coluna
                int linhasPuladas = contarQuebrasDeLinha(lexema);

                if (linhasPuladas > 0) {
                    linha += linhasPuladas;
                    coluna = lexema.length() - lexema.lastIndexOf("\n");
                } else {
                    coluna += lexema.length();
                }

                posicao += lexema.length();
                return;
            }
        }

        // Se nenhuma regra bateu
        String caractereInvalido = codigo.substring(posicao, posicao + 1);
        tokens.add(new Token(TipoToken.ERRO, caractereInvalido, null, linha, coluna));
        posicao++;
        coluna++;
    }

    private boolean EOF() {
        return posicao >= codigo.length();
    }

    private int contarQuebrasDeLinha(String texto) {
        int count = 0;
        for (char c : texto.toCharArray()) {
            if (c == '\n') count++;
        }
        return count;
    }
}
